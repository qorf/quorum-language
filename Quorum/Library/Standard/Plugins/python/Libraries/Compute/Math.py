import math

# Java's Math gives NaN or an infinity where Python's math raises an error;
# these helpers do as Java does.
def _Math_Nan_On_Error(function, value):
	try:
		return function(value)
	except (ValueError, OverflowError):
		return float("nan")

def _Math_Odd_Integer(power):
	return math.isfinite(power) and power == int(power) and int(power) % 2 == 1

class plugins_quorum_Libraries_Compute_Math_():
	def Floor__quorum_number (self, value):
		return math.floor(value)


	def Ceiling__quorum_number (self, value):
		return math.ceil(value)


	def Sine__quorum_number (self, value):
		return math.sin(value)


	def Cosine__quorum_number (self, value):
		return math.cos(value)


	def Tangent__quorum_number (self, value):
		return math.tan(value)


	def HyperbolicSine__quorum_number (self, value):
		return math.sinh(value)


	def HyperbolicCosine__quorum_number (self, value):
		return math.cosh(value)


	def HyperbolicTangent__quorum_number (self, value):
		return math.tanh(value)


	def InverseSine__quorum_number (self, value):
		return _Math_Nan_On_Error(math.asin, value)


	def InverseCosine__quorum_number (self, value):
		return _Math_Nan_On_Error(math.acos, value)


	def InverseTangent__quorum_number (self, value):
		return math.atan(value)


	def InverseTangent__quorum_number__quorum_number (self, x, y):
		return math.atan2(y, x)


	def InverseHyperbolicSine__quorum_number (self, value):
		return math.asinh(value)


	def InverseHyperbolicCosine__quorum_number (self, value):
		return math.acosh(value)


	def InverseHyperbolicTangent__quorum_number (self, value):
		return math.atanh(value)


	def Logarithm__quorum_number (self, value):
		# As Java: -Infinity for 0, NaN below 0.
		if value == 0:
			return float("-inf")
		return _Math_Nan_On_Error(math.log10, value)


	def NaturalLogarithm__quorum_number (self, value):
		if value == 0:
			return float("-inf")
		return _Math_Nan_On_Error(math.log, value)


	def SquareRoot__quorum_number (self, value):
		return _Math_Nan_On_Error(math.sqrt, value)


	def Round__quorum_number (self, value):
		# As Java's Math.round: halves round up (Python's round goes to the
		# even neighbor), NaN gives 0, and the result is a whole number.
		if value != value:
			return 0.0
		if math.isinf(value):
			return math.copysign(9.223372036854776e18, value)
		below = math.floor(value)
		if value - below >= 0.5:
			return float(below + 1)
		return float(below)


	def RoundToNearestInteger__quorum_number (self, value):
		# A midpoint goes to the even integer, as Java's Math.rint. Written out,
		# since round() on MicroPython may not round halves the same way.
		if value != value or math.isinf(value):
			return value
		below = math.floor(value)
		difference = value - below
		if difference > 0.5 or (difference == 0.5 and below % 2 == 1):
			result = float(below + 1)
		else:
			result = float(below)
		# a zero keeps the value's sign, as Java's: rint(-0.5) is -0.0
		return math.copysign(result, value) if result == 0 else result


	def RaiseToPower__quorum_number__quorum_number (self, value, power):
		# As Java's Math.pow: an infinity on overflow or for 0 to a negative
		# power, and NaN for a negative number to a fraction.
		try:
			return math.pow(value, power)
		except OverflowError:
			if value < 0 and _Math_Odd_Integer(power):
				return float("-inf")
			return float("inf")
		except ValueError:
			if value == 0:
				if math.copysign(1.0, value) < 0 and _Math_Odd_Integer(power):
					return float("-inf")
				return float("inf")
			return float("nan")


	def DegreesToRadians__quorum_number (self, degrees):
		return degrees * math.pi / 180


	def RadiansToDegrees__quorum_number (self, radians):
		return 180 * radians  / math.pi