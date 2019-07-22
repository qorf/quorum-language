#include <deque>


#ifndef Menu_HEADER
#define Menu_HEADER

class MenuItemControl;
#define MENUITEM_ITERATOR std::deque<MenuItemControl*>::iterator


class Menu
{
public:

	bool AddMenuItem(_In_ MenuItemControl* pMenuItem);
	bool RemoveMenuItem(MenuItemControl * pMenuItem);
	MENUITEM_ITERATOR GetMenuItemAt(_In_ int index);

	// Number of child items for this Menu.
	int GetCount();

	bool HasChildren();

private:
	std::deque<MenuItemControl*> m_menuItemCollection;

};

#endif
