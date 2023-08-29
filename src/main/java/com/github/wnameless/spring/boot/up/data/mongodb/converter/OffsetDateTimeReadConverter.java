package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.OffsetDateTime;
import org.springframework.core.convert.converter.Converter;

public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {

  @Override
  public OffsetDateTime convert(String source) {
    return OffsetDateTime.parse(source);
  }

}
