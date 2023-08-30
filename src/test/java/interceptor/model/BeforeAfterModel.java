package interceptor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.SourceAndDocument;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.AfterConvertFromMongo;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.AfterSaveToMongo;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.BeforeConvertToMongo;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.BeforeSaveToMongo;
import interceptor.InterceptorTest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Data
@Document
public class BeforeAfterModel {

  @Id
  private String id;

  @BeforeConvertToMongo
  void beforeConvert() {
    InterceptorTest.messages.add("beforeConvertToMongo");
  }

  @BeforeConvertToMongo
  void beforeConvertArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }

  @BeforeSaveToMongo
  void beforeSave() {
    InterceptorTest.messages.add("beforeSaveToMongo");
  }

  @BeforeSaveToMongo
  void beforeSaveArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }

  @AfterSaveToMongo
  void afterSave() {
    InterceptorTest.messages.add("afterSaveToMongo");
  }

  @AfterSaveToMongo
  void afterSaveArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }

  @AfterConvertFromMongo
  void afterConvert() {
    InterceptorTest.messages.add("afterConvertFromMongo");
  }

  @AfterConvertFromMongo
  void afterConvertArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }


}
