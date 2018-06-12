#include "Item.h"

void Item::SetControlFocus(bool Focused)
{
}

bool Item::HasFocus()
{
	return false;
}

HWND Item::GetHWND()
{
	return m_ControlHWND;
}

void Item::SetName(WCHAR * name)
{
	m_ControlName = name;
}

WCHAR * Item::GetName()
{
	return m_ControlName;
}

void Item::SetDescription(WCHAR * description)
{
	m_ControlDescription = description;
}

WCHAR * Item::GetDescription()
{
	return m_ControlDescription;
}
