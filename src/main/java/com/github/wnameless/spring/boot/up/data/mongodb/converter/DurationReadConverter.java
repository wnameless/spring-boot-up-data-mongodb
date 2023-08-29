package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Duration;
import org.springframework.core.convert.converter.Converter;

public class DurationReadConverter implements Converter<String, Duration> {

  @Override
  public Duration convert(String source) {
    return Duration.parse(source);
  }

}
