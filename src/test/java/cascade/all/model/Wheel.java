package cascade.all.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
@Document
public class Wheel {

  @Id
  String id;

  @ParentRef
  @DBRef(lazy = true)
  Car car;

  String tireBrand;

  public Wheel() {}

  public Wheel(String tireBrand) {
    this.tireBrand = tireBrand;
  }

}
