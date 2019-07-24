#pragma once

#include "Item.h"

class RootItemBase : public Item
{
public:
	RootItemBase(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	virtual wil::com_ptr<IRawElementProviderFragmentRoot> GetProviderFragmentRoot() = 0;
