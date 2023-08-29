package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.OffsetDateTime;
import org.springframework.core.convert.converter.Converter;

public class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {

  @Override
  public String convert(OffsetDateTime source) {
    return source.toString();
  }

}
