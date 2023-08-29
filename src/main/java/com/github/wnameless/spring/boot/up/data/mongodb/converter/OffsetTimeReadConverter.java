package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.OffsetTime;
import org.springframework.core.convert.converter.Converter;

public class OffsetTimeReadConverter implements Converter<String, OffsetTime> {

  @Override
  public OffsetTime convert(String source) {
    return OffsetTime.parse(source);
  }

}
