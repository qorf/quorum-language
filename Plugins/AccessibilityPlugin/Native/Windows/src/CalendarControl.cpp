#include "CalendarControl.h"
#include "CalendarProvider.h"
#include "CalendarDayProvider.h"
#include "ControlTImpl.h"

#include <climits>

namespace
{

std::wstring ToWideString(JNIEnv* env, jstring value)
{
	if (value == nullptr)
	{
		return L"";
	}
	const jchar* characters = env->GetStringChars(value, NULL);
	jsize length = env->GetStringLength(value);
	std::wstring result(reinterpret_cast<const wchar_t*>(characters), length);
	env->ReleaseStringChars(value, characters);
	env->DeleteLocalRef(value);
	return result;
}

} // anonymous namespace

CalendarControl::CalendarControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem)
	: ControlT(env, std::move(name), std::move(description), jItem)
{
	m_firstShown = 0;
	m_current = 0;
	m_chosen = 0;
	m_hasChosen = false;
	if (IsAvailableForQueries())
	{
		m_firstShown = GetFirstShownDay();
		m_current = GetCurrentDay();
		m_hasChosen = GetChosenDay(m_chosen);
	}
}

bool CalendarControl::IsAvailableForQueries()
{
	return GetMe() != nullptr && GetJNIEnv() != NULL;
}

int CalendarControl::GetFirstShownDay()
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetCalendarFirstShownDay == nullptr)
	{
		return m_firstShown;
	}
	return env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCalendarFirstShownDay, GetMe());
}

int CalendarControl::GetCurrentDay()
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetCalendarCurrentDay == nullptr)
	{
		return m_current;
	}
	return env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCalendarCurrentDay, GetMe());
}

// Quorum gives the smallest integer when no day is chosen.
bool CalendarControl::GetChosenDay(int& day)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetCalendarChosenDay == nullptr)
	{
		day = m_chosen;
		return m_hasChosen;
	}
	jint value = env->CallStaticIntMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCalendarChosenDay, GetMe());
	if (value == INT_MIN)
	{
		return false;
	}
	day = value;
	return true;
}

std::wstring CalendarControl::GetDayText(int day)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetCalendarDayText == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCalendarDayText, GetMe(), (jint)day);
	return ToWideString(env, value);
}

std::wstring CalendarControl::GetWeekdayName(int column)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_Calendar.GetWeekdayName == nullptr)
	{
		return L"";
	}
	jstring value = (jstring)env->CallObjectMethod(GetMe(), JavaClass_Calendar.GetWeekdayName, (jint)column);
	return ToWideString(env, value);
}

bool CalendarControl::IsDayAvailable(int day)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.IsCalendarDayAvailable == nullptr)
	{
		return false;
	}
	return env->CallStaticBooleanMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.IsCalendarDayAvailable, GetMe(), (jint)day) == JNI_TRUE;
}

bool CalendarControl::IsDayShown(int day)
{
	int index = day - GetFirstShownDay();
	return index >= 0 && index < DayCount;
}

// Row -1 is the row of weekday names.
UiaRect CalendarControl::GetCellBounds(int row, int column)
{
	UiaRect bounds = {};
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_AccessibilityManager.GetCalendarCellBounds == nullptr)
	{
		return bounds;
	}
	jdoubleArray valuesArray = (jdoubleArray)env->CallStaticObjectMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.GetCalendarCellBounds, GetMe(), (jint)row, (jint)column);
	if (valuesArray == nullptr)
	{
		return bounds;
	}
	jdouble* values = env->GetDoubleArrayElements(valuesArray, 0);
	if (values != nullptr)
	{
		bounds = { values[0], values[1], values[2], values[3] };
		env->ReleaseDoubleArrayElements(valuesArray, values, JNI_ABORT);
	}
	env->DeleteLocalRef(valuesArray);
	return bounds;
}

void CalendarControl::MoveCurrentDayTo(int day)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_Calendar.MoveCurrentDay == nullptr)
	{
		return;
	}
	int days = day - GetCurrentDay();
	if (days != 0)
	{
		env->CallVoidMethod(GetMe(), JavaClass_Calendar.MoveCurrentDay, (jint)days);
	}
}

void CalendarControl::ChooseDay(int day)
{
	JNIEnv* env = GetJNIEnv();
	if (env == NULL || GetMe() == nullptr || JavaClass_Calendar.ChooseCurrentDay == nullptr)
	{
		return;
	}
	MoveCurrentDayTo(day);
	env->CallVoidMethod(GetMe(), JavaClass_Calendar.ChooseCurrentDay);
}

wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetDayFragment(int day)
{
	wil::com_ptr<IRawElementProviderSimple> provider;
	auto found = m_days.find(day);
	if (found != m_days.end())
	{
		provider = found->second;
	}
	else
	{
		wil::com_ptr<CalendarDayProvider> made = wil::MakeOrThrow<CalendarDayProvider>(this, day);
		provider = made.query<IRawElementProviderSimple>();
		m_days[day] = provider;
	}
	return provider.try_query<IRawElementProviderFragment>();
}

wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetHeaderFragment(int column)
{
	if (column < 0 || column >= Columns)
	{
		return nullptr;
	}
	if (!m_headers[column])
	{
		wil::com_ptr<CalendarHeaderProvider> made = wil::MakeOrThrow<CalendarHeaderProvider>(this, column);
		m_headers[column] = made.query<IRawElementProviderSimple>();
	}
	return m_headers[column].try_query<IRawElementProviderFragment>();
}

wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetPartFragment(int part)
{
	if (part < 0 || part >= PartCount)
	{
		return nullptr;
	}
	if (part < Columns)
	{
		return GetHeaderFragment(part);
	}
	return GetDayFragment(GetFirstShownDay() + part - Columns);
}

// The last Quorum child, such as the next month button, which comes before
// the parts.
wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetLastChildFragment()
{
	Item* last = GetLastChild();
	if (last == nullptr)
	{
		return nullptr;
	}
	return last->GetProviderFragment();
}

wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetUiaFocusFragment()
{
	if (!IsAvailableForQueries())
	{
		return GetProviderFragment();
	}
	int current = GetCurrentDay();
	if (!IsDayShown(current))
	{
		return GetProviderFragment();
	}
	return GetDayFragment(current);
}

wil::com_ptr<IRawElementProviderFragment> CalendarControl::GetFragmentAfterChildren()
{
	return GetPartFragment(0);
}

void CalendarControl::RaiseNameChanged(IRawElementProviderSimple* provider, const std::wstring& name)
{
	VARIANT oldName, newName;
	VariantInit(&oldName);
	VariantInit(&newName);
	newName.vt = VT_BSTR;
	newName.bstrVal = SysAllocStringLen(name.data(), static_cast<UINT>(name.size()));
	UiaRaiseAutomationPropertyChangedEvent(provider, UIA_NamePropertyId, oldName, newName);
	VariantClear(&newName);
}

void CalendarControl::RaiseSelectedChanged(IRawElementProviderSimple* provider, bool selected)
{
	VARIANT oldValue, newValue;
	VariantInit(&oldValue);
	VariantInit(&newValue);
	oldValue.vt = VT_BOOL;
	newValue.vt = VT_BOOL;
	oldValue.boolVal = selected ? VARIANT_FALSE : VARIANT_TRUE;
	newValue.boolVal = selected ? VARIANT_TRUE : VARIANT_FALSE;
	UiaRaiseAutomationPropertyChangedEvent(provider, UIA_SelectionItemIsSelectedPropertyId, oldValue, newValue);
}

void CalendarControl::NotifyChanged()
{
	if (!IsAvailableForQueries())
	{
		return;
	}

	int first = GetFirstShownDay();
	int current = GetCurrentDay();
	int chosen = 0;
	bool hasChosen = GetChosenDay(chosen);

	bool firstChanged = (first != m_firstShown);
	bool currentChanged = (current != m_current);
	bool chosenChanged = (hasChosen != m_hasChosen) || (hasChosen && chosen != m_chosen);
	int oldChosen = m_chosen;
	bool hadChosen = m_hasChosen;

	m_firstShown = first;
	m_current = current;
	m_chosen = chosen;
	m_hasChosen = hasChosen;

	bool ready = IsReadyForEvents();

	// Days no longer shown leave the tree.
	if (firstChanged)
	{
		for (auto day = m_days.begin(); day != m_days.end();)
		{
			int index = day->first - first;
			if (index < 0 || index >= DayCount)
			{
				if (ready)
				{
					UiaDisconnectProvider(day->second.get());
				}
				day = m_days.erase(day);
			}
			else
			{
				++day;
			}
		}
	}

	if (!ready)
	{
		return;
	}

	if (firstChanged)
	{
		// Another month: the calendar's parts are read again.
		int rid[] = { UiaAppendRuntimeId, GetUniqueId() };
		UiaRaiseStructureChangedEvent(GetProvider().get(), StructureChangeType_ChildrenInvalidated, rid, ARRAYSIZE(rid));

		// The weekday names change when the first day of the week does.
		for (int column = 0; column < Columns; column++)
		{
			if (m_headers[column])
			{
				RaiseNameChanged(m_headers[column].get(), GetWeekdayName(column));
			}
		}
	}

	if (chosenChanged)
	{
		if (hadChosen)
		{
			auto found = m_days.find(oldChosen);
			if (found != m_days.end())
			{
				RaiseNameChanged(found->second.get(), GetDayText(oldChosen));
				RaiseSelectedChanged(found->second.get(), false);
			}
		}
		if (hasChosen && IsDayShown(chosen))
		{
			const auto fragment = GetDayFragment(chosen);
			const auto provider = fragment.try_query<IRawElementProviderSimple>();
			if (provider)
			{
				RaiseNameChanged(provider.get(), GetDayText(chosen));
				RaiseSelectedChanged(provider.get(), true);
				UiaRaiseAutomationEvent(provider.get(), UIA_SelectionItem_ElementSelectedEventId);
			}
		}
	}

	// While the calendar has the focus, the focus follows the current day.
	if ((currentChanged || firstChanged) && GetRoot() != nullptr && HasUiaFocus() && IsDayShown(current))
	{
		const auto fragment = GetDayFragment(current);
		const auto provider = fragment.try_query<IRawElementProviderSimple>();
		if (provider)
		{
			UiaRaiseAutomationEvent(provider.get(), UIA_AutomationFocusChangedEventId);
		}
	}
}

// Generate ControlT's members here, so other files can link to them (the
// quorum-natives build, WindowsDllMacBuild.md fix 2).
template class ControlT<CalendarControl, CalendarProvider>;
