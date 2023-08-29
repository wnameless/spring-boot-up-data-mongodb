package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZonedDateTime;
import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, String> {

  @Override
  public String convert(ZonedDateTime source) {
    return source.toString();
  }

}
