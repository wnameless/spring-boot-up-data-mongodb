package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.DayOfWeek;
import org.springframework.core.convert.converter.Converter;

public class DayOfWeekReadConverter implements Converter<Integer, DayOfWeek> {

  @Override
  public DayOfWeek convert(Integer source) {
    return DayOfWeek.of(source);
  }

}
