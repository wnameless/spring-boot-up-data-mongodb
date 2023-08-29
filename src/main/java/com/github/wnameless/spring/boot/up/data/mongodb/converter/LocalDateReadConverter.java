package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class LocalDateReadConverter implements Converter<String, LocalDate> {

  @Override
  public LocalDate convert(String source) {
    return LocalDate.parse(source);
  }

}
