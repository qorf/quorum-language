import time

# The Python DateTime plugin. It follows the Java plugin: a DateTime holds a
# moment (whole milliseconds since 1970-01-01 UTC) and a time zone, either the
# machine's own (the default) or a fixed offset in whole hours (SetTimeZone).
# Until a time is set (SetEpochTime, a Parse action or an Add or Subtract
# action), the DateTime follows the clock. Dates come from integer arithmetic
# on the epoch milliseconds, so only time.time (or time.time_ns), time.gmtime
# and time.localtime are used, all of which MicroPython has. The machine's
# offset comes from time.localtime; on MicroPython, which has no time zones,
# it is normally 0 (UTC).

_DateTime_Day_Milliseconds = 86400000
_DateTime_Month_Names = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
_DateTime_Day_Names = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
# Seconds from 1970 to the time module's epoch: some MicroPython ports count
# from 2000-01-01. None until it is first needed.
_DateTime_Epoch_Shift = None

def _DateTime_Shift():
	global _DateTime_Epoch_Shift
	if _DateTime_Epoch_Shift is None:
		_DateTime_Epoch_Shift = 0
		try:
			if time.gmtime(0)[0] == 2000:
				_DateTime_Epoch_Shift = 946684800
		except Exception:
			pass
	return _DateTime_Epoch_Shift

# The current time in whole milliseconds since 1970-01-01 UTC.
def _DateTime_Now():
	if hasattr(time, "time_ns"):
		value = time.time_ns() // 1000000
	else:
		value = int(time.time() * 1000)
	return value + _DateTime_Shift() * 1000

# Days from 1970-01-01 to a year, month (1 to 12) and day.
def _DateTime_Days_From_Civil(year, month, day):
	y = year
	if month <= 2:
		y = year - 1
	era = y // 400
	yearOfEra = y - era * 400
	if month > 2:
		m = month - 3
	else:
		m = month + 9
	dayOfYear = (153 * m + 2) // 5 + day - 1
	dayOfEra = yearOfEra * 365 + yearOfEra // 4 - yearOfEra // 100 + dayOfYear
	return era * 146097 + dayOfEra - 719468

# The [year, month (1 to 12), day] of a count of days from 1970-01-01.
def _DateTime_Civil_From_Days(days):
	z = days + 719468
	era = z // 146097
	dayOfEra = z - era * 146097
	yearOfEra = (dayOfEra - dayOfEra // 1460 + dayOfEra // 36524 - dayOfEra // 146096) // 365
	dayOfYear = dayOfEra - (365 * yearOfEra + yearOfEra // 4 - yearOfEra // 100)
	mp = (5 * dayOfYear + 2) // 153
	day = dayOfYear - (153 * mp + 2) // 5 + 1
	if mp < 10:
		month = mp + 3
	else:
		month = mp - 9
	year = yearOfEra + era * 400
	if month <= 2:
		year = year + 1
	return [year, month, day]

def _DateTime_Month_Length(year, month):
	if month == 2:
		if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0:
			return 29
		return 28
	if month == 4 or month == 6 or month == 9 or month == 11:
		return 30
	return 31

# A whole number with zeros in front, to at least size digits.
def _DateTime_Pad(value, size):
	result = str(value)
	while len(result) < size:
		result = "0" + result
	return result

# A year as ISO 8601 writes it: four digits, with a sign beyond 9999 or below 0.
def _DateTime_Year_Text(year):
	if year < 0:
		return "-" + _DateTime_Pad(-year, 4)
	if year > 9999:
		return "+" + str(year)
	return _DateTime_Pad(year, 4)

def _DateTime_Error(message):
	error = quorum_Libraries_Language_Errors_Error_()
	error.SetErrorMessage__quorum_text(message)
	return error

# The value of text made only of ASCII digits, or -1.
def _DateTime_Digits(text):
	if len(text) == 0:
		return -1
	result = 0
	for character in text:
		code = ord(character)
		if code < 48 or code > 57:
			return -1
		result = result * 10 + code - 48
	return result

# Reads an ISO 8601 date (2024-03-15) as [year, month, day], or None. As
# Java's ISO_LOCAL_DATE (strict), the date must exist.
def _DateTime_Read_Date(value):
	sign = ""
	if len(value) > 0 and (value[0] == "+" or value[0] == "-"):
		sign = value[0]
		value = value[1:]
	parts = value.split("-")
	if len(parts) != 3 or len(parts[1]) != 2 or len(parts[2]) != 2:
		return None
	size = len(parts[0])
	if size < 4 or size > 10 or (sign == "" and size != 4) or (sign == "+" and size == 4):
		return None
	year = _DateTime_Digits(parts[0])
	month = _DateTime_Digits(parts[1])
	day = _DateTime_Digits(parts[2])
	if year < 0 or month < 1 or month > 12 or day < 1:
		return None
	if sign == "-":
		year = -year
	if day > _DateTime_Month_Length(year, month):
		return None
	return [year, month, day]

# Reads an ISO 8601 time (13:45, 13:45:30 or 13:45:30.5) as
# [hour, minute, second, millisecond], or None.
def _DateTime_Read_Time(value):
	fraction = ""
	point = value.find(".")
	if point >= 0:
		fraction = value[point + 1:]
		value = value[:point]
		if len(fraction) < 1 or len(fraction) > 9 or _DateTime_Digits(fraction) < 0:
			return None
	parts = value.split(":")
	if len(parts) < 2 or len(parts) > 3 or (point >= 0 and len(parts) != 3):
		return None
	for part in parts:
		if len(part) != 2:
			return None
	hour = _DateTime_Digits(parts[0])
	minute = _DateTime_Digits(parts[1])
	second = 0
	if len(parts) == 3:
		second = _DateTime_Digits(parts[2])
	if hour < 0 or hour > 23 or minute < 0 or minute > 59 or second < 0 or second > 59:
		return None
	millisecond = 0
	if fraction != "":
		millisecond = _DateTime_Digits((fraction + "00")[:3])
	return [hour, minute, second, millisecond]

class plugins_quorum_Libraries_System_DateTime_():
	def __init__(self):
		self.time = _DateTime_Now()
		self.now = True
		# None for the machine's time zone, or a fixed offset in hours.
		self.zone = None

	# The offset from UTC, in milliseconds, at a moment. For the machine's
	# zone, the clock time localtime gives for that second, less the second.
	def Offset_(self, moment):
		if self.zone is not None:
			return self.zone * 3600000
		seconds = moment // 1000
		try:
			local = time.localtime(seconds - _DateTime_Shift())
		except Exception:
			try:
				seconds = _DateTime_Now() // 1000
				local = time.localtime(seconds - _DateTime_Shift())
			except Exception:
				return 0
		days = _DateTime_Days_From_Civil(local[0], local[1], local[2])
		localSeconds = days * 86400 + local[3] * 3600 + local[4] * 60 + local[5]
		return (localSeconds - seconds) * 1000

	# The moment for a clock time in this time zone, given in milliseconds
	# as if it were UTC. As Java: in a daylight saving gap the time moves
	# later by the gap; in an overlap the earlier offset is used, unless
	# keep (the offset before an Add or Subtract) is the other one.
	def Resolve_(self, local, keep):
		before = self.Offset_(local - _DateTime_Day_Milliseconds)
		after = self.Offset_(local + _DateTime_Day_Milliseconds)
		beforeValid = self.Offset_(local - before) == before
		afterValid = self.Offset_(local - after) == after
		if beforeValid and afterValid and keep == after:
			return local - after
		if beforeValid:
			return local - before
		if afterValid:
			return local - after
		return local - before

	def Update_(self):
		if self.now:
			self.time = _DateTime_Now()

	# [year, month, day, hour, minute, second, millisecond, day of week
	# (1 is Monday), days since 1970-01-01, offset] in this time zone.
	def Fields_(self):
		self.Update_()
		offset = self.Offset_(self.time)
		local = self.time + offset
		days = local // _DateTime_Day_Milliseconds
		rest = local - days * _DateTime_Day_Milliseconds
		date = _DateTime_Civil_From_Days(days)
		weekday = (days + 3) % 7 + 1
		return [date[0], date[1], date[2], rest // 3600000, (rest // 60000) % 60, (rest // 1000) % 60, rest % 1000, weekday, days, offset]

	# Sets the time to a clock time in this time zone.
	def SetLocal_(self, year, month, day, hour, minute, second, millisecond, keep):
		local = _DateTime_Days_From_Civil(year, month, day) * _DateTime_Day_Milliseconds + hour * 3600000 + minute * 60000 + second * 1000 + millisecond
		self.time = self.Resolve_(local, keep)
		self.now = False

	def GetYear(self):
		return self.Fields_()[0]

	def GetMonth(self):
		return self.Fields_()[1]

	def GetDayOfMonth(self):
		return self.Fields_()[2]

	# ISO 8601: 1 is Monday and 7 is Sunday.
	def GetDayOfWeek(self):
		return self.Fields_()[7]

	def GetHour(self):
		return self.Fields_()[3]

	def GetMinute(self):
		return self.Fields_()[4]

	def GetSecond(self):
		return self.Fields_()[5]

	# Whole hours from UTC at the current time, as the Java plugin.
	def GetTimeZone(self):
		offset = self.Offset_(_DateTime_Now())
		if offset < 0:
			return -((-offset) // 3600000)
		return offset // 3600000

	# A fixed offset has no daylight saving time. In the machine's time
	# zone, the standard offset is the smaller of January's and July's.
	def IsDaylightSavings(self):
		if self.zone is not None:
			return False
		year = self.Fields_()[0]
		january = self.Offset_(_DateTime_Days_From_Civil(year, 1, 1) * _DateTime_Day_Milliseconds)
		july = self.Offset_(_DateTime_Days_From_Civil(year, 7, 1) * _DateTime_Day_Milliseconds)
		return self.Offset_(self.time) > min(january, july)

	def GetEpochTime(self):
		self.Update_()
		return float(self.time)

	def SetEpochTime__quorum_number(self, value):
		self.now = False
		self.time = int(value)

	# A fixed offset in whole hours, -18 to 18 as Java allows. A time
	# already set stays the same moment, shown in the new zone.
	def SetTimeZone__quorum_integer(self, value):
		if value < -18 or value > 18:
			raise _DateTime_Error("The time zone offset " + str(value) + " is not from -18 to 18.")
		self.zone = value

	def Now(self):
		return float(_DateTime_Now())

	# ISO 8601 time, as Java's ISO_LOCAL_TIME: 09:26:53, with a fraction
	# of a second only when it is not 0, without trailing zeros.
	def Time_Text_(self, f):
		result = _DateTime_Pad(f[3], 2) + ":" + _DateTime_Pad(f[4], 2) + ":" + _DateTime_Pad(f[5], 2)
		if f[6] != 0:
			fraction = _DateTime_Pad(f[6], 3)
			while fraction.endswith("0"):
				fraction = fraction[:len(fraction) - 1]
			result = result + "." + fraction
		return result

	def Date_Text_(self, f):
		return _DateTime_Year_Text(f[0]) + "-" + _DateTime_Pad(f[1], 2) + "-" + _DateTime_Pad(f[2], 2)

	# ISO 8601 date and time, as Java's ISO_LOCAL_DATE_TIME: 2015-03-14T09:26:53.
	def GetTimestamp(self):
		f = self.Fields_()
		return self.Date_Text_(f) + "T" + self.Time_Text_(f)

	# RFC 1123, as Java's RFC_1123_DATE_TIME: Fri, 15 Mar 2024 13:45:30 -0700
	# (GMT for an offset of 0).
	def ToText(self):
		f = self.Fields_()
		zone = "GMT"
		minutes = abs(f[9]) // 60000
		if minutes != 0:
			sign = "+"
			if f[9] < 0:
				sign = "-"
			zone = sign + _DateTime_Pad(minutes // 60, 2) + _DateTime_Pad(minutes % 60, 2)
		return _DateTime_Day_Names[f[7] - 1][:3] + ", " + str(f[2]) + " " + _DateTime_Month_Names[f[1] - 1][:3] + " " + _DateTime_Pad(f[0], 4) + " " + _DateTime_Pad(f[3], 2) + ":" + _DateTime_Pad(f[4], 2) + ":" + _DateTime_Pad(f[5], 2) + " " + zone

	# yyyy-MM-dd, as Java's ISO_LOCAL_DATE.
	def DateToText(self):
		return self.Date_Text_(self.Fields_())

	def TimeToText(self):
		return self.Time_Text_(self.Fields_())

	# English names (Java uses the machine's language).
	def GetMonthName(self):
		return _DateTime_Month_Names[self.Fields_()[1] - 1]

	def GetShortMonthName(self):
		return _DateTime_Month_Names[self.Fields_()[1] - 1][:3]

	def GetDayOfWeekName(self):
		return _DateTime_Day_Names[self.Fields_()[7] - 1]

	def GetShortDayofWeekName(self):
		return _DateTime_Day_Names[self.Fields_()[7] - 1][:3]

	# Parsing. A parsed date or time has no time zone: it is that clock time
	# in this DateTime's zone, as in the Java plugin.

	# ISO 8601 date and time, with a T (or a space, as the Java plugin) between them.
	def ParseTimestamp__quorum_text(self, value):
		text = value.replace(" ", "T")
		cut = text.find("T")
		if cut < 0:
			cut = text.find("t")
		date = None
		clock = None
		if cut >= 0:
			date = _DateTime_Read_Date(text[:cut])
			clock = _DateTime_Read_Time(text[cut + 1:])
		if date is None or clock is None:
			raise _DateTime_Error("Could not parse '" + value + "' as a timestamp.")
		self.SetLocal_(date[0], date[1], date[2], clock[0], clock[1], clock[2], clock[3], None)

	# ISO 8601 date: 2015-03-14, at the start of that day.
	def ParseDate__quorum_text(self, value):
		date = _DateTime_Read_Date(value)
		if date is None:
			raise _DateTime_Error("Could not parse '" + value + "' as a date.")
		self.SetLocal_(date[0], date[1], date[2], 0, 0, 0, 0, None)

	# month/day/year, as Java's pattern "M/d/[uuuu][uu]": a four digit year,
	# or a two digit year in the 2000s. As Java's default (smart) reading, a
	# day past the end of its month (2/30) becomes the last day of it.
	def ParseAmericanDate__quorum_text(self, value):
		parts = value.split("/")
		month = -1
		day = -1
		year = -1
		if len(parts) == 3 and (len(parts[2]) == 4 or len(parts[2]) == 2):
			month = _DateTime_Digits(parts[0])
			day = _DateTime_Digits(parts[1])
			year = _DateTime_Digits(parts[2])
		if month < 1 or month > 12 or day < 1 or day > 31 or year < 0:
			raise _DateTime_Error("Could not parse '" + value + "' as a date.")
		if len(parts[2]) == 2:
			year = year + 2000
		day = min(day, _DateTime_Month_Length(year, month))
		self.SetLocal_(year, month, day, 0, 0, 0, 0, None)

	# ISO 8601 time: 13:45:30. With keepDate, on the date already held;
	# otherwise on today's date.
	def ParseTime__quorum_text__quorum_boolean(self, value, keepDate):
		clock = _DateTime_Read_Time(value)
		if clock is None:
			raise _DateTime_Error("Could not parse '" + value + "' as a time.")
		if not keepDate:
			self.time = _DateTime_Now()
		f = self.Fields_()
		self.SetLocal_(f[0], f[1], f[2], clock[0], clock[1], clock[2], clock[3], None)

	# A DateTime that follows the clock compares as the current time; two
	# that follow it are neither before nor after each other (Java plugin).
	def Compare_(self, value):
		other = value.plugin_
		if self.now and other.now:
			return 0
		mine = self.time
		if self.now:
			mine = _DateTime_Now()
		theirs = other.time
		if other.now:
			theirs = _DateTime_Now()
		if mine < theirs:
			return -1
		if mine > theirs:
			return 1
		return 0

	def IsBefore__quorum_Libraries_System_DateTime(self, value):
		return self.Compare_(value) < 0

	def IsAfter__quorum_Libraries_System_DateTime(self, value):
		return self.Compare_(value) > 0

	# Math, as Java's ZonedDateTime: years, months, weeks and days change
	# the date of the clock time (a day past the end of a month becomes its
	# last day); hours, minutes and seconds move the moment.

	def AddMonths_(self, months):
		f = self.Fields_()
		total = f[0] * 12 + (f[1] - 1) + months
		year = total // 12
		month = total - year * 12 + 1
		day = min(f[2], _DateTime_Month_Length(year, month))
		self.SetLocal_(year, month, day, f[3], f[4], f[5], f[6], f[9])

	def AddDays_(self, days):
		f = self.Fields_()
		date = _DateTime_Civil_From_Days(f[8] + days)
		self.SetLocal_(date[0], date[1], date[2], f[3], f[4], f[5], f[6], f[9])

	def AddMilliseconds_(self, milliseconds):
		self.Update_()
		self.now = False
		self.time = self.time + milliseconds

	def AddYears__quorum_integer(self, value):
		self.AddMonths_(value * 12)

	def AddMonths__quorum_integer(self, value):
		self.AddMonths_(value)

	def AddWeeks__quorum_integer(self, value):
		self.AddDays_(value * 7)

	def AddDays__quorum_integer(self, value):
		self.AddDays_(value)

	def AddHours__quorum_integer(self, value):
		self.AddMilliseconds_(value * 3600000)

	def AddMinutes__quorum_integer(self, value):
		self.AddMilliseconds_(value * 60000)

	def AddSeconds__quorum_integer(self, value):
		self.AddMilliseconds_(value * 1000)

	def SubtractYears__quorum_integer(self, value):
		self.AddMonths_(-value * 12)

	def SubtractMonths__quorum_integer(self, value):
		self.AddMonths_(-value)

	def SubtractWeeks__quorum_integer(self, value):
		self.AddDays_(-value * 7)

	def SubtractDays__quorum_integer(self, value):
		self.AddDays_(-value)

	def SubtractHours__quorum_integer(self, value):
		self.AddMilliseconds_(-value * 3600000)

	def SubtractMinutes__quorum_integer(self, value):
		self.AddMilliseconds_(-value * 60000)

	def SubtractSeconds__quorum_integer(self, value):
		self.AddMilliseconds_(-value * 1000)
