#pragma once

#include "ControlT.h"

class ToolBarProvider;

class ToolBarControl : public ControlT<ToolBarControl, ToolBarProvider>
{
public:
	ToolBarControl(JNIEnv* env, std::wstring&& name, jobject jItem);
};
