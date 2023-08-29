package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Instant;
import org.springframework.core.convert.converter.Converter;

public class InstantReadConverter implements Converter<String, Instant> {

  @Override
  public Instant convert(String source) {
    return Instant.parse(source);
  }

}
