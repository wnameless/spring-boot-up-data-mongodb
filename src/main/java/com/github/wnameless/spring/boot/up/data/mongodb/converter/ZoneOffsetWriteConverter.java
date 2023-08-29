package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.ZoneOffset;
import org.springframework.core.convert.converter.Converter;

public class ZoneOffsetWriteConverter implements Converter<ZoneOffset, String> {

  @Override
  public String convert(ZoneOffset source) {
    return source.toString();
  }

}
