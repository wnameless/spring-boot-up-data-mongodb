package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZoneOffset;
import org.springframework.core.convert.converter.Converter;

public class ZoneOffsetReadConverter implements Converter<String, ZoneOffset> {

  @Override
  public ZoneOffset convert(String source) {
    return ZoneOffset.of(source);
  }

}
