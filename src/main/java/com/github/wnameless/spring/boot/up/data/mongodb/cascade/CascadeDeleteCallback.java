package com.github.wnameless.spring.boot.up.data.mongodb.cascade;

import static com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType.ALL;
import static com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType.DELETE;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.CascadeRef;

public class CascadeDeleteCallback implements ReflectionUtils.FieldCallback {

  private final Object source;
  private final MongoOperations mongoOperations;

  public CascadeDeleteCallback(Object source, MongoOperations mongoOperations) {
    this.source = source;
    this.mongoOperations = mongoOperations;
  }

  @Override
  public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
    ReflectionUtils.makeAccessible(field);

    CascadeRef cascade = AnnotationUtils.getAnnotation(field, CascadeRef.class);
    if (cascade == null || !field.isAnnotationPresent(DBRef.class)) {
      return;
    }
    List<CascadeType> cascadeTypes = Arrays.asList(cascade.value());
    if (!cascadeTypes.contains(ALL) && !cascadeTypes.contains(DELETE)) {
      return;
    }
    Object fieldValue = field.get(source);
    if (fieldValue == null) {
      return;
    }

    // Collection field
    if (Collection.class.isAssignableFrom(fieldValue.getClass())
        || Map.class.isAssignableFrom(fieldValue.getClass())) {
      Collection<?> collection;
      if (Map.class.isAssignableFrom(fieldValue.getClass())) {
        collection = ((Map<?, ?>) fieldValue).values();
      } else {
        collection = (Collection<?>) fieldValue;
      }
      for (Object element : collection) {
        cascadeDelete(element);
      }
    } else { // Non-Collection field
      cascadeDelete(fieldValue);
    }
  }

  private void cascadeDelete(Object value) throws IllegalArgumentException, IllegalAccessException {
    IdFieldCallback callback = new IdFieldCallback();
    ReflectionUtils.doWithFields(value.getClass(), callback);

    if (callback.isIdFound() && callback.getId(value) != null) {
      mongoOperations.remove(value);
    }
  }

}
