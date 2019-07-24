#pragma once

#include "ControlT.h"


class ItemProvider;

class ItemControl : public ControlT<ItemControl, ItemProvider>
{
public:
	ItemControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	virtual void Focus(bool focus) override;
};
