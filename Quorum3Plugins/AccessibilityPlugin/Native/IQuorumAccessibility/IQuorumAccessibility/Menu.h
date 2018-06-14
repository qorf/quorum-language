#include <deque>


#ifndef Menu_HEADER
#define Menu_HEADER

class MenuItemControl;
#define MENUITEM_ITERATOR std::deque<MenuItemControl*>::iterator


class Menu
{
public:

	MENUITEM_ITERATOR GetMenuItemAt(_In_ int index);
	bool RemoveMenuItem(_In_ int index);

	// Number of child items for this MenuBarControl.
	int GetCount();

	bool hasChildren();

	bool AddMenuItem(_In_ MenuItemControl* pMenuItem);

	// Generates a unique id for this instance of this control for RuntimeIds of children.
	ULONG CreateUniqueId();

private:
	std::deque<MenuItemControl*> m_menuItemCollection;

};

#endif