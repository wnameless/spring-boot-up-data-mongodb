package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.DayOfWeek;
import org.springframework.core.convert.converter.Converter;

public class DayOfWeekReadConverter implements Converter<String, DayOfWeek> {

  @Override
  public DayOfWeek convert(String source) {
    return DayOfWeek.valueOf(source);
  }

}
