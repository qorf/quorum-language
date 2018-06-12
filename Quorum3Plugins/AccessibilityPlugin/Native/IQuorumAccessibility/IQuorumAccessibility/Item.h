#include <windows.h>
#include <UIAutomation.h>


class Item
{
public:
	virtual void SetControlFocus(_In_ bool Focused);
	virtual bool HasFocus();

	virtual HWND GetHWND();

	void SetName(_In_ WCHAR* name);
	WCHAR* GetName();
	
	void SetDescription(_In_ WCHAR* description);
	WCHAR* GetDescription();



private:
	WCHAR* m_ControlName;
	WCHAR* m_ControlDescription;
};