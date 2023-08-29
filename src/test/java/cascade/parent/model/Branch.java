package cascade.parent.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Document
public class Branch {

  @Id
  String id;

  @ParentRef
  @DBRef
  Tree tree;

  @ToString.Exclude
  @CascadeRef(CascadeType.UPDATE)
  @DBRef
  List<Leaf> leaves = new ArrayList<>();

}
