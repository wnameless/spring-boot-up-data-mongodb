package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeReadConverter implements Converter<String, LocalTime> {

  @Override
  public LocalTime convert(String source) {
    return LocalTime.parse(source);
  }

}
