#include <windows.h>
#include <UIAutomation.h>

#ifndef Item_HEADER
#define Item_HEADER 

class Item
{
public:
	virtual void SetControlFocus(_In_ bool Focused);
	virtual bool HasFocus();

	HWND GetHWND();

	void SetName(_In_ WCHAR* name);
	WCHAR* GetName();

	void SetDescription(_In_ WCHAR* description);
	WCHAR* GetDescription();

protected:
	WCHAR* m_ControlName = L"Item";
	WCHAR* m_ControlDescription = L"";
	HWND   m_ControlHWND = NULL;
};

#endif


