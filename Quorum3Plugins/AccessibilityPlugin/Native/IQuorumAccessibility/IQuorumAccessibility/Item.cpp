#include "Item.h"


Item::Item(std::wstring controlName, std::wstring controlDescription) 
	: m_ControlName(controlName), m_ControlDescription(controlDescription), m_ControlHWND(NULL)
{
}

void Item::SetControlFocus(_In_ bool Focused)
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

void Item::SetName(_In_ std::wstring name)
{
	m_ControlName = name;
}

const WCHAR* Item::GetName()
{
	return m_ControlName.c_str();
}

void Item::SetDescription(_In_ std::wstring description)
{
	m_ControlDescription = description;
}

const WCHAR* Item::GetDescription()
{
	return m_ControlDescription.c_str();
}