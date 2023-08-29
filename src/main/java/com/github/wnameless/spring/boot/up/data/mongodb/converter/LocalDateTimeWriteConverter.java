package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeWriteConverter implements Converter<LocalDateTime, String> {

  @Override
  public String convert(LocalDateTime source) {
    return source.toString();
  }

}
