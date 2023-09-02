[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-data-mongodb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-data-mongodb)

spring-boot-up-data-mongodb
=============
MongoDB enhancement brought by spring-boot-up.

## Goal - introducing `Cascade` and more features into Spring MongoDB
## Purpose - reduces the boilerplate codes followed by Spring `@DBRef`
Entity relationship model:
```mermaid
graph TD;
    Car-->GasTank;
    Car-->Engine;
    Engine-->Motor;
    Car-->Wheel;
    Car-->SubGasTank
```

<details>
<summary>Entity Initialization</summary>

```java
var car  = new Car();
var gasTank =  new GasTank();
var engine  = new Engine();
var motor = new Motor();
var frontRightWheel = new Wheel();
var frontLeftWheel = new Wheel();
var rareRightWheel = new Wheel();
var rareLeftWheel = new Wheel();
```
</details>

___Before___ spring-boot-up-data-mongodb:
```java
// Must save all documents before assigning @DBRef fields

// Create Car
carRepository.save(car);

// Create GasTank with Car ref
gasTank.set(car);
gasTankRepository.save(gasTank);

// Create Engine with Car ref
engine.setCar(car);
engineRepository.save(engine);

// Create Motor with Engine and Car ref
motor.setEngine(engine);
motor.setCar(car);
motorRepository.save(motor);

// Update Engine with Motor ref
engine.setMotor(motor);
engineRepository.save(engine);

// Create Wheel(s) with Car ref
frontRightWheel.setCar(car);
frontLeftWheel.setCar(car);
rareRightWheel.setCar(car);
rareLeftWheel.setCar(car);
wheelRepository.save(wheels);

// Update Car with GasTank, Engine and Wheel(s) ref
car.setGasTank(gasTank);
car.setEngine(engine);
car.setWheels(Arrays.asList(frontRightWheel, frontLeftWheel, rareRightWheel, rareLeftWheel));
carRepository.save(car);
```

___After___ spring-boot-up-data-mongodb:
```java
// Only need to focus on the relationships between documents
car.setGasTank(gasTank);
car.setEngine(engine);
engine.setMotor(motor);
car.setWheels(wheels);

carRepository.save(car);
```

# Maven Repo
```xml
<dependency>
	<groupId>com.github.wnameless.spring.boot.up</groupId>
	<artifactId>spring-boot-up-data-mongodb</artifactId>
	<version>${newestVersion}</version>
	<!-- Newest version shows in the maven-central badge above -->
</dependency>
```
This lib uses Semantic Versioning: `{MAJOR.MINOR.PATCH}`.<br>
However, the MAJOR version is always matched the Spring Boot MAJOR version.
```diff
! Maven dependency spring-boot-starter-data-mongodb is required
```

# Quick Start
```java
@EnableSpringBootUpMongo(allowAnnotationDrivenEvent = true) // Default value is false
@EnableSpringBootUp
@Configuration
public class MyConfiguration {}
```

```java
@Repository
public interface CarRepository extends MongoRepository<Car, String>, MongoProjectionRepository<Car> {}
// With projection feature
```
<details>
<summary>Repository without projection feature</summary>

```java
@Repository
public interface CarRepository extends MongoRepository<Car, String> {}
```
</details>

# Feature List<a id='top'></a>
| Name | Description | Since |
| --- | --- | --- |
| [Cascade(@CascadeRef)](#3.0.0-1) | Cascade feature for Spring Data MongoDB entities | v3.0.0 |
| [@ParentRef](#3.0.0-2) | Automatically set the cascade event publisher object into `@ParentRef` annotated field of the cascade event receiver | v3.0.0 |
| [Annotation Driven Event](#3.0.0-3) | Annotation Driven Event feature for `MongoEvent` | v3.0.0 |
| [Projection](#3.0.0-4) | Projection feature supports both QueryDSL `Predicate` and Spring Data `Query` | v3.0.0 |
| [Custom Conversions](#3.0.0-5) | MongoCustomConversions for Java 8 Date/Time | v3.0.0 |

### [:top:](#top) Cascade(@CascadeRef)<a id='3.0.0-1'></a>
```diff
+ @CascadeRef must annotate alongside @DBRef
```
Entity classes:
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Car {
  @Id
  String id;

  @CascadeRef({CascadeType.CREATE, CascadeType.DELETE})
  @DBRef
  Engine engine;

  @CascadeRef(CascadeType.CREATE)
  @DBRef
  GasTank gasTank;

  @CascadeRef // Equivalent to @CascadeRef(CascadeType.ALL)
  @DBRef
  List<Wheel> wheels = new ArrayList<>();

  @CascadeRef({CascadeType.UPDATE, CascadeType.DELETE})
  @DBRef
  GasTank subGasTank;
}
```
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class GasTank {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  double capacity = 100;
}
```
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Engine {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  double horsePower = 500;

  @CascadeRef
  @DBRef
  Motor motor;
}
```
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Motor {
  @Id
  String id;

  @ParentRef
  @DBRef
  Engine engine;

  @ParentRef("car")
  @DBRef
  Car car;

  double rpm = 60000;
}
```
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Wheel {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  String tireBrand = "MAXXIS";
}

```

JUnit `BeaforeEach`
```java
mongoTemplate.getDb().drop(); // reset DB before each test

car.setGasTank(gasTank);
car.setEngine(engine);
engine.setMotor(motor);
car.setWheels(Arrays.asList(frontRightWheel, frontLeftWheel, rareRightWheel, rareLeftWheel));
carRepository.save(car);
```

Test `CascadeType.CREATE`
```java
assertEquals(1, carRepository.count());
assertEquals(1, gasTankRepository.count());
assertEquals(1, engineRepository.count());
assertEquals(1, motorRepository.count());
assertEquals(4, wheelRepository.count());
```

Test `CascadeType.UPDATE`
```java
car = new Car();
var subGasTank = new GasTank();
car.setSubGasTank(subGasTank);
// Because this car object hasn't been saved, so the CascadeType.UPDATE about the subGasTank object won't be performed
assertThrows(RuntimeException.class, () -> {
  carRepository.save(car);
});

car = new Car();
carRepository.save(car);
var subGasTank = new GasTank();
car.setSubGasTank(subGasTank);
carRepository.save(car);
// Because this car object has been saved, so the CascadeType.UPDATE is performed
assertSame(subGasTank, car.getSubGasTank());
```
The main diffrence between `CascadeType.UPDATE` and plain `@DBREf` is that<br>
`CascadeType.UPDATE` allows unsaved documents to be set in `@DBREf` fields but plain `@DBREf` won't.
```diff
! When @DBRef is established, all field updates will be cascaded mandatory in @DBRef's nature 
```

Test `CascadeType.DELETE`
```java
carRepository.deleteAll();
assertEquals(0, carRepository.count());
assertEquals(1, engineRepository.count());
assertEquals(1, motorRepository.count());
assertEquals(1, gasTankRepository.count());
assertEquals(4, wheelRepository.count());
```
```diff
- Cascade is NOT working on bulk operations(ex: CrudRepository#deleteAll)
```
```java
carRepository.deleteAll(carRepository.findAll()); 
assertEquals(0, carRepository.count());
assertEquals(0, engineRepository.count());
assertEquals(0, motorRepository.count());
assertEquals(1, gasTankRepository.count());
// gasTank won't be deleted because it's only annotated with @CascadeRef(CascadeType.CREATE)
assertEquals(0, wheelRepository.count());
```
```diff
+ Using CrudRepository#deleteAll(Iterable) instead of CrudRepository#deleteAll can perform cascade normally in most circumstances
```

### [:top:](#top) @ParentRef<a id='3.0.0-2'></a>
#### _Default Usage_
Car is treated as a _parent_ of GasTank, because it is an event publisher to GasTank.
<details>
<summary>Car</summary>

```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Car {
  @Id
  String id;

  @CascadeRef({CascadeType.CREATE, CascadeType.DELETE})
  @DBRef
  Engine engine;

  @CascadeRef(CascadeType.CREATE)
  @DBRef
  GasTank gasTank;

  @CascadeRef // Equivalent to @CascadeRef(CascadeType.ALL)
  @DBRef
  List<Wheel> wheels = new ArrayList<>();

  @CascadeRef({CascadeType.UPDATE, CascadeType.DELETE})
  @DBRef
  GasTank subGasTank;
}
```
</details>

Therefore, the `@ParentRef` annotated field of a GasTank will be set by Car automatically.
<details open>
<summary>GasTank</summary>

```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class GasTank {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  double capacity = 100;
}
```
</details>

#### _Advanced Usage_
Engine is treated as a _parent_ of Motor, because it is an event publisher to Motor.
<details>
<summary>Engine</summary>

```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Engine {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  double horsePower = 500;

  @CascadeRef
  @DBRef
  Motor motor;
}
```
</details>

Therefore, the `@ParentRef("car")` field of Motor is set by the _car_ field of Engine automatically.
<details open>
<summary>Motor</summary>

```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class Motor {
  @Id
  String id;

  @ParentRef
  @DBRef
  Engine engine;

  @ParentRef("car")
  @DBRef
  Car car;

  double rpm = 60000;
}
```
</details>

Test `@ParentRef`
```java
// Default usage
assertSame(car, gasTank.getCar());
// Advanced usage
assertSame(car, engine.getCar());
assertSame(engine, motor.getEngine());
assertSame(car, motor.getCar());
```

### [:top:](#top) Annotation Driven Event<a id='3.0.0-3'></a>
6 types of annotation driven events are supported:

* BeforeConvertToMongo
* BeforeSaveToMongo
* AfterSaveToMongo
* AfterConvertFromMongo
* BeforeDeleteFromMongo
* AfterDeleteFromMongo

All annotated methods will be triggered in corresponding MongoDB lifecycle events.

Annotated methods can accept only empty or single `SourceAndDocument` as argument.
`SourceAndDocument` stores both event source object and event BSON Document at that point.
```java
@Document
public class Car {
  @Id
  String id;

  @BeforeConvertToMongo
  void beforeConvert() {
    System.out.println("beforeConvertToMongo");
  }

  @BeforeConvertToMongo
  void beforeConvertArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }

  @BeforeSaveToMongo
  void beforeSave() {
    System.out.println("beforeSaveToMongo");
  }

  @BeforeSaveToMongo
  void beforeSaveArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }

  @AfterSaveToMongo
  void afterSave() {
    System.out.println("afterSaveToMongo");
  }

  @AfterSaveToMongo
  void afterSaveArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }

  @AfterConvertFromMongo
  void afterConvert() {
    System.out.println("afterConvertFromMongo");
  }

  @AfterConvertFromMongo
  void afterConvertArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }

  @BeforeDeleteFromMongo
  void beforeDeleteFromMongo() {
    System.out.println("beforeDeleteFromMongo");
  }

  @BeforeDeleteFromMongo
  void beforeDeleteFromMongoArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }

  @AfterDeleteFromMongo
  void afterDeleteFromMongo() {
    System.out.println("afterDeleteFromMongo");
  }

  @AfterDeleteFromMongo
  void afterDeleteFromMongoArg(SourceAndDocument sad) {
    var car = sad.getSource(Car.class);
  }
}
```
```diff
- Annotation Driven Event won't be triggered under Mongo bulk operations
```

### [:top:](#top) Projection<a id='3.0.0-4'></a>
Entity classes:
```java
@EqualsAndHashCode(of = "id")
@Data
@Document
public class ComplexModel {
  @Id
  String id;

  String str;

  Integer i;

  Double d;

  Boolean b;

  NestedModel nested;
}
```
```java
@Data
public class NestedModel {
  Float f;

  Short s;
}
```
```java
@Data
public class ProjectModel {
  String str;
}
```

Init:
```java
var model = new ComplexModel();
model.setStr("str");
model.setI(123);
model.setD(45.6);
model.setB(true);
var nested = new NestedModel();
nested.setF(7.8f);
nested.setS((short) 9);
model.setNested(nested);
complexModelRepository.save(model);
```

#### Projection can be performed by 3 ways:
Approach 1: dot path 
```java
// Use dot operator(.) to represent nested projection object
var projected = complexModelRepository.findProjectedBy("str");
var nestedProjected = complexModelRepository.findProjectedBy("nested.f");
```
Result:
```java
// JUnit
assertEquals("str", projected.getStr());
assertNull(projected.getI());
assertNull(projected.getD());
assertNull(projected.getB());
assertNull(projected.getNested());

assertNull(nestedProjected.getStr());
assertNull(nestedProjected.getI());
assertNull(nestedProjected.getD());
assertNull(nestedProjected.getB());
assertEquals(7.8f, nestedProjected.getNested().getF());
```

Approach 2: QueryDSL `Path`
```java
// QueryDSL PathBuilder
PathBuilder<Car> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
var projected = carRepository.findProjectedBy(entityPath.getString("str"));
```
Result:
```java
// JUnit
assertEquals("str", projected.getStr());
assertNull(projected.getI());
assertNull(projected.getD());
assertNull(projected.getB());
assertNull(projected.getNested());
```

Approach 3: Java `Class` as projection reference
```java
// By projection Class
var projected = carRepository.findProjectedBy(ProjectModel.class);
```
Result:
```java
// JUnit
assertEquals("str", projected.getStr());
assertNull(projected.getI());
assertNull(projected.getD());
assertNull(projected.getB());
assertNull(projected.getNested());
```

### [:top:](#top) Custom Conversions<a id='3.0.0-5'></a>
MongoDB doesn't natively support Java 8 Date/Time(Ex: `LocalDateTime`), so here is a convenient solution.
`MongoConverters.javaTimeConversions()` includes all types of `Converter` for Java 8 Date/Time.
```java
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
  @Override
  public MongoCustomConversions customConversions() {
    return MongoConverters.javaTimeConversions();
  }
}
```
All Java 8 Date/Time types(excluding DayOfWeek and Month Enums) are converted to `String`, and vice versa.

## MISC
| Note| Since |
| --- | --- |
| Java 17 required. | v3.0.0 |
| Spring Boot 3.0.0+ required. | v3.0.0 |
