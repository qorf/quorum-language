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
    
	def ShiftLeft__quorum_integer__quorum_integer(self, value, amount):
		return value << amount
    
	def ShiftRight__quorum_integer__quorum_integer(self, value, amount):
		return value >> amount
    
	def ShiftRightPositive__quorum_integer__quorum_integer(self, value, amount):
		return (value % 0x100000000) >> amount
