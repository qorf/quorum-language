def Text_(value):
	val = quorum_Libraries_Language_Types_Text_()
	val.SetValue__quorum_text(value)
	return val


def _Text_Location_Error(index):
	# The error Text raises for a position outside the text, as Text.quorum does.
	error = quorum_Libraries_Language_Errors_InvalidLocationError_()
	error.SetErrorMessage__quorum_text("Text index out of range, " + str(index))
	return error

def _Text_Parse_Error(kind):
	error = quorum_Libraries_Language_Errors_ParseError_()
	error.SetErrorMessage__quorum_text("Parse Error: The text value could not be converted to " + kind + ".")
	return error

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
		# As Java's Integer.parseInt: an optional sign and ASCII digits only,
		# within the integer range (int() also allows spaces and 1_000).
		import re
		if re.fullmatch(r"[+-]?[0-9]+", self.text) is None:
			raise _Text_Parse_Error("an integer")
		value = int(self.text)
		if value < -2147483648 or value > 2147483647:
			raise _Text_Parse_Error("an integer")
		return value

	def ParseNumber(self):
		# As Java's Double.parseDouble: surrounding spaces are ignored; a sign,
		# digits with an optional point and exponent, NaN or Infinity, and an
		# optional f/F/d/D suffix (float() also allows 1_000, inf and nan).
		import re
		text = self.text.strip("".join(chr(c) for c in range(33)))
		if re.fullmatch(r"[+-]?(NaN|Infinity|(([0-9]+\.?[0-9]*|\.[0-9]+)([eE][+-]?[0-9]+)?)[fFdD]?)", text) is None:
			raise _Text_Parse_Error("a number")
		text = text.rstrip("fFdD")
		return float(text.replace("Infinity", "inf").replace("NaN", "nan"))

	def ParseBoolean(self):
		# As Java's Boolean.parseBoolean: "true" in any letter case.
		return self.text.lower() == "true"
			
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
		# Fails outside the text, as Java's charAt (Python indexes from the end
		# for negative values).
		if value < 0 or value >= len(self.text):
			raise _Text_Location_Error(value)
		return ord(self.text[value])

	def ContainsNative__quorum_text__quorum_text(self, left, right):
		return right in left

	def EndsWithNative__quorum_text__quorum_text(self, left, right):
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
		# Java's String.hashCode, kept to 32 bits, as a signed integer.
		h = 0
		for character in self.text:
			h = (31 * h + ord(character)) & 0xFFFFFFFF
		return h - 0x100000000 if h & 0x80000000 else h

	def GetSubtext__quorum_integer__quorum_integer(self, startIndex, endIndex):
		# Java's substring rules (Python slices never fail).
		if startIndex < 0:
			raise _Text_Location_Error(startIndex)
		if endIndex > len(self.text) or startIndex > endIndex:
			raise _Text_Location_Error(endIndex)
		return self.text[startIndex:endIndex]

	def Split__quorum_text__quorum_boolean(self, delimiter, include):
		# As Java's PrimitiveSplit(self, delimiter, keep), which splits on the
		# literal delimiter: with include, every piece; without, trailing empty
		# pieces are dropped. Empty text, or no match, gives the whole text.
		text = self.text
		if len(text) == 0:
			pieces = [""]
		elif delimiter == "":
			pieces = list(text)
			if include:
				pieces.append("")
		elif delimiter not in text:
			pieces = [text]
		else:
			pieces = text.split(delimiter)
			if not include:
				while len(pieces) > 0 and pieces[-1] == "":
					pieces.pop()
		array = quorum_Libraries_Containers_Array_()
		for piece in pieces:
			array.Add__quorum_Libraries_Language_Object(Text_(piece))
		return array
