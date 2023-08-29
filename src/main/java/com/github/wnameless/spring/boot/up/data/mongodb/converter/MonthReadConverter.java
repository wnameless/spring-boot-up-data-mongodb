package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Month;
import org.springframework.core.convert.converter.Converter;

public class MonthReadConverter implements Converter<Integer, Month> {

  @Override
  public Month convert(Integer source) {
    return Month.of(source);
  }

}
