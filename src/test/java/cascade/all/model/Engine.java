package cascade.all.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, of = {"id"})
@Data
@Document
public class Engine {

  @Id
  String id;

  @ParentRef
  @DBRef
  Car car;

  double horsePower;

  @CascadeRef
  @DBRef
  Motor motor;

}
