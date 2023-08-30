package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Month;
import org.springframework.core.convert.converter.Converter;

public class MonthReadConverter implements Converter<String, Month> {

  @Override
  public Month convert(String source) {
    return Month.valueOf(source);
  }

}
