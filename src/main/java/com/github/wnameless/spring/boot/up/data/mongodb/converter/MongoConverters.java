package com.github.wnameless.spring.boot.up.data.mongodb.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

public final class MongoConverters {

  private MongoConverters() {}

  public static MongoCustomConversions javaTimeConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(new YearWriteConverter());
    converters.add(new YearReadConverter());
    converters.add(new YearMonthWriteConverter());
    converters.add(new YearMonthReadConverter());
    converters.add(new LocalTimeWriteConverter());
    converters.add(new LocalTimeReadConverter());
    converters.add(new LocalDateWriteConverter());
    converters.add(new LocalDateReadConverter());
    converters.add(new LocalDateTimeWriteConverter());
    converters.add(new LocalDateTimeReadConverter());
    converters.add(new ZonedDateTimeWriteConverter());
    converters.add(new ZonedDateTimeReadConverter());
    converters.add(new OffsetTimeWriteConverter());
    converters.add(new OffsetTimeReadConverter());
    converters.add(new OffsetDateTimeWriteConverter());
    converters.add(new OffsetDateTimeReadConverter());
    converters.add(new MonthDayWriteConverter());
    converters.add(new MonthDayReadConverter());
    converters.add(new PeriodWriteConverter());
    converters.add(new PeriodReadConverter());
    converters.add(new DurationWriteConverter());
    converters.add(new DurationReadConverter());
    converters.add(new ZoneIdWriteConverter());
    converters.add(new ZoneIdReadConverter());
    converters.add(new ZoneOffsetWriteConverter());
    converters.add(new ZoneOffsetReadConverter());
    converters.add(new InstantWriteConverter());
    converters.add(new InstantReadConverter());
    return new MongoCustomConversions(converters);
  }

}
