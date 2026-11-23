globalSharedClasses = {}

def Global_Get_Shared_Class(key):
	return globalSharedClasses.get(key)

def Global_Add_Shared_Class(key, value):
	return globalSharedClasses.setdefault(key, value)

def Global_Remove_Shared_Class(key):
	globalSharedClasses.pop(key);

def global_Empty_Shared_Classes():
	globalSharedClasses.clear()
	
def Global_Cast_Text_To_Boolean(value):
	if value == "true":
			return True
	elif value == "false":
		return False
	else:
		raise Exception("Error: Cast Error: An error occurred while casting")
		
def Global_Cast_To_Text(value):
	return str(value)
	
def Global_Cast_Text_To_Integer(value):
	try:
		return int(value)
	except ValueError as e:
		raise quorum_Libraries_Language_Errors_CastError_()
	
def Global_Cast_Text_To_Number(value):
	try:
		return float(value)
	except ValueError as e:
		raise quorum_Libraries_Language_Errors_CastError_()

def Global_Cast_Boolean_To_Text(value):
	if value == True:
		return "true"
	else:
		return "false"

def Global_Cast_Boolean_To_Number(value):
	if value == True:
		return float(1.0)
	else:
		return float(0.0)

def Global_Cast_Boolean_To_Integer(value):
	if value == True:
		return int(1)
	else:
		return int(0)
		
def Global_Cast_Integer_To_Text(value):
	return str(value)
	
def Global_Cast_Number_To_Text(value):
	# A number as text, as Java's Double.toString: plain from 0.001 up to 10
	# million, otherwise d.dddE±n. repr gives the shortest digits that read
	# back as the same number, which Java (19 and later) also uses.
	value = float(value)
	if value != value:
		return "NaN"
	if value == float("inf"):
		return "Infinity"
	if value == float("-inf"):
		return "-Infinity"
	if value == 0:
		return "-0.0" if str(value).startswith("-") else "0.0"
	sign = "-" if value < 0 else ""
	size = abs(value)
	# Java prints the smallest double with two digits.
	if size == 5e-324:
		return sign + "4.9E-324"
	# find and slices, not partition, which some MicroPython builds lack.
	written = repr(size)
	marker = written.find("e")
	if marker >= 0:
		mantissa = written[:marker]
		exponent = int(written[marker + 1:])
	else:
		mantissa = written
		exponent = 0
	point = mantissa.find(".")
	if point >= 0:
		whole = mantissa[:point]
		fraction = mantissa[point + 1:]
	else:
		whole = mantissa
		fraction = ""
	if whole != "0":
		exponent = exponent + len(whole) - 1
	else:
		exponent = exponent - (len(fraction) - len(fraction.lstrip("0"))) - 1
	digits = (whole + fraction).strip("0")
	if 1e-3 <= size < 1e7:
		if exponent >= 0:
			if len(digits) < exponent + 1:
				digits = digits + "0" * (exponent + 1 - len(digits))
			rest = digits[exponent + 1:]
			return sign + digits[:exponent + 1] + "." + (rest if rest else "0")
		return sign + "0." + "0" * (-exponent - 1) + digits
	return sign + digits[0] + "." + (digits[1:] if len(digits) > 1 else "0") + "E" + str(exponent)
	
def Global_Cast_Integer_To_Number(value):
	return float(value)
	
def Global_Cast_Number_To_Integer(value):
	return int(value)
		
def Global_GetValue_(value, type):
	if (value == None):
		if(type == "number" or type == "boolean" or type == "integer"):
			raise ValueError("Cannot get a value from the type: " + type)
		else:
			return None
	else:
		return value.GetValue()
		
def Global_Cast_To_Int(value):
	return int(value)

def Global_Wrap_Int(value):
	# The 32-bit signed integer Java's int arithmetic would give.
	value &= 0xFFFFFFFF
	return value - 0x100000000 if value & 0x80000000 else value
		

# Integer division and remainder, as Java's int: by zero is a
# DivideByZeroError, the quotient is cut toward zero (Python's // rounds
# down) and wrapped to 32 bits, and a remainder takes the dividend's sign
# (Python's % takes the divisor's). abs, // and % only, for MicroPython.
def Global_Integer_Divide(left, right):
	if right == 0:
		raise quorum_Libraries_Language_Errors_DivideByZeroError_()
	quotient = abs(left) // abs(right)
	if (left < 0) != (right < 0):
		quotient = -quotient
	return Global_Wrap_Int(quotient)

def Global_Integer_Modulo(left, right):
	if right == 0:
		raise quorum_Libraries_Language_Errors_DivideByZeroError_()
	remainder = abs(left) % abs(right)
	return -remainder if left < 0 else remainder

# Number division and remainder, as Java's double: by zero gives Infinity,
# -Infinity or NaN (Python raises), with -0.0 counting as negative, and a
# remainder takes the dividend's sign (math.fmod, which MicroPython has).
def Global_Number_Divide(left, right):
	try:
		return left / right
	except ZeroDivisionError:
		if left != left or left == 0:
			return float("nan")
		negative = (left < 0) != str(float(right)).startswith("-")
		return float("-inf") if negative else float("inf")

def Global_Number_Modulo(left, right):
	import math
	try:
		return math.fmod(left, right)
	except ValueError:
		return float("nan")

def Global_CheckCast(from_, to):
	if (from_ is None or from_ == None):
		return from_
	names = from_.inheritance_;
	for element in names:
		if(element == to):
			return from_
    
	raise quorum_Libraries_Language_Errors_CastError_()
	
def Exception_(e):
	val = e
	if isinstance(e, quorum_Libraries_Language_Errors_Error_):
		return e
	elif isinstance(e, AttributeError):
		if "NoneType" in str(e): 
			val = quorum_Libraries_Language_Errors_UndefinedObjectError_()
		else:
			val = quorum_Libraries_Language_Errors_Error_()
	else:
		val = quorum_Libraries_Language_Errors_Error_()
	
	message = str(e)
	val.Set_Libraries_Language_Errors_Error__errorMessage_(message)
	
	return val