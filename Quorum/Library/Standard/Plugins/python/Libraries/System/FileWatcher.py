# FileWatcher in the Python back end: not supported yet. IsSupportedNative
# returns false, so FileWatcher:Watch raises an error in Quorum before
# WatchNative is reached. There are never changes.
class plugins_quorum_Libraries_System_FileWatcher_():
	def __init__(self):
		self.settleTime = 200

	def IsSupportedNative(self):
		return False

	def WatchNative__quorum_text__quorum_boolean(self, path, recursive):
		pass

	def UnwatchNative__quorum_text(self, path):
		pass

	def StopNative(self):
		pass

	def ExcludeFolderNameNative__quorum_text(self, name):
		pass

	def IgnoreChangesToNative__quorum_text__quorum_integer(self, path, milliseconds):
		pass

	def SetSettleTimeNative__quorum_integer(self, milliseconds):
		self.settleTime = max(0, milliseconds)

	def GetSettleTimeNative(self):
		return self.settleTime

	def NextChangeNative(self):
		return False

	def WaitForChangeNative__quorum_integer(self, milliseconds):
		return False

	def GetChangePathNative(self):
		return ""

	def GetChangeKindNative(self):
		return 1

	def GetChangeRootNative(self):
		return ""
