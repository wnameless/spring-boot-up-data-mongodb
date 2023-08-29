package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.MonthDay;
import org.springframework.core.convert.converter.Converter;

public class MonthDayWriteConverter implements Converter<MonthDay, String> {

  @Override
  public String convert(MonthDay source) {
    return source.toString();
  }

}
