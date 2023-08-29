package cascade.github.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, of = {"id"})
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
