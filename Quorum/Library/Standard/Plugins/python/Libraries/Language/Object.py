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