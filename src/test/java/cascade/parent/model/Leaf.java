package cascade.parent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Document
public class Leaf {

  @Id
  String id;

  @ParentRef
  @DBRef
  Branch branch;

}
