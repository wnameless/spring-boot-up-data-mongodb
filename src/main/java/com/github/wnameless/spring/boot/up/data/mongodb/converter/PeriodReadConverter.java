package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Period;
import org.springframework.core.convert.converter.Converter;

public class PeriodReadConverter implements Converter<String, Period> {

  @Override
  public Period convert(String source) {
    return Period.parse(source);
  }

}
