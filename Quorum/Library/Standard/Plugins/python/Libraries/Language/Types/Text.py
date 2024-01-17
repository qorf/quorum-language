def Text_(value):
	val = quorum_Libraries_Language_Types_Text_()
	val.SetValue__quorum_text(value)
	return val


class plugins_quorum_Libraries_Language_Types_Text_:
	def __init__(self, value = ""):
		self.text = value

	def SetValueNative__quorum_text(self, value):
		self.text = value;
		
	def GetCharacterNative__quorum_integer(self, index):
		return str(self.text[index])

	def GetSubstringNative__quorum_integer__quorum_integer (self, startIndex, endIndex):
		return self.text[startIndex:endIndex]

	def ParseInteger(self):
		return int(self.text)

	def ParseNumber(self):
		return float(self.text)

	def ParseBoolean(self):
		if self.text == "True" or self.text == "true":
			return true
		else:
			return false
			
	def GetLineFeed(self):
		return "\n"

	def GetCarriageReturn(self):
		return "\r"
		
	def GetTab(self):
		return "\t"

	def GetDoubleQuote(self):
		return '"'
		
	def GetUnicodeValue__quorum_integer(self, value):
		return ""

	def GetUnicodeInteger__quorum_integer(self, value):
		return ""

	def ContainsNative__quorum_text__quorum_text(self, left, right):
		return False

	def EndsWith__quorum_text(self, left, right):
		return False

	def StartsWithNative__quorum_text__quorum_text(self, left, right):
		return False
 
	def SplitIntoLines(self):
		return ""

	def IndexOfNative__quorum_text__quorum_text(self, left, right):
		return ""

	def IndexOf__quorum_text__quorum_integer(self, left, right, index):
		return ""

	def IsEmptyNative__quorum_text(self, left):
		return False

	def ReplaceNative__quorum_text__quorum_text__quorum_text(self, left, old, replacement):
		return ""

	def GetSubtextNative__quorum_text__quorum_integer(left, index):
		return ""

	def ToLowerCase(self):
		return self.text.lower()
 
	def ToUpperCase(self):
		return self.text.upper()

	def TrimNative__quorum_text(self, left):
		return ""

	def EqualsIgnoringCaseNative__quorum_text__quorum_text(self, left, right):
		return False
    
	def CompareInt__quorum_text__quorum_text__quorum_boolean(self, left, right, isIgnoringCase):
		return 0
    
	def GetSize(self):
		return self.text.len()

	def GetHashCode(self):
		return 0

	def GetSubtext__quorum_integer__quorum_integer(self, startIndex, endIndex):
		return self.text[startIndex, endIndex]
