package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Duration;
import org.springframework.core.convert.converter.Converter;

public class DurationWriteConverter implements Converter<Duration, String> {

  @Override
  public String convert(Duration source) {
    return source.toString();
  }

}
