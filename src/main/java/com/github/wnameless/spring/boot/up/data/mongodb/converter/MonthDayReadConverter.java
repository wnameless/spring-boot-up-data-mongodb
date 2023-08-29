package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.MonthDay;
import org.springframework.core.convert.converter.Converter;

public class MonthDayReadConverter implements Converter<String, MonthDay> {

  @Override
  public MonthDay convert(String source) {
    return MonthDay.parse(source);
  }

}
