package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Instant;
import org.springframework.core.convert.converter.Converter;

public class InstantWriteConverter implements Converter<Instant, String> {

  @Override
  public String convert(Instant source) {
    return source.toString();
  }

}
