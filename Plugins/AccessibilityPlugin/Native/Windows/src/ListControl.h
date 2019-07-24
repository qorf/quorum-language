#pragma once

#include "ControlT.h"

class ListProvider;
class ListItemControl;

class ListControl : public ControlT<ListControl, ListProvider>
{
public:
	ListControl(JNIEnv* env, std::wstring&& name, jobject jItem);

	ListItemControl* GetSelected() const noexcept;
	Item* GetUiaFocusDescendant() const noexcept override;
	void SetSelected(_In_opt_ ListItemControl* selectedItem);

private:
	ListItemControl* m_selectedItem = nullptr;
};
