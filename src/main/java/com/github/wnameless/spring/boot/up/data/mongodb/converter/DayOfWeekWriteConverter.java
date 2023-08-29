package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.DayOfWeek;
import org.springframework.core.convert.converter.Converter;

public class DayOfWeekWriteConverter implements Converter<DayOfWeek, Integer> {

  @Override
  public Integer convert(DayOfWeek source) {
    return source.getValue();
  }

}
