package interceptor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
@Document
public class BeforeAfterDeleteModel implements BaseModel {

  @Id
  private String id;

}
