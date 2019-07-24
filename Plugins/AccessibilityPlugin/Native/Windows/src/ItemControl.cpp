#include "ItemControl.h"
#include "ItemProvider.h"
#include "ControlTImpl.h"

ItemControl::ItemControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem) : ControlT(env, std::move(controlName), std::move(controlDescription), jItem)
{
}

void ItemControl::Focus(bool focused)
{
	if (focused) 
	{
		this->GetProvider()->NotifyFocusGained();
	}
}
