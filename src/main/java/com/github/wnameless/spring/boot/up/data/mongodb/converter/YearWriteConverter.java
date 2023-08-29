package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Year;
import org.springframework.core.convert.converter.Converter;

public class YearWriteConverter implements Converter<Year, String> {

  @Override
  public String convert(Year source) {
    return source.toString();
  }

}
