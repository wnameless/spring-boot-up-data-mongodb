package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.OffsetTime;
import org.springframework.core.convert.converter.Converter;

public class OffsetTimeWriteConverter implements Converter<OffsetTime, String> {

  @Override
  public String convert(OffsetTime source) {
    return source.toString();
  }

}
