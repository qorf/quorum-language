#include "ListControl.h"
#include "ListProvider.h"
#include "ListItemControl.h"
#include "ListItemProvider.h"
#include "ControlTImpl.h"

ListControl::ListControl(JNIEnv* env, std::wstring&& name, jobject jItem)
	: ControlT(env, std::move(name), L"", jItem)
{
}

ListItemControl* ListControl::GetSelected() const noexcept
{
	return m_selectedItem;
}

Item* ListControl::GetUiaFocusDescendant() const noexcept
{
	return GetSelected();
}

void ListControl::SetSelected(_In_opt_ ListItemControl* item)
{
	m_selectedItem = item;
	if (item != nullptr && UiaClientsAreListening())
	{
		item->GetProvider()->NotifyElementSelected();
	}
}
