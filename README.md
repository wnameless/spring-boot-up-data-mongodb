[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-data-mongodb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring.boot.up/spring-boot-up-data-mongodb)

spring-boot-up-data-mongodb
=============
MongoDB enhancement brought by SpringBootUp.

## Purpose - reduces the boilerplate code followed by Spring @DBRef
Init:
```java
var car  = new Car();
var gasTank =  new GasTank();
var engine  = new Engine();
var motor = new Motor();
var frontRightWheel = new Wheel();
var frontLeftWheel = new Wheel();
var rareRightWheel = new Wheel();
var rareLeftWheel = new Wheel();
var wheels = Arrays.asList(frontRightWheel, frontLeftWheel, rareRightWheel, rareLeftWheel);
```

Before spring-boot-up-data-mongodb, we have to create and save documents before constructing the @DBRef:
```java
carRepository.save(car);

gasTankRepository.save(gasTank);

engine.setCar(car);
engineRepository.save(engine);

motor.setEngine(engine);
motorRepository.save(motor);

engine.setMotor(motor);
engineRepository.save(engine);

wheelRepository.save(wheels);

car.setGasTank(gasTank);
car.setEngine(engine);
car.setWheels((wheels);
carRepository.save(car);
```

After spring-boot-up-data-mongodb, we only need to focus on the relationships between documents:
```java
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
The version follows the pattern of {MajorVersion.MinorVersion.IncrementalVersion}.<br>
However, the MajorVersion is always matched the Spring Boot major version.<br>
Only MinorVersion and IncrementalVersion are used to represent the changes of this library.

# Quick Start
```java
@EnableSpringBootUpMongo(allowAnnotationDrivenEvent = true) // Default value is false
@EnableSpringBootUp
@Configuration
public class MyConfiguration {}
```

```java
@Repository
public interface CarRepository extends MongoRepository<Car, String>, MongoProjectionRepository<Car>  {} // With projection feature
// public interface CarRepository extends MongoRepository<Car, String> {} // Without projection feature
```

# Feature List<a id='top'></a>
| Name | Description | Since |
| --- | --- | --- |
| [Cascade(@CascadeRef, @ParentRef)](#3.0.0-1) | Cascade feature for Spring Data MongoDB entities | v3.0.0 |
| [Annotation Driven Event](#3.0.0-2) | Annotation Driven Event feature for Mongo Event  | v3.0.0 |
| [Projection](#3.0.0-3) | Projection feature supports both QueryDSL Predicate and Spring Data Query | v3.0.0 |

### [:top:](#top) Cascade<a id='3.0.0-1'></a>
Entity:
```java
@Document
public class Car {
  @Id
  String id;

  @CascadeRef
  @DBRef
  Engine engine;

  @CascadeRef
  @DBRef
  GasTank gasTank;

  @CascadeRef
  @DBRef
  List<Wheel> wheels = new ArrayList<>();
}
```
```java
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
@Document
public class Motor {
  @Id
  String id;

  @ParentRef
  @DBRef
  Engine engine;

  double rpm = 60000;
}
```
```java
@Document
public class Wheel {
  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  String tireBrand;
}
```

Operation:
```java
car.setGasTank(gasTank);
car.setEngine(engine);
engine.setMotor(motor);
car.setWheels(wheels);
carRepository.save(car);

carRepository.count(); // 1
gasTankRepository.count(); // 1
engineRepository.count(); // 1
motorRepository.count(); // 1
wheelRepository.count(); // 4

carRepository.delete(car);
carRepository.count(); // 0
gasTankRepository.count(); // 0
engineRepository.count(); // 0
motorRepository.count(); // 0
wheelRepository.count(); // 0
```

#### Cascade is NOT working on bulk operations(ex: CrudRepository#deleteAll)
```java
car.setGasTank(gasTank);
car.setEngine(engine);
engine.setMotor(motor);
car.setWheels(wheels);
carRepository.save(car);

carRepository.deleteAll(); // Instead, carRepository.deleteAll(carRepository.findAll()) is working well
carRepository.count(); // 0
engineRepository.count(); // 1
motorRepository.count(); // 1
gasTankRepository.count(); // 1
wheelRepository.count(); // 4
```
```diff
+ Using CrudRepository#deleteAll(Iterable) instead of CrudRepository#deleteAll can perform cascade normally in most circumstances
```

### [:top:](#top) Annotation Driven Event<a id='3.0.0-2'></a>
Entity:
```java
@Document
public class Car {
  @Id
  String id;

  @AfterConvertFromMongo
  void afterConvert() {
    System.out.println("afterConvertFromMongo");
  }

  @AfterConvertFromMongo
  void afterConvertArg(SourceAndDocument sad) {
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

  @BeforeConvertToMongo
  void beforeConvert() {
    System.out.println("beforeConvertToMongo");
  }

  @BeforeConvertToMongo
  void beforeConvertArg(SourceAndDocument sad) {
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
- Annotation Driven Event won't be triggered under Mongo bulk operations.
```

### [:top:](#top) Projection<a id='3.0.0-3'></a>
Entity:
```java
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
public class NestedModel {
  Float f;

  Short s;
}
```
```java
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

Projection can be performed by 3 ways:
```java
// By dot path - allows to use dot operator(.) to represent nested projection object
complexModelRepository.findAllProjectedBy("str");

// JUnit
// assertEquals("str", model.getStr());
// assertNull(model.getI());
// assertNull(model.getD());
// assertNull(model.getB());
// assertNull(model.getNested());

complexModelRepository.findProjectedBy("nested.f");

// JUnit
// assertNull(model.getStr());
// assertNull(model.getI());
// assertNull(model.getD());
// assertNull(model.getB());
// assertEquals(7.8f, model.getNested().getF());
```

```java
// By QueryDSL Path
PathBuilder<Car> entityPath = new PathBuilder<>(ComplexModel.class, "entity");
carRepository.findAllProjectedBy(entityPath.getString("str"));


// JUnit
// assertEquals("str", model.getStr());
// assertNull(model.getI());
// assertNull(model.getD());
// assertNull(model.getB());
// assertNull(model.getNested());
```

```java
// By projection Class
carRepository.findAllProjectedBy(ProjectModel.class);

// JUnit
// assertEquals("str", model.getStr());
// assertNull(model.getI());
// assertNull(model.getD());
// assertNull(model.getB());
// assertNull(model.getNested());
```

## MISC
| Note| Since |
| --- | --- |
| Java 17 required. | v3.0.0 |
| Spring Boot 3.0.0+ required. | v3.0.0 |
