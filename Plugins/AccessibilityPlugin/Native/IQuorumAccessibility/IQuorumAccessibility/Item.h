#include <windows.h>
#include <UIAutomation.h>
#include <string>

#include "jni.h"
#include "Resources.h"

#ifndef Item_HEADER
#define Item_HEADER 

class Item
{
public:
	Item(JNIEnv* env, std::wstring controlName, std::wstring controlDescription, jobject jItem);
	virtual void Focus(bool isFocused);
	virtual bool HasFocus();

	HWND GetHWND();
	void SetName(_In_ std::wstring name);
	const WCHAR* GetName();
	void SetDescription(_In_ std::wstring description);
	const WCHAR* GetDescription();
	jobject GetMe();
	int GetHashCode();
	void SetHashCode(int hash);

	// TODO: Change the return type to Item* once we're rid of the HWNDs.
	jlong SetFocus();
protected:
	std::wstring m_ControlName;
	std::wstring m_ControlDescription;
	HWND   m_ControlHWND;
	bool focused;
	int objectHash;
	jobject javaItem;
};

#endif


