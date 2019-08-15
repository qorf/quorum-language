#include "DialogControl.h"
#include "DialogProvider.h"
#include "ControlTImpl.h"

DialogControl::DialogControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}
