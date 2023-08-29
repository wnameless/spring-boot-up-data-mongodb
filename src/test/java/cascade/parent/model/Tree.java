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

@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Document
public class Tree {

  @Id
  String id;

  @ParentRef("subForest")
  @DBRef
  Forest forest;

  @CascadeRef(CascadeType.CREATE)
  @DBRef
  List<Branch> branches = new ArrayList<>();

}
