import time

class plugins_quorum_Libraries_System_DateTime_():
	def __init__(self):
		self.currentTime = time.time()
		self.now = True

	def GetYear(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_year
		
	def GetMonth(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_mon

	def GetDayOfMonth(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_mday
    
	def GetDayOfWeek(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_wday
  
	def GetHour(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_hour
		
	def GetMinute(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_min
		
		
	def GetSecond(self):
		if(self.now):
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		return timeStruct.tm_sec
    
	def GetTimeZone(self):
		return 0 #Not Supported
	
	def IsDaylightSavings(self):
		if self.now :
			self.currentTime = time.time()
		timeStruct = time.gmtime()
		daylight = timeStruct.tm_isdst
		if daylight:
			return True
		else:
			return False

    
	def GetEpochTime(self):
		if(self.now):
			self.currentTime = time.time()
		return self.currentTime
		
    
	def SetEpochTime__quorum_number(self, value):
		self.now = False
		self.currentTime = value
    
	def SetTimeZone__quorum_integer(self, value):
    		pass
		
	def Now(self):
		return time.time()
    
	def GetTimestamp(self):
		return time.strftime("%Y-%m-%dT%H:%M:%S", time.gmtime(time.time()))

	def ToText(self):
		return self.GetTimestamp()
		
	def DateToText(self):
		return self.GetTimestamp()
		
	def TimeToText(self):
		return self.GetTimestamp()

	def GetMonthName(self):
		timeStruct = time.gmtime()
		monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
		return monthNames[timeStruct.tm_mon]
		
	def GetShortMonthName(self):
		timeStruct = time.gmtime()
		monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		return monthNames[timeStruct.tm_mon]


	def GetDayOfWeekName(self):
		timeStruct = time.gmtime()
		dayNames = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
		return monthNames[timeStruct.tm_wday]
		
	def GetShortDayOfWeekName(self):
		timeStruct = time.gmtime()
		dayNames = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
		return monthNames[timeStruct.tm_wday]
		
	def ParseTimestamp__quorum_text(self, value):
		pass #There is likely a way to do it, but not supported in this version

	def ParseDate__quorum_text(self, value):
		pass

	def ParseAmericanDate__quorum_text(self, value):
		pass

	def ParseTime__quorum_text(self, value, keepDate):
		pass

	def IsBefore__quorum_Libraries_System_DateTime_(self, value):
		return False

	def IsAfter__quorum_Libraries_System_DateTime_(self, value):
		return False

	#These are not supported in the current version
	def AddYears__quorum_integer(self, value):
		self.now = False

	def AddMonths__quorum_integer(self, value):
		self.now = False
		
	def AddWeeks__quorum_integer(self, value):
		self.now = False
		
	def AddDays__quorum_integer(self, value):
		self.now = False

	def AddHours__quorum_integer(self, value):
		self.now = False
		
	def AddMinutes__quorum_integer(self, value):
		self.now = False
		
	def AddSeconds__quorum_integer(self, value):
		self.now = False

	def SubtractYears__quorum_integer(self, value):
		self.now = False

	def SubtractMonths__quorum_integer(self, value):
		self.now = False
		
	def SubtractWeeks__quorum_integer(self, value):
		self.now = False
		
	def SubtractDays__quorum_integer(self, value):
		self.now = False

	def SubtractHours__quorum_integer(self, value):
		self.now = False
		
	def SubtractMinutes__quorum_integer(self, value):
		self.now = False
		
	def SubtractSeconds__quorum_integer(self, value):
		self.now = False
