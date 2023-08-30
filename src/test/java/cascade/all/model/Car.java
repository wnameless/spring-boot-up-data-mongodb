package cascade.all.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
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
  List<Wheel> frontWheels = new ArrayList<>();

  @CascadeRef(CascadeType.ALL)
  @DBRef
  Map<String, Wheel> rareWheels = new HashMap<>();

  @CascadeRef(CascadeType.CREATE)
  @DBRef
  Engine voidEngine1;

  @CascadeRef(CascadeType.UPDATE)
  @DBRef
  Engine voidEngine2;

  @CascadeRef(CascadeType.DELETE)
  @DBRef
  Engine voidEngine3;

  @CascadeRef(CascadeType.CREATE)
  Engine voidEngine4;

  @CascadeRef(CascadeType.UPDATE)
  Engine voidEngine5;

  @CascadeRef(CascadeType.DELETE)
  Engine voidEngine6;

}
