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
	value = str(value)
	value = value.replace("e+", "E")
	return value
	
def Global_Cast_Integer_To_Number(value):
	return float(value)
	
def Global_Cast_Number_To_Integer(value):
	return int(value)
		
def  Global_GetValue_(value, type):
	if (value == None):
		if(type == "number" or type == "boolean" or type == "integer"):
			raise ValueError("Cannot get a value from the type: " + type)
		else:
			return None
	else:
		return value.GetValue()
		
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