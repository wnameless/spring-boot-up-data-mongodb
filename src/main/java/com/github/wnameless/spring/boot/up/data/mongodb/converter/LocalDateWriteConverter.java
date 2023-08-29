package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class LocalDateWriteConverter implements Converter<LocalDate, String> {

  @Override
  public String convert(LocalDate source) {
    return source.toString();
  }

}
