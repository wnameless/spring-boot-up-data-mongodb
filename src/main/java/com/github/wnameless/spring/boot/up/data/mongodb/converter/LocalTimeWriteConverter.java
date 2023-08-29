package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeWriteConverter implements Converter<LocalTime, String> {

  @Override
  public String convert(LocalTime source) {
    return source.toString();
  }

}
