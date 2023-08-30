package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.DayOfWeek;
import org.springframework.core.convert.converter.Converter;

public class DayOfWeekWriteConverter implements Converter<DayOfWeek, String> {

  @Override
  public String convert(DayOfWeek source) {
    return source.name();
  }

}
