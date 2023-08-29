package com.github.wnameless.spring.boot.up.data.mongodb.cascade;

import static com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType.ALL;
import static com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType.CREATE;
import static com.github.wnameless.spring.boot.up.data.mongodb.cascade.CascadeType.UPDATE;
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
import com.github.wnameless.spring.boot.up.data.mongodb.cascade.annotation.ParentRef;

public class ParentRefCallback implements ReflectionUtils.FieldCallback {

  private final Object source;
  private final MongoOperations mongoOperations;

  public ParentRefCallback(Object source, MongoOperations mongoOperations) {
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
    if (!cascadeTypes.contains(ALL) && !cascadeTypes.contains(CREATE)
        && !cascadeTypes.contains(UPDATE)) {
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
        cascadeParentRef(element);
      }
    } else { // Non-Collection field
      cascadeParentRef(fieldValue);
    }
  }

  private void cascadeParentRef(Object value)
      throws IllegalArgumentException, IllegalAccessException {
    IdFieldCallback callback = new IdFieldCallback();
    ReflectionUtils.doWithFields(value.getClass(), callback);

    if (callback.isIdFound()) {
      for (Field f : value.getClass().getDeclaredFields()) {
        ReflectionUtils.makeAccessible(f);

        ParentRef parentRef = AnnotationUtils.findAnnotation(f, ParentRef.class);
        if (parentRef != null) {
          String refFieldName = parentRef.value();

          if (refFieldName.isEmpty()) {
            if (f.getType().isAssignableFrom(source.getClass())) {
              f.set(value, source);
              mongoOperations.save(value);
            }
          } else {
            Field srcField = ReflectionUtils.findField(source.getClass(), refFieldName);
            Object srcFieldVal = ReflectionUtils.getField(srcField, source);
            if (srcFieldVal != null) {
              if (f.getType().isAssignableFrom(srcFieldVal.getClass())) {
                f.set(value, srcFieldVal);
                mongoOperations.save(value);
              }
            }
          }
        }
      }
    }
  }

}
