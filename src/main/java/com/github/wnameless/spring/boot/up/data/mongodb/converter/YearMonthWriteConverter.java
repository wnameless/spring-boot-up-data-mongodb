package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.YearMonth;
import org.springframework.core.convert.converter.Converter;

public class YearMonthWriteConverter implements Converter<YearMonth, String> {

  @Override
  public String convert(YearMonth source) {
    return source.toString();
  }

}
