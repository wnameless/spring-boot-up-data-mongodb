package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeReadConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String source) {
    return LocalDateTime.parse(source);
  }

}
