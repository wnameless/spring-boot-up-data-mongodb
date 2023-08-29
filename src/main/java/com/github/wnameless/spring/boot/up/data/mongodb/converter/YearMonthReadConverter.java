package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.YearMonth;
import org.springframework.core.convert.converter.Converter;

public class YearMonthReadConverter implements Converter<String, YearMonth> {

  @Override
  public YearMonth convert(String source) {
    return YearMonth.parse(source);
  }

}
