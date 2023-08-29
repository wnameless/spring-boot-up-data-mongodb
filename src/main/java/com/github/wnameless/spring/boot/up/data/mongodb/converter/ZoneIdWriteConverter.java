package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZoneId;
import org.springframework.core.convert.converter.Converter;

public class ZoneIdWriteConverter implements Converter<ZoneId, String> {

  @Override
  public String convert(ZoneId source) {
    return source.toString();
  }

}
