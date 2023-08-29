package cascade.github.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, of = {"id"})
@Data
@Document
public class Car {

  @Id
  String id;

  @CascadeRef({CascadeType.CREATE})
  @DBRef
  Engine engine;

  @CascadeRef({CascadeType.CREATE, CascadeType.DELETE})
  @DBRef
  GasTank gasTank;

  @CascadeRef({CascadeType.CREATE, CascadeType.UPDATE})
  @DBRef
  List<Wheel> wheels = new ArrayList<>();

  @CascadeRef({CascadeType.UPDATE, CascadeType.DELETE})
  @DBRef
  GasTank subGasTank;

}
