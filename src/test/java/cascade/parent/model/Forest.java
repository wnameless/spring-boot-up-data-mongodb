package cascade.parent.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Document
public class Forest {

  @Id
  String id;

  @CascadeRef(CascadeType.CREATE)
  @DBRef
  List<Tree> trees = new ArrayList<>();

  @CascadeRef(CascadeType.ALL)
  @DBRef
  Forest subForest;

}
