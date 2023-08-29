package interceptor.model;

import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.SourceAndDocument;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.AfterDeleteFromMongo;
import com.github.wnameless.spring.boot.up.data.mongodb.interceptor.annotation.BeforeDeleteFromMongo;
import interceptor.InterceptorTest;

public interface BaseModel {

  @BeforeDeleteFromMongo
  default void beforeDeleteFromMongo() {
    InterceptorTest.messages.add("beforeDeleteFromMongo");
  }

  @BeforeDeleteFromMongo
  default void beforeDeleteFromMongoArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }

  @AfterDeleteFromMongo
  default void afterDeleteFromMongo() {
    InterceptorTest.messages.add("afterDeleteFromMongo");
  }

  @AfterDeleteFromMongo
  default void afterDeleteFromMongoArg(SourceAndDocument sad) {
    InterceptorTest.sads.add(sad);
  }

}
