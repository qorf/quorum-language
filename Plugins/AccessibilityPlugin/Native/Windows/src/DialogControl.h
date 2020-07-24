#pragma once

#include "ControlT.h"

class DialogProvider;

class DialogControl : public ControlT<DialogControl, DialogProvider>
{
public:
	DialogControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);
	void Close();
	bool IsModal();
};
