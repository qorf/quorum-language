#pragma once

#include <map>
#include <string>
#include <utility>
#include "ControlT.h"

class SpreadsheetProvider;

/*
	What the native side knows of a Quorum Spreadsheet at one time, read
	from Java in one call (AccessibilityManager.GetSpreadsheetState). Rows
	and columns count from 0; "shown" means the spreadsheet's displayed
	size, its values and the blank rows and columns after them; "visible"
	means on screen, even partly.
*/
struct SpreadsheetState
{
	// SpreadsheetSelection's kinds.
	static constexpr int Cells = 0;
	static constexpr int Rows = 1;
	static constexpr int Columns = 2;
	static constexpr int Everything = 3;

	int rows = 0;
	int columns = 0;
	int firstVisibleRow = 0;
	int lastVisibleRow = -1;
	int firstVisibleColumn = 0;
	int lastVisibleColumn = -1;
	int currentRow = -1;
	int currentColumn = -1;
	int kind = Cells;
	int firstRow = 0;
	int lastRow = -1;
	int firstColumn = 0;
	int lastColumn = -1;
	bool editing = false;

	bool HasCurrent() const noexcept;
	bool IsCurrent(int row, int column) const noexcept;
	// As SpreadsheetSelection's Contains.
	bool Contains(int row, int column) const noexcept;
	// More than the current cell is selected.
	bool IsRange() const noexcept;
	bool HasRow(int row) const noexcept;
	bool HasColumn(int column) const noexcept;
	bool HasCell(int row, int column) const noexcept;
	int GetVisibleRowCount() const noexcept;
	int GetVisibleColumnCount() const noexcept;
	bool IsRowVisible(int row) const noexcept;
	bool IsColumnVisible(int column) const noexcept;
	bool SameSelection(const SpreadsheetState& other) const noexcept;
	bool SameVisibleArea(const SpreadsheetState& other) const noexcept;

	// Where a screen reader's focus goes, as Spreadsheet's
	// GetAccessibilityFocusPart: the current row's number while whole rows
	// are selected, the current column's letters while whole columns are,
	// otherwise the current cell (or nothing, with no current cell).
	static constexpr int FocusNone = 0;
	static constexpr int FocusCell = 1;
	static constexpr int FocusRowHeader = 2;
	static constexpr int FocusColumnHeader = 3;
	int GetFocusPart() const noexcept;
	// Whether a part (row -1 for a column's letters, column -1 for a row's
	// number) is where the focus goes.
	bool IsFocusTarget(int row, int column) const noexcept;
};

/*
	A Quorum Spreadsheet for UI Automation: a data grid whose cells aren't
	Quorum items. Its cells and its column letters and row numbers are
	parts made here (SpreadsheetPartProvider.h), as a Calendar's days are,
	each known by its row and column, so a cell keeps its element while it
	exists, on screen or not. The edit field, while a cell is edited, is a
	Quorum item, a child before the parts.

	The parts, counted in order, are the ones on screen: the letters of the
	visible columns, then, row by row, each visible row's number followed by
	its visible cells.
*/
class SpreadsheetControl : public ControlT<SpreadsheetControl, SpreadsheetProvider>
{
public:
	SpreadsheetControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	// Whether the Quorum spreadsheet is still there to ask.
	bool IsAvailableForQueries();

	// The state as of the last change Quorum reported.
	const SpreadsheetState& GetKnownState() const noexcept;

	// Reads which rows and columns are on screen again, for a screen reader
	// reading the children before any change was reported (such as before
	// the spreadsheet was first laid out).
	void RefreshVisibleArea();

	// Facts from Quorum.
	std::wstring GetCellText(int row, int column);
	std::wstring GetCellName(int row, int column);
	std::wstring GetColumnLabel(int column);
	// What a screen reader says in place of a part's role (UI Automation's
	// localized control type): a cell's name, such as "C2"; "row" or
	// "column", with ", selected" when wholly selected. Row -1 gives a
	// column's letters; column -1 a row's number.
	std::wstring GetRoleText(int row, int column);
	bool IsCellSelected(int row, int column);
	// Row -1 gives a column's letters; column -1 a row's number.
	UiaRect GetCellBounds(int row, int column);

	// Actions for screen readers, as the keys do.
	void MoveTo(int row, int column, bool extend);
	void SetCellText(int row, int column, const std::wstring& value);

	// The parts' elements, made when first asked for.
	wil::com_ptr<IRawElementProviderFragment> GetCellFragment(int row, int column);
	wil::com_ptr<IRawElementProviderFragment> GetColumnHeaderFragment(int column);
	wil::com_ptr<IRawElementProviderFragment> GetRowHeaderFragment(int row);

	// The parts on screen, in order (see above). A part's place is -1 when
	// it isn't on screen.
	int GetPartCount();
	wil::com_ptr<IRawElementProviderFragment> GetPartFragment(int part);
	int GetCellPart(int row, int column);
	int GetColumnHeaderPart(int column);
	int GetRowHeaderPart(int row);
	wil::com_ptr<IRawElementProviderFragment> GetLastChildFragment();

	// The part the focus goes to (see SpreadsheetState::GetFocusPart), or
	// null.
	wil::com_ptr<IRawElementProviderFragment> GetFocusTargetFragment();

	// Item: the focus target is the focus within the spreadsheet, and the
	// parts follow the Quorum children.
	wil::com_ptr<IRawElementProviderFragment> GetUiaFocusFragment() override;
	wil::com_ptr<IRawElementProviderFragment> GetFragmentAfterChildren() override;

	// Called from Quorum after the spreadsheet changed: its values, its size,
	// what is on screen or the selection.
	void NotifyChanged();
private:
	struct CellEntry
	{
		wil::com_ptr<IRawElementProviderSimple> provider;
		std::wstring name;
		std::wstring value;
		std::wstring roleText;
		bool selected = false;
	};

	struct HeaderEntry
	{
		wil::com_ptr<IRawElementProviderSimple> provider;
		std::wstring roleText;
	};

	SpreadsheetState ReadState();
	void RaiseStringChanged(IRawElementProviderSimple* provider, PROPERTYID property, const std::wstring& value);
	void RaiseSelectedChanged(IRawElementProviderSimple* provider, bool selected);
	void RaiseFocusOnCurrent();

	std::map<std::pair<int, int>, CellEntry> m_cells;
	std::map<int, HeaderEntry> m_columnHeaders;
	std::map<int, HeaderEntry> m_rowHeaders;

	SpreadsheetState m_state;
};
