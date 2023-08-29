package converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.DayOfWeekReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.DayOfWeekWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.DurationReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.DurationWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.InstantReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.InstantWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalDateReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalDateTimeReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalDateTimeWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalDateWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalTimeReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.LocalTimeWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MongoConverters;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MonthDayReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MonthDayWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MonthReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MonthWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.OffsetDateTimeReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.OffsetDateTimeWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.OffsetTimeReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.OffsetTimeWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.PeriodReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.PeriodWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.YearMonthReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.YearMonthWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.YearReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.YearWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZoneIdReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZoneIdWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZoneOffsetReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZoneOffsetWriteConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZonedDateTimeReadConverter;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.ZonedDateTimeWriteConverter;

public class ConverterTest {

  @Test
  public void testMongoConverters() {
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(Year.class, String.class));
    assertTrue(MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, Year.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(YearMonth.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, YearMonth.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(LocalDate.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, LocalDate.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(LocalTime.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, LocalTime.class));
    assertTrue(MongoConverters.javaTimeConversions().hasCustomWriteTarget(LocalDateTime.class,
        String.class));
    assertTrue(MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class,
        LocalDateTime.class));
    assertTrue(MongoConverters.javaTimeConversions().hasCustomWriteTarget(ZonedDateTime.class,
        String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, OffsetTime.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(OffsetTime.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, MonthDay.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(MonthDay.class, String.class));
    assertTrue(MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class,
        ZonedDateTime.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(Month.class, Integer.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(Integer.class, Month.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(DayOfWeek.class, Integer.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(Integer.class, DayOfWeek.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, Period.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(Period.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, Duration.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(Duration.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, ZoneId.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(ZoneId.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, ZoneOffset.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(ZoneOffset.class, String.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomReadTarget(String.class, Instant.class));
    assertTrue(
        MongoConverters.javaTimeConversions().hasCustomWriteTarget(Instant.class, String.class));
  }

  @Test
  public void testYearConverter() {
    var time = Year.now();
    var reader = new YearReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new YearWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testYearMonthConverter() {
    var time = YearMonth.now();
    var reader = new YearMonthReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new YearMonthWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testLocalDateConverter() {
    var time = LocalDate.now();
    var reader = new LocalDateReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new LocalDateWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testLocalTimeConverter() {
    var time = LocalTime.now();
    var reader = new LocalTimeReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new LocalTimeWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testLocalDateTimeConverter() {
    var time = LocalDateTime.now();
    var reader = new LocalDateTimeReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new LocalDateTimeWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testZonedDateTimeConverter() {
    var time = ZonedDateTime.now();
    var reader = new ZonedDateTimeReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new ZonedDateTimeWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testOffsetTimeConverter() {
    var time = OffsetTime.now();
    var reader = new OffsetTimeReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new OffsetTimeWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testOffsetDateTimeConverter() {
    var time = OffsetDateTime.now();
    var reader = new OffsetDateTimeReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new OffsetDateTimeWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testMonthDayConverter() {
    var time = MonthDay.now();
    var reader = new MonthDayReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new MonthDayWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testMonthConverter() {
    var time = Month.of(1);
    var reader = new MonthReadConverter();
    assertEquals(time, reader.convert(time.getValue()));
    var writer = new MonthWriteConverter();
    assertEquals(time.getValue(), writer.convert(time));
  }

  @Test
  public void testDayOfWeekConverter() {
    var time = DayOfWeek.MONDAY;
    var reader = new DayOfWeekReadConverter();
    assertEquals(time, reader.convert(time.getValue()));
    var writer = new DayOfWeekWriteConverter();
    assertEquals(time.getValue(), writer.convert(time));
  }

  @Test
  public void testPeriodConverter() {
    var time = Period.ofMonths(1);
    var reader = new PeriodReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new PeriodWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testDurationConverter() {
    var time = Duration.ofDays(1);
    var reader = new DurationReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new DurationWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testZoneIdConverter() {
    var time = ZoneId.systemDefault();
    var reader = new ZoneIdReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new ZoneIdWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testZoneOffsetConverter() {
    var time = ZoneOffset.UTC;
    var reader = new ZoneOffsetReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new ZoneOffsetWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

  @Test
  public void testInstantConverter() {
    var time = Instant.now();
    var reader = new InstantReadConverter();
    assertEquals(time, reader.convert(time.toString()));
    var writer = new InstantWriteConverter();
    assertEquals(time.toString(), writer.convert(time));
  }

}
