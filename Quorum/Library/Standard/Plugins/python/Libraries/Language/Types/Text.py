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
			return True
		else:
			return False
			
	def GetLineFeed(self):
		return "\n"

	def GetCarriageReturn(self):
		return "\r"
		
	def GetTab(self):
		return "\t"

	def GetDoubleQuote(self):
		return "\""
		
	def GetUnicodeValue__quorum_integer(self, value):
		return chr(value)

	def GetUnicodeInteger__quorum_integer(self, value):
		return ord(self.text[value])

	def ContainsNative__quorum_text__quorum_text(self, left, right):
		return right in left

	def EndsWith__quorum_text(self, left, right):
		return left.endswith(right)

	def StartsWithNative__quorum_text__quorum_text(self, left, right):
		return left.startswith(right)
 
	def SplitIntoLines(self):
		lines = self.text.splitlines()
		array = quorum_Libraries_Containers_Array_()
		for line in lines:
			array.Add__quorum_Libraries_Language_Object(Text_(line))
		return array

	def IndexOfNative__quorum_text__quorum_text(self, left, right):
		try:
			return left.index(right)
		except:
			return -1

	def IndexOfNative__quorum_text__quorum_text__quorum_integer(self, left, right, index):
		try:
			val = left[index:]
			
			result = val.find(right)
			if result == -1:
				return -1
			else:
				return result + index
		except:
			return -1

	def IsEmptyNative__quorum_text(self, left):
		return len(self.text) == 0

	def ReplaceNative__quorum_text__quorum_text__quorum_text(self, left, old, replacement):
		return self.text.replace(old, replacement)

	def GetSubtextNative__quorum_text__quorum_integer(self, left, index):
		return left[index:]

	def ToLowerCase(self):
		return self.text.lower()
 
	def ToUpperCase(self):
		return self.text.upper()

	def TrimNative__quorum_text(self, left):
		return left.strip()

	def EqualsIgnoringCaseNative__quorum_text__quorum_text(self, left, right):
		return left.lower() == right.lower()
    
	def CompareInt__quorum_text__quorum_text__quorum_boolean(self, left, right, isIgnoringCase):
		if isIgnoringCase:
			left = left.lower()
			right = right.lower()
			
		if left == right:
			return 0
		elif left < right:
			return -1
		else:
			return 1
    
	def GetSize(self):
		return len(self.text)

	def GetHashCode(self):
		return hash(self.text)

	def GetSubtext__quorum_integer__quorum_integer(self, startIndex, endIndex):
		return self.text[startIndex:endIndex+1]
