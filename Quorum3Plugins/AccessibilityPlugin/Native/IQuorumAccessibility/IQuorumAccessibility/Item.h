#include <windows.h>
#include <UIAutomation.h>
#include <string>

#ifndef Item_HEADER
#define Item_HEADER 

class Item
{
public:
	Item(std::wstring controlName, std::wstring controlDescription);
	virtual void SetControlFocus(_In_ bool Focused);
	virtual bool HasFocus();

	HWND GetHWND();

	void SetName(_In_ std::wstring name);
	const WCHAR* GetName();

	void SetDescription(_In_ std::wstring description);
	const WCHAR* GetDescription();

protected:
	std::wstring m_ControlName;
	std::wstring m_ControlDescription;
	HWND   m_ControlHWND;
};

#endif


