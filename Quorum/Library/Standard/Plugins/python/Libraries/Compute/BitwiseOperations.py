def _Bitwise_Signed32(value):
	# The low 32 bits as a signed integer, as Java's int.
	value = value & 0xFFFFFFFF
	return value - 0x100000000 if value & 0x80000000 else value

class plugins_quorum_Libraries_Compute_BitwiseOperations_():
	def __init__(self):
		self.value = 0
		
	def And__quorum_integer__quorum_integer(self, left, right):
		return left & right
    
	def Or__quorum_integer__quorum_integer(self, left, right):
		return left | right
    
	def ExclusiveOr__quorum_integer__quorum_integer(self, left, right):
		return left ^ right
    
	def Negate__quorum_integer(self, value):
		return ~value
    
	# Java uses only the low 5 bits of the shift amount.
	def ShiftLeft__quorum_integer__quorum_integer(self, value, amount):
		return _Bitwise_Signed32(value << (amount & 31))
    
	def ShiftRight__quorum_integer__quorum_integer(self, value, amount):
		return value >> (amount & 31)
    
	def ShiftRightPositive__quorum_integer__quorum_integer(self, value, amount):
		return _Bitwise_Signed32((value & 0xFFFFFFFF) >> (amount & 31))
