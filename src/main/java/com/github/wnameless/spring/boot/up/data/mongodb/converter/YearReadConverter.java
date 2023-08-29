package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.time.Year;
import org.springframework.core.convert.converter.Converter;

public class YearReadConverter implements Converter<String, Year> {

  @Override
  public Year convert(String source) {
    return Year.parse(source);
  }

}
