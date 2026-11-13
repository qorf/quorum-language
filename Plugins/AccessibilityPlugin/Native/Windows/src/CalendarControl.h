#pragma once

#include <map>
#include <string>
#include "ControlT.h"

class CalendarProvider;

/*
	A Quorum Calendar for UI Automation, modeled on the Win32 month
	calendar: a grid of six weeks of seven days, with the weekday names as
	column headers. The days and headers aren't Quorum items; they are parts
	made here (CalendarDayProvider, CalendarHeaderProvider), each day known
	by its day number (days from January 1, 1970), so a day keeps its
	element while it stays shown. The previous and next month buttons are
	Quorum items, the calendar's children before its parts.

	The parts, counted in order: the seven headers (0 to 6), then the 42
	days shown, row by row (7 to 48).
*/
class CalendarControl : public ControlT<CalendarControl, CalendarProvider>
{
public:
	static constexpr int Rows = 6;
	static constexpr int Columns = 7;
	static constexpr int DayCount = Rows * Columns;
	static constexpr int PartCount = Columns + DayCount;

	CalendarControl(JNIEnv* env, std::wstring&& controlName, std::wstring&& controlDescription, jobject jItem);

	// Whether the Quorum calendar is still there to ask.
	bool IsAvailableForQueries();

	// Facts from Quorum. Days are day numbers.
	int GetFirstShownDay();
	int GetCurrentDay();
	bool GetChosenDay(int& day);
	std::wstring GetDayText(int day);
	std::wstring GetWeekdayName(int column);
	bool IsDayAvailable(int day);
	bool IsDayShown(int day);
	UiaRect GetCellBounds(int row, int column);

	// Actions for screen readers: moving the current day to a day, and
	// choosing a day, as the arrow keys and Enter do.
	void MoveCurrentDayTo(int day);
	void ChooseDay(int day);

	// The parts' elements, made when first asked for.
	wil::com_ptr<IRawElementProviderFragment> GetDayFragment(int day);
	wil::com_ptr<IRawElementProviderFragment> GetHeaderFragment(int column);
	wil::com_ptr<IRawElementProviderFragment> GetPartFragment(int part);
	wil::com_ptr<IRawElementProviderFragment> GetLastChildFragment();

	// Item: the current day is the focus within the calendar, and the parts
	// follow the Quorum children.
	wil::com_ptr<IRawElementProviderFragment> GetUiaFocusFragment() override;
	wil::com_ptr<IRawElementProviderFragment> GetFragmentAfterChildren() override;

	// Called from Quorum after the calendar changed: the current day moved,
	// another month is shown, or a day was chosen.
	void NotifyChanged();
private:
	void RaiseNameChanged(IRawElementProviderSimple* provider, const std::wstring& name);
	void RaiseSelectedChanged(IRawElementProviderSimple* provider, bool selected);

	std::map<int, wil::com_ptr<IRawElementProviderSimple>> m_days;
	wil::com_ptr<IRawElementProviderSimple> m_headers[Columns];

	int m_firstShown;
	int m_current;
	int m_chosen;
	bool m_hasChosen;
};
