#include "RootItemBase.h"

RootItemBase::RootItemBase(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem)
	: Item(env, std::move(controlName), std::move(controlDescription), jItem)
{
	m_root = this;
}
