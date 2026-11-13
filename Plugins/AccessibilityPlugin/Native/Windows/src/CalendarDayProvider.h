#pragma once

#include "CalendarControl.h"
#include "ProviderT.h"

/*
	The parts of a Quorum Calendar that aren't Quorum items: its days and
	its weekday column headers. Each is an element of UI Automation's tree
	under the calendar, reached through the calendar's provider. A part
	holds its calendar and what it stands for (a day number, or a column),
	so an element a screen reader kept after its month was left reports
	that it is no longer available.

	CalendarPartT gives what both kinds share: the element's place in the
	tree, its runtime ID and its bounds. DerivedT provides GetPart (its
	place among the calendar's parts, or -1 when not shown), GetRow and
	GetColumn (row -1 for the headers), GetKind, GetKey, GetControlType,
	GetName, IsPatternSupported and the focus and enabled facts.
*/
template <class DerivedT, typename ...MoreInterfaces>
class CalendarPartT : public wrl::RuntimeClass<
	wrl::RuntimeClassFlags<wrl::RuntimeClassType::ClassicCom>,
	IRawElementProviderSimple,
	IRawElementProviderFragment,
	MoreInterfaces...>
{
public:
	CalendarPartT(_In_ CalendarControl* calendar) : m_calendar(calendar)
	{
	}

	// IRawElementProviderSimple

	IFACEMETHODIMP get_ProviderOptions(_Out_ ProviderOptions* retVal) noexcept override
	{
		*retVal = ProviderOptions_ServerSideProvider | ProviderOptions_UseComThreading;
		return S_OK;
	}

	IFACEMETHODIMP GetPatternProvider(PATTERNID patternId, _Outptr_result_maybenull_ IUnknown** retVal) noexcept override try
	{
		*retVal = nullptr;
		if (static_cast<DerivedT*>(this)->IsPatternSupported(patternId))
		{
			wil::com_query_to(this, retVal);
		}
		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP GetPropertyValue(PROPERTYID propertyId, _Out_ VARIANT* retVal) noexcept override try
	{
		VariantInit(retVal);
		if (!IsAvailable())
		{
			return S_OK;
		}
		DerivedT* derived = static_cast<DerivedT*>(this);
		switch (propertyId)
		{
			case UIA_FrameworkIdPropertyId:
				retVal->bstrVal = wil::make_bstr(L"Quorum").release();
				retVal->vt = VT_BSTR;
				break;

			case UIA_NamePropertyId:
			{
				std::wstring name = derived->GetName();
				retVal->bstrVal = SysAllocStringLen(name.data(), static_cast<UINT>(name.size()));
				retVal->vt = VT_BSTR;
				break;
			}

			case UIA_ControlTypePropertyId:
				retVal->lVal = derived->GetControlType();
				retVal->vt = VT_I4;
				break;

			case UIA_HasKeyboardFocusPropertyId:
				retVal->boolVal = derived->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsKeyboardFocusablePropertyId:
				retVal->boolVal = derived->IsFocusable() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsEnabledPropertyId:
				retVal->boolVal = derived->IsEnabled() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsControlElementPropertyId:
			case UIA_IsContentElementPropertyId:
				retVal->boolVal = VARIANT_TRUE;
				retVal->vt = VT_BOOL;
				break;
		}
		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal) noexcept override
	{
		*retVal = nullptr;
		return S_OK;
	}

	// IRawElementProviderFragment

	IFACEMETHODIMP Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept override try
	{
		*retVal = nullptr;
		if (!IsAvailable())
		{
			return S_OK;
		}
		int part = static_cast<DerivedT*>(this)->GetPart();
		switch (direction)
		{
			case NavigateDirection_Parent:
				*retVal = m_calendar->GetProviderFragment().detach();
				break;

			case NavigateDirection_NextSibling:
				if (part >= 0)
				{
					*retVal = m_calendar->GetPartFragment(part + 1).detach();
				}
				break;

			case NavigateDirection_PreviousSibling:
				if (part == 0)
				{
					*retVal = m_calendar->GetLastChildFragment().detach();
				}
				else if (part > 0)
				{
					*retVal = m_calendar->GetPartFragment(part - 1).detach();
				}
				break;

			default:
				// The parts have no children.
				break;
		}
		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP GetRuntimeId(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override try
	{
		DerivedT* derived = static_cast<DerivedT*>(this);
		int rid[] = { UiaAppendRuntimeId, m_calendar->GetUniqueId(), derived->GetKind(), derived->GetKey() };

		unique_safearray sa{ SafeArrayCreateVector(VT_I4, 0, ARRAYSIZE(rid)) };
		THROW_IF_NULL_ALLOC(sa);
		for (LONG i = 0; i < ARRAYSIZE(rid); i++)
		{
			THROW_IF_FAILED(SafeArrayPutElement(sa.get(), &i, &(rid[i])));
		}
		*retVal = sa.release();
		return S_OK;
	}
	CATCH_RETURN();

	IFACEMETHODIMP get_BoundingRectangle(_Out_ UiaRect* retVal) noexcept override
	{
		*retVal = {};
		if (IsAvailable())
		{
			DerivedT* derived = static_cast<DerivedT*>(this);
			*retVal = m_calendar->GetCellBounds(derived->GetRow(), derived->GetColumn());
		}
		return S_OK;
	}

	IFACEMETHODIMP GetEmbeddedFragmentRoots(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept override
	{
		*retVal = nullptr;
		return S_OK;
	}

	IFACEMETHODIMP SetFocus() noexcept override
	{
		return S_OK;
	}

	IFACEMETHODIMP get_FragmentRoot(_Outptr_result_maybenull_ IRawElementProviderFragmentRoot** retVal) noexcept override try
	{
		*retVal = nullptr;
		if (m_calendar == nullptr || m_calendar->GetRoot() == nullptr)
		{
			return S_OK;
		}
		m_calendar->GetRoot()->GetProviderFragmentRoot().query_to(retVal);
		return S_OK;
	}
	CATCH_RETURN();

protected:
	// Whether the calendar is still there and this part is still shown.
	bool IsAvailable()
	{
		if (m_calendar == nullptr || !m_calendar->IsAvailableForQueries())
		{
			return false;
		}
		return static_cast<DerivedT*>(this)->GetPart() >= 0;
	}

	CalendarControl* m_calendar;
};

/*
	A day of a Quorum Calendar: a data item in the calendar's grid, named
	with the date in words ("Thursday, November 12, 2026, today"), with its
	row and column, its weekday's column header, and the selection item and
	invoke patterns, which choose it as Enter does.
*/
class CalendarDayProvider : public CalendarPartT<CalendarDayProvider, IGridItemProvider, ITableItemProvider, ISelectionItemProvider, IInvokeProvider>
{
public:
	CalendarDayProvider(_In_ CalendarControl* calendar, int day);

	// CalendarPartT
	int GetPart();
	int GetRow();
	int GetColumn();
	int GetKind() const noexcept;
	int GetKey() const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	std::wstring GetName();
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	bool HasFocus();
	bool IsFocusable() const noexcept;
	bool IsEnabled();

	// IRawElementProviderFragment: moves the current day here.
	IFACEMETHODIMP SetFocus() noexcept override;

	// IGridItemProvider
	IFACEMETHODIMP get_Row(int* pRetVal) noexcept override;
	IFACEMETHODIMP get_Column(int* pRetVal) noexcept override;
	IFACEMETHODIMP get_RowSpan(int* pRetVal) noexcept override;
	IFACEMETHODIMP get_ColumnSpan(int* pRetVal) noexcept override;
	IFACEMETHODIMP get_ContainingGrid(IRawElementProviderSimple** pRetVal) noexcept override;

	// ITableItemProvider
	IFACEMETHODIMP GetRowHeaderItems(SAFEARRAY** pRetVal) noexcept override;
	IFACEMETHODIMP GetColumnHeaderItems(SAFEARRAY** pRetVal) noexcept override;

	// ISelectionItemProvider
	IFACEMETHODIMP Select() noexcept override;
	IFACEMETHODIMP AddToSelection() noexcept override;
	IFACEMETHODIMP RemoveFromSelection() noexcept override;
	IFACEMETHODIMP get_IsSelected(BOOL* pRetVal) noexcept override;
	IFACEMETHODIMP get_SelectionContainer(IRawElementProviderSimple** pRetVal) noexcept override;

	// IInvokeProvider
	IFACEMETHODIMP Invoke() noexcept override;
private:
	HRESULT Choose();

	int m_day;
};

/*
	A weekday's column header in a Quorum Calendar, such as "Sunday".
*/
class CalendarHeaderProvider : public CalendarPartT<CalendarHeaderProvider>
{
public:
	CalendarHeaderProvider(_In_ CalendarControl* calendar, int column);

	// CalendarPartT
	int GetPart();
	int GetRow();
	int GetColumn();
	int GetKind() const noexcept;
	int GetKey() const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	std::wstring GetName();
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	bool HasFocus();
	bool IsFocusable() const noexcept;
	bool IsEnabled();
private:
	int m_column;
};
