package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZonedDateTime;
import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeReadConverter implements Converter<String, ZonedDateTime> {

  @Override
  public ZonedDateTime convert(String source) {
    return ZonedDateTime.parse(source);
  }

}
