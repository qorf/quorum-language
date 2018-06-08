#include <deque>


#ifndef MenuControl_HEADER
#define MenuControl_HEADER

class MenuItemControl;
#define MENUITEM_ITERATOR std::deque<MenuItemControl*>::iterator


class MenuControl
{
public:

	MENUITEM_ITERATOR GetMenuItemAt(_In_ int index);

	// Number of child items for this MenuBarControl.
	int GetCount();

	bool AddMenuItem(_In_ MenuItemControl* pMenuItem);

	// Generates a unique id for this instance of this control for RuntimeIds of children.
	ULONG CreateUniqueId();

private:
	std::deque<MenuItemControl*> m_menuItemCollection;

};

#endif