package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZoneId;
import org.springframework.core.convert.converter.Converter;

public class ZoneIdReadConverter implements Converter<String, ZoneId> {

  @Override
  public ZoneId convert(String source) {
    return ZoneId.of(source);
  }

}
