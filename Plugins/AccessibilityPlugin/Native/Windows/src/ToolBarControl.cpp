#include "ToolBarControl.h"
#include "ToolBarProvider.h"
#include "ControlTImpl.h"

ToolBarControl::ToolBarControl(JNIEnv* env, std::wstring&& name, jobject jItem)
	: ControlT(env, std::move(name), L"", jItem)
{
}
