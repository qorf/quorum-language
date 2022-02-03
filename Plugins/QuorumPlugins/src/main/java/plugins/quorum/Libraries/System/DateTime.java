package plugins.quorum.Libraries.System;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * The default implementation of the date time plugin for the compiler.
 * 
 * @author Andreas Stefik
 */
public class DateTime {
    public java.lang.Object me_ = null;

    private boolean now = true; // if false, user has called SetEpochTime()
    private TimeZone timezone = new SimpleTimeZone(Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()), "local timezone");
    private long epochTime = 0; // only used if user has called SetEpochTime()*/
    private Calendar cal;

    ZoneId zoneId = ZoneId.systemDefault();
    public ZonedDateTime zDateTime = ZonedDateTime.now(ZoneId.systemDefault());

    public int GetYear() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getYear();
    }

    public int GetMonth() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getMonthValue();
    }

    public int GetDayOfMonth() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getDayOfMonth();
    }

    public int GetDayOfWeek() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getDayOfWeek().getValue();
    }

    public int GetHour() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getHour();
    }

    public int GetMinute() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getMinute();
    }

    public int GetSecond() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.getSecond();
    }

    public int GetTimeZone() {
        ZonedDateTime temp = ZonedDateTime.now(zoneId);
        ZoneOffset offset = temp.getOffset();
        return (offset.getTotalSeconds()) / 3600;
    }

    public boolean IsDaylightSavings() {
        return zoneId.getRules().isDaylightSavings(zDateTime.toInstant());
    }

    public double GetEpochTime() {
        if (now)
            zDateTime = zDateTime.now(zoneId);
        return zDateTime.toInstant().toEpochMilli();
    }

    public void SetEpochTime(double epochTime) {
        // Set the internal representation of time to the given epoch time.
        now = false;
        Instant instant = Instant.ofEpochMilli((long)epochTime);
        zDateTime = ZonedDateTime.ofInstant(instant, zoneId);
    }

    public void SetTimeZone(int timeZoneOffset) {
        timezone = new SimpleTimeZone(timeZoneOffset * 1000 * 60 *60, "custom time zone");
        zoneId = timezone.toZoneId();
    }

    public double Now() {
        return System.currentTimeMillis();
    }

    //output functions
    public String GetTimestamp() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String ToText() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public String TimeToText() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public String DateToText() {
        if (now)
            zDateTime = zDateTime.now(zoneId);

        return zDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String GetMonthName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return zDateTime.format(formatter);
    }

    public String GetShortMonthName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        return zDateTime.format(formatter);
    }

    public String GetDayOfWeekName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        return zDateTime.format(formatter);
    }

    public String GetShortDayOfWeekName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");
        return zDateTime.format(formatter);
    }
    // parse functions
    public void ParseTimestamp(String timestamp) {
        now = false;
        timestamp = timestamp.replace(' ', 'T');
        LocalDateTime temp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        zDateTime = temp.atZone(zoneId);
    }

    public void ParseDate(String date) {
        now = false;
        LocalDate temp = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        zDateTime = temp.atStartOfDay().atZone(zoneId);
    }

    public void ParseAmericanDate(String date) {
        now = false;
        LocalDate temp = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/[uuuu][uu]"));
        zDateTime = temp.atStartOfDay().atZone(zoneId);
    }

    public void ParseTime(String time, boolean keepDate) {
        LocalTime temp = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDate date;
        if (keepDate) {
            date = zDateTime.toLocalDate();
        } else {
            date = LocalDate.now();
        }
        zDateTime = ZonedDateTime.of(date, temp, zoneId);
    }

    // compare functions
    public boolean IsBefore(quorum.Libraries.System.DateTime_ dateTime) {
        quorum.Libraries.System.DateTime temp = (quorum.Libraries.System.DateTime) dateTime;
        DateTime dt = temp.plugin_;
        ZonedDateTime compared = dt.zDateTime;
        if (now && dt.now)
            return false;
        if (now)
            zDateTime = ZonedDateTime.now(zoneId);
        if (dt.now)
            compared = ZonedDateTime.now(zoneId);
        return zDateTime.isBefore(dt.zDateTime);
    }

    public boolean IsAfter(quorum.Libraries.System.DateTime_ dateTime) {
        quorum.Libraries.System.DateTime temp = (quorum.Libraries.System.DateTime) dateTime;
        DateTime dt = temp.plugin_;
        ZonedDateTime compared = dt.zDateTime;
        if (now && dt.now)
            return false;
        if (now)
            zDateTime = ZonedDateTime.now(zoneId);
        if (dt.now)
            compared = ZonedDateTime.now(zoneId);
        return zDateTime.isAfter(dt.zDateTime);
    }

    // math functions
    public void AddYears(int years) {
        now = false;
        zDateTime = zDateTime.plusYears(years);
    }

    public void AddMonths(int months) {
        now = false;
        zDateTime = zDateTime.plusMonths(months);
    }

    public void AddWeeks(int weeks) {
        now = false;
        zDateTime = zDateTime.plusWeeks(weeks);
    }

    public void AddDays(int days) {
        now = false;
        zDateTime = zDateTime.plusDays(days);
    }

    public void AddHours(int hours) {
        now = false;
        zDateTime = zDateTime.plusHours(hours);
    }

    public void AddMinutes(int minutes) {
        now = false;
        zDateTime = zDateTime.plusMinutes(minutes);
    }

    public void AddSeconds(int seconds) {
        now = false;
        zDateTime = zDateTime.plusSeconds(seconds);
    }

    public void SubtractYears(int years) {
        now = false;
        zDateTime = zDateTime.minusYears(years);
    }

    public void SubtractMonths(int months) {
        now = false;
        zDateTime = zDateTime.minusMonths(months);
    }

    public void SubtractWeeks(int weeks) {
        now = false;
        zDateTime = zDateTime.minusWeeks(weeks);
    }

    public void SubtractDays(int days) {
        now = false;
        zDateTime = zDateTime.minusDays(days);
    }

    public void SubtractHours(int hours) {
        now = false;
        zDateTime = zDateTime.minusHours(hours);
    }

    public void SubtractMinutes(int minutes) {
        now = false;
        zDateTime = zDateTime.minusMinutes(minutes);
    }

    public void SubtractSeconds(int seconds) {
        now = false;
        zDateTime = zDateTime.minusSeconds(seconds);
    }
}
