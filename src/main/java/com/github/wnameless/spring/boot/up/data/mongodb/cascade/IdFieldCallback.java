package com.github.wnameless.spring.boot.up.data.mongodb.cascade;

import java.lang.reflect.Field;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

public class IdFieldCallback implements ReflectionUtils.FieldCallback {

  private Field idField;
  private boolean idFound;

  @Override
  public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
    if (field.isAnnotationPresent(Id.class)) {
      ReflectionUtils.makeAccessible(field);
      idField = field;
      idFound = true;
    }
  }

  public Field getIdField() {
    return idField;
  }

  public boolean isIdFound() {
    return idFound;
  }

  public Object getId(Object obj) throws IllegalArgumentException, IllegalAccessException {
    return getIdField().get(obj);
  }

}
