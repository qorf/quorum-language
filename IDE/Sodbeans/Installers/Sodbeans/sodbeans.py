"""
App Module for Sodbeans

This module will put NVDA into sleep mode when Sodbeans is active.
"""

import appModuleHandler

class AppModule(appModuleHandler.AppModule):
	sleepMode = True