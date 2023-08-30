package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Month;
import org.springframework.core.convert.converter.Converter;

public class MonthWriteConverter implements Converter<Month, String> {

  @Override
  public String convert(Month source) {
    return source.name();
  }

}
