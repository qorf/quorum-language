#include <windows.h>
#include <UIAutomation.h>

#include "CustomMessages.h"
#include "Menu.h"
#include "Item.h"

class MenuItemProvider;
class MenuBarControl;

class MenuItemControl : public Menu, public Item
{
public:
	MenuItemControl(_In_ WCHAR* menuItemName, _In_ WCHAR* menuItemShortcut, _In_ ULONG uniqueId, _In_opt_ MenuItemControl* parentMenuItem, _In_ MenuBarControl* parentMenuBar);
	virtual ~MenuItemControl();

	MenuBarControl* GetParentMenuBar();
	void SetParentMenuBar(_In_ MenuBarControl* menuBar);
	MenuItemControl* GetParentMenuItem();
	MenuItemProvider* GetMenuItemProvider();
	Menu* GetMenuControl();

	WCHAR* GetShortcut();
	
	ULONG GetId();

	int GetMenuItemIndex();
	void SetMenuItemIndex(_In_ int index);

	bool HasFocus();

private:

	// The id that uniquely identifies this item within an instance of a MenuBar or MenuItem collection.
	ULONG m_uniqueId;

	// Where this MenuItem is located in the collection.
	int m_myIndex;

	WCHAR* m_shortcut;

	// The provider for this MenuItem
	MenuItemProvider* m_pMenuItemProvider;
	
	// The MenuBar that this MenuItem belongs to. This won't always be
	// this MenuItem's direct parent but it is always an ancestor.
	MenuBarControl* m_pParentMenuBar;

	// The MenuItem that this MenuItem is nested in. This can be null.
	// If this is null then the MenuBar is the direct parent of this control.
	MenuItemControl* m_pParentMenuItem;

	void SetControlFocus(_In_ bool focused);
};