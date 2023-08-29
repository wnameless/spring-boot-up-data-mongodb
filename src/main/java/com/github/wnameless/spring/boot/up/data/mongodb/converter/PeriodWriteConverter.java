package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Period;
import org.springframework.core.convert.converter.Converter;

public class PeriodWriteConverter implements Converter<Period, String> {

  @Override
  public String convert(Period source) {
    return source.toString();
  }

}
