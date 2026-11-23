/*
The JavaScript DateTime plugin. It follows the Java plugin: a DateTime holds a
moment (milliseconds since the epoch) and a time zone, either the machine's
own (with its daylight saving rules, the default) or a fixed offset in whole
hours (SetTimeZone). Until a time is set (SetEpochTime, a Parse action or an
Add or Subtract action), the DateTime follows the clock. Dates come from
arithmetic on the epoch milliseconds, so years before 100 work as in Java.
*/
var global_DateTime_Day_Milliseconds_ = 86400000;
var global_DateTime_Month_Names_ = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var global_DateTime_Day_Names_ = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

// Days from 1970-01-01 to a year, month (1 to 12) and day.
function global_DateTime_Days_From_Civil_(year, month, day) {
    var y = month <= 2 ? year - 1 : year;
    var era = Math.floor(y / 400);
    var yearOfEra = y - era * 400;
    var dayOfYear = Math.floor((153 * (month > 2 ? month - 3 : month + 9) + 2) / 5) + day - 1;
    var dayOfEra = yearOfEra * 365 + Math.floor(yearOfEra / 4) - Math.floor(yearOfEra / 100) + dayOfYear;
    return era * 146097 + dayOfEra - 719468;
}

// The [year, month (1 to 12), day] of a count of days from 1970-01-01.
function global_DateTime_Civil_From_Days_(days) {
    var z = days + 719468;
    var era = Math.floor(z / 146097);
    var dayOfEra = z - era * 146097;
    var yearOfEra = Math.floor((dayOfEra - Math.floor(dayOfEra / 1460) + Math.floor(dayOfEra / 36524) - Math.floor(dayOfEra / 146096)) / 365);
    var dayOfYear = dayOfEra - (365 * yearOfEra + Math.floor(yearOfEra / 4) - Math.floor(yearOfEra / 100));
    var mp = Math.floor((5 * dayOfYear + 2) / 153);
    var day = dayOfYear - Math.floor((153 * mp + 2) / 5) + 1;
    var month = mp < 10 ? mp + 3 : mp - 9;
    var year = yearOfEra + era * 400;
    return [month <= 2 ? year + 1 : year, month, day];
}

function global_DateTime_Month_Length_(year, month) {
    if (month === 2) {
        return ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) ? 29 : 28;
    }
    if (month === 4 || month === 6 || month === 9 || month === 11) {
        return 30;
    }
    return 31;
}

function global_DateTime_Two_(n) {
    return (n < 10 ? "0" : "") + n;
}

// A year as ISO 8601 writes it: four digits, with a sign beyond 9999 or below 0.
function global_DateTime_Year_Text_(year) {
    if (year < 0) {
        return "-" + String(-year).padStart(4, "0");
    }
    if (year > 9999) {
        return "+" + year;
    }
    return String(year).padStart(4, "0");
}

function global_DateTime_Error_(message) {
    var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
    exceptionInstance_.SetErrorMessage$quorum_text(message);
    return exceptionInstance_;
}

// Reads an ISO 8601 date (2024-03-15) as [year, month, day], or null. As
// Java's ISO_LOCAL_DATE (strict), the date must exist.
function global_DateTime_Read_Date_(value) {
    var parts = /^([+-]?)(\d{4,10})-(\d{2})-(\d{2})$/.exec(value);
    if (parts === null) {
        return null;
    }
    if ((parts[1] === "" && parts[2].length !== 4) || (parts[1] === "+" && parts[2].length === 4)) {
        return null;
    }
    var year = Number(parts[2]);
    if (parts[1] === "-") {
        year = -year;
    }
    var month = Number(parts[3]);
    var day = Number(parts[4]);
    if (month < 1 || month > 12 || day < 1 || day > global_DateTime_Month_Length_(year, month)) {
        return null;
    }
    return [year, month, day];
}

// Reads an ISO 8601 time (13:45, 13:45:30 or 13:45:30.5) as
// [hour, minute, second, millisecond], or null.
function global_DateTime_Read_Time_(value) {
    var parts = /^(\d{2}):(\d{2})(?::(\d{2})(?:\.(\d{1,9}))?)?$/.exec(value);
    if (parts === null) {
        return null;
    }
    var hour = Number(parts[1]);
    var minute = Number(parts[2]);
    var second = parts[3] === undefined ? 0 : Number(parts[3]);
    var millisecond = parts[4] === undefined ? 0 : Number((parts[4] + "00").substring(0, 3));
    if (hour > 23 || minute > 59 || second > 59) {
        return null;
    }
    return [hour, minute, second, millisecond];
}

function plugins_quorum_Libraries_System_DateTime_() {

    this.time = Date.now();
    this.now = true;
    // null for the machine's time zone, or a fixed offset in hours.
    this.zone = null;

    // The offset from UTC, in milliseconds, at a moment.
    this.Offset_ = function (time) {
        if (this.zone === null) {
            var offset = new Date(time).getTimezoneOffset();
            return isNaN(offset) ? 0 : -offset * 60000;
        }
        return this.zone * 3600000;
    };

    // The moment for a clock time in this time zone, given in milliseconds
    // as if it were UTC. As Java: in a daylight saving gap the time moves
    // later by the gap; in an overlap the earlier offset is used, unless
    // "keep" (the offset before an Add or Subtract) is the other one.
    this.Resolve_ = function (local, keep) {
        var before = this.Offset_(local - global_DateTime_Day_Milliseconds_);
        var after = this.Offset_(local + global_DateTime_Day_Milliseconds_);
        var beforeValid = this.Offset_(local - before) === before;
        var afterValid = this.Offset_(local - after) === after;
        if (beforeValid && afterValid && keep === after) {
            return local - after;
        }
        if (beforeValid) {
            return local - before;
        }
        if (afterValid) {
            return local - after;
        }
        return local - before;
    };

    this.Update_ = function () {
        if (this.now) {
            this.time = Date.now();
        }
    };

    // [year, month, day, hour, minute, second, millisecond, day of week
    // (1 is Monday), days since 1970-01-01, offset] in this time zone.
    this.Fields_ = function () {
        this.Update_();
        var offset = this.Offset_(this.time);
        var local = this.time + offset;
        var days = Math.floor(local / global_DateTime_Day_Milliseconds_);
        var rest = local - days * global_DateTime_Day_Milliseconds_;
        var date = global_DateTime_Civil_From_Days_(days);
        var weekday = (((days + 3) % 7) + 7) % 7 + 1;
        return [date[0], date[1], date[2], Math.floor(rest / 3600000), Math.floor(rest / 60000) % 60,
            Math.floor(rest / 1000) % 60, rest % 1000, weekday, days, offset];
    };

    // Sets the time to a clock time in this time zone.
    this.SetLocal_ = function (year, month, day, hour, minute, second, millisecond, keep) {
        var local = global_DateTime_Days_From_Civil_(year, month, day) * global_DateTime_Day_Milliseconds_
            + hour * 3600000 + minute * 60000 + second * 1000 + millisecond;
        this.time = this.Resolve_(local, keep);
        this.now = false;
    };

    this.GetYear = function () {
        return this.Fields_()[0];
    };

    this.GetMonth = function () {
        return this.Fields_()[1];
    };

    this.GetDayOfMonth = function () {
        return this.Fields_()[2];
    };

    // ISO 8601: 1 is Monday and 7 is Sunday.
    this.GetDayOfWeek = function () {
        return this.Fields_()[7];
    };

    this.GetHour = function () {
        return this.Fields_()[3];
    };

    this.GetMinute = function () {
        return this.Fields_()[4];
    };

    this.GetSecond = function () {
        return this.Fields_()[5];
    };

    // Whole hours from UTC at the current time, as the Java plugin (it was
    // minutes, positive west of UTC).
    this.GetTimeZone = function () {
        return Math.trunc(this.Offset_(Date.now()) / 3600000);
    };

    // A fixed offset has no daylight saving time. In the machine's time
    // zone, the standard offset is the smaller of January's and July's.
    this.IsDaylightSavings = function () {
        if (this.zone !== null) {
            return false;
        }
        var year = this.Fields_()[0];
        var january = this.Offset_(global_DateTime_Days_From_Civil_(year, 1, 1) * global_DateTime_Day_Milliseconds_);
        var july = this.Offset_(global_DateTime_Days_From_Civil_(year, 7, 1) * global_DateTime_Day_Milliseconds_);
        return this.Offset_(this.time) > Math.min(january, july);
    };

    this.GetEpochTime = function () {
        this.Update_();
        return this.time;
    };

    this.SetEpochTime$quorum_number = function (value) {
        this.now = false;
        this.time = Math.trunc(value);
    };

    // A fixed offset in whole hours, -18 to 18 as Java allows. A time
    // already set stays the same moment, shown in the new zone.
    this.SetTimeZone$quorum_integer = function (value) {
        if (value < -18 || value > 18) {
            throw global_DateTime_Error_("The time zone offset " + value + " is not from -18 to 18.");
        }
        this.zone = value;
    };

    this.Now = function () {
        return Date.now();
    };

    // ISO 8601 time, as Java's ISO_LOCAL_TIME: 09:26:53, with a fraction
    // of a second only when it is not 0, without trailing zeros.
    this.Time_Text_ = function (f) {
        var result = global_DateTime_Two_(f[3]) + ":" + global_DateTime_Two_(f[4]) + ":" + global_DateTime_Two_(f[5]);
        if (f[6] !== 0) {
            var fraction = String(f[6]).padStart(3, "0");
            while (fraction.charAt(fraction.length - 1) === "0") {
                fraction = fraction.substring(0, fraction.length - 1);
            }
            result = result + "." + fraction;
        }
        return result;
    };

    this.Date_Text_ = function (f) {
        return global_DateTime_Year_Text_(f[0]) + "-" + global_DateTime_Two_(f[1]) + "-" + global_DateTime_Two_(f[2]);
    };

    // ISO 8601 date and time, as Java's ISO_LOCAL_DATE_TIME: 2015-03-14T09:26:53.
    this.GetTimestamp = function () {
        var f = this.Fields_();
        return this.Date_Text_(f) + "T" + this.Time_Text_(f);
    };

    // RFC 1123, as Java's RFC_1123_DATE_TIME: Fri, 15 Mar 2024 13:45:30 -0700
    // (GMT for an offset of 0).
    this.ToText = function () {
        var f = this.Fields_();
        var zone = "GMT";
        var minutes = Math.trunc(f[9] / 60000);
        if (minutes !== 0) {
            var size = Math.abs(minutes);
            zone = (minutes < 0 ? "-" : "+") + global_DateTime_Two_(Math.floor(size / 60)) + global_DateTime_Two_(size % 60);
        }
        return global_DateTime_Day_Names_[f[7] - 1].substring(0, 3) + ", " + f[2] + " "
            + global_DateTime_Month_Names_[f[1] - 1].substring(0, 3) + " " + String(f[0]).padStart(4, "0") + " "
            + global_DateTime_Two_(f[3]) + ":" + global_DateTime_Two_(f[4]) + ":" + global_DateTime_Two_(f[5]) + " " + zone;
    };

    // yyyy-MM-dd, as Java's ISO_LOCAL_DATE.
    this.DateToText = function () {
        return this.Date_Text_(this.Fields_());
    };

    this.TimeToText = function () {
        return this.Time_Text_(this.Fields_());
    };

    // English names (Java uses the machine's language).
    this.GetMonthName = function () {
        return global_DateTime_Month_Names_[this.Fields_()[1] - 1];
    };

    this.GetShortMonthName = function () {
        return global_DateTime_Month_Names_[this.Fields_()[1] - 1].substring(0, 3);
    };

    this.GetDayOfWeekName = function () {
        return global_DateTime_Day_Names_[this.Fields_()[7] - 1];
    };

    this.GetShortDayofWeekName = function () {
        return global_DateTime_Day_Names_[this.Fields_()[7] - 1].substring(0, 3);
    };

    // Parsing. A parsed date or time has no time zone: it is that clock time
    // in this DateTime's zone, as in the Java plugin.

    // ISO 8601 date and time, with a T (or a space, as the Java plugin) between them.
    this.ParseTimestamp$quorum_text = function (value) {
        var text = value.split(" ").join("T");
        var cut = text.indexOf("T");
        if (cut < 0) {
            cut = text.indexOf("t");
        }
        var date = cut < 0 ? null : global_DateTime_Read_Date_(text.substring(0, cut));
        var time = cut < 0 ? null : global_DateTime_Read_Time_(text.substring(cut + 1));
        if (date === null || time === null) {
            throw global_DateTime_Error_("Could not parse '" + value + "' as a timestamp.");
        }
        this.SetLocal_(date[0], date[1], date[2], time[0], time[1], time[2], time[3], null);
    };

    // ISO 8601 date: 2015-03-14, at the start of that day.
    this.ParseDate$quorum_text = function (value) {
        var date = global_DateTime_Read_Date_(value);
        if (date === null) {
            throw global_DateTime_Error_("Could not parse '" + value + "' as a date.");
        }
        this.SetLocal_(date[0], date[1], date[2], 0, 0, 0, 0, null);
    };

    // month/day/year, as Java's pattern "M/d/[uuuu][uu]": a four digit year,
    // or a two digit year in the 2000s. As Java's default (smart) reading, a
    // day past the end of its month (2/30) becomes the last day of it.
    this.ParseAmericanDate$quorum_text = function (value) {
        var parts = /^(\d+)\/(\d+)\/(\d{4}|\d{2})$/.exec(value);
        var month = parts === null ? 0 : Number(parts[1]);
        var day = parts === null ? 0 : Number(parts[2]);
        if (parts === null || month < 1 || month > 12 || day < 1 || day > 31) {
            throw global_DateTime_Error_("Could not parse '" + value + "' as a date.");
        }
        var year = Number(parts[3]);
        if (parts[3].length === 2) {
            year = year + 2000;
        }
        day = Math.min(day, global_DateTime_Month_Length_(year, month));
        this.SetLocal_(year, month, day, 0, 0, 0, 0, null);
    };

    // ISO 8601 time: 13:45:30. With keepDate, on the date already held;
    // otherwise on today's date.
    this.ParseTime$quorum_text$quorum_boolean = function (value, keepDate) {
        var time = global_DateTime_Read_Time_(value);
        if (time === null) {
            throw global_DateTime_Error_("Could not parse '" + value + "' as a time.");
        }
        if (!keepDate) {
            this.time = Date.now();
        }
        var f = this.Fields_();
        this.SetLocal_(f[0], f[1], f[2], time[0], time[1], time[2], time[3], null);
    };

    // A DateTime that follows the clock compares as the current time; two
    // that follow it are neither before nor after each other (Java plugin).
    this.Compare_ = function (value) {
        var other = value.plugin_;
        if (this.now && other.now) {
            return 0;
        }
        var mine = this.now ? Date.now() : this.time;
        var theirs = other.now ? Date.now() : other.time;
        return mine < theirs ? -1 : (mine > theirs ? 1 : 0);
    };

    // The names the compiled library calls (they had a trailing "_", so
    // IsBefore, IsAfter and with them Compare and sorting failed on the web).
    this.IsBefore$quorum_Libraries_System_DateTime = function (value) {
        return this.Compare_(value) < 0;
    };

    this.IsAfter$quorum_Libraries_System_DateTime = function (value) {
        return this.Compare_(value) > 0;
    };

    // Math, as Java's ZonedDateTime: years, months, weeks and days change
    // the date of the clock time (a day past the end of a month becomes its
    // last day); hours, minutes and seconds move the moment.

    this.AddMonths_ = function (months) {
        var f = this.Fields_();
        var total = f[0] * 12 + (f[1] - 1) + months;
        var year = Math.floor(total / 12);
        var month = total - year * 12 + 1;
        var day = Math.min(f[2], global_DateTime_Month_Length_(year, month));
        this.SetLocal_(year, month, day, f[3], f[4], f[5], f[6], f[9]);
    };

    this.AddDays_ = function (days) {
        var f = this.Fields_();
        var date = global_DateTime_Civil_From_Days_(f[8] + days);
        this.SetLocal_(date[0], date[1], date[2], f[3], f[4], f[5], f[6], f[9]);
    };

    this.AddMilliseconds_ = function (milliseconds) {
        this.Update_();
        this.now = false;
        this.time = this.time + milliseconds;
    };

    this.AddYears$quorum_integer = function (value) {
        this.AddMonths_(value * 12);
    };

    this.AddMonths$quorum_integer = function (value) {
        this.AddMonths_(value);
    };

    this.AddWeeks$quorum_integer = function (value) {
        this.AddDays_(value * 7);
    };

    this.AddDays$quorum_integer = function (value) {
        this.AddDays_(value);
    };

    this.AddHours$quorum_integer = function (value) {
        this.AddMilliseconds_(value * 3600000);
    };

    this.AddMinutes$quorum_integer = function (value) {
        this.AddMilliseconds_(value * 60000);
    };

    this.AddSeconds$quorum_integer = function (value) {
        this.AddMilliseconds_(value * 1000);
    };

    this.SubtractYears$quorum_integer = function (value) {
        this.AddMonths_(-value * 12);
    };

    this.SubtractMonths$quorum_integer = function (value) {
        this.AddMonths_(-value);
    };

    this.SubtractWeeks$quorum_integer = function (value) {
        this.AddDays_(-value * 7);
    };

    this.SubtractDays$quorum_integer = function (value) {
        this.AddDays_(-value);
    };

    this.SubtractHours$quorum_integer = function (value) {
        this.AddMilliseconds_(-value * 3600000);
    };

    this.SubtractMinutes$quorum_integer = function (value) {
        this.AddMilliseconds_(-value * 60000);
    };

    this.SubtractSeconds$quorum_integer = function (value) {
        this.AddMilliseconds_(-value * 1000);
    };
}
