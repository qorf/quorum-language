import time
import sys
try:
	import gc
except ImportError:
	gc = None

# SystemHelper in the Python back end. Works on CPython and MicroPython:
# Sleep uses time.sleep_ms where it exists (MicroPython) and time.sleep
# otherwise. Memory comes from gc.mem_free and gc.mem_alloc, which only
# MicroPython has; elsewhere it is 0. There is no text editor to open.
class plugins_quorum_Libraries_System_SystemHelper_():
	def Sleep__quorum_integer(self, milliseconds):
		if milliseconds <= 0:
			return
		if hasattr(time, "sleep_ms"):
			time.sleep_ms(milliseconds)
		else:
			time.sleep(milliseconds / 1000.0)

	def RequestGarbageCollection(self):
		if gc is not None:
			gc.collect()

	def GetFreeMemory(self):
		if gc is not None and hasattr(gc, "mem_free"):
			return gc.mem_free() + 0.0
		return 0.0

	def GetMaximumMemory(self):
		return self.GetTotalMemory()

	def GetTotalMemory(self):
		if gc is not None and hasattr(gc, "mem_free") and hasattr(gc, "mem_alloc"):
			return gc.mem_free() + gc.mem_alloc() + 0.0
		return 0.0

	def GetAvailableProcessors(self):
		return 1

	def GetThreadName(self):
		return "main"

	def OpenInDefaultTextEditor__quorum_Libraries_System_File(self, file):
		pass

	def Exit__quorum_integer(self, code):
		sys.exit(code)
