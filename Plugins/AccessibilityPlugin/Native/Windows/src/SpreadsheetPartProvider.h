#pragma once

#include "SpreadsheetControl.h"
#include "ProviderT.h"

/*
	The parts of a Quorum Spreadsheet that aren't Quorum items: its cells,
	its column letters and its row numbers. Each is an element of UI
	Automation's tree under the spreadsheet, reached through the
	spreadsheet's provider, as a Calendar's days are (CalendarDayProvider.h).
	A part holds its spreadsheet and its row and column, so an element a
	screen reader kept after its row or column was removed reports that it
	is no longer available.

	SpreadsheetPartT gives what all kinds share: the element's place in the
	tree, its runtime ID and its bounds. DerivedT provides GetPart (its place
	among the parts on screen, or -1), GetRow and GetColumn (row -1 for a
	column's letters, column -1 for a row's number), Exists, IsVisible,
	GetKind, GetControlType, GetName, GetRoleText (said in place of the
	control type; empty for the usual words), IsPatternSupported and the
	focus facts.
*/
template <class DerivedT, typename ...MoreInterfaces>
class SpreadsheetPartT : public wrl::RuntimeClass<
	wrl::RuntimeClassFlags<wrl::RuntimeClassType::ClassicCom>,
	IRawElementProviderSimple,
	IRawElementProviderFragment,
	MoreInterfaces...>
{
public:
	SpreadsheetPartT(_In_ SpreadsheetControl* sheet) : m_sheet(sheet)
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
		if (IsAvailable() && static_cast<DerivedT*>(this)->IsPatternSupported(patternId))
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

			// Said in place of the control type: a cell's name, such as
			// "C2", or "row" or "column" for a header.
			case UIA_LocalizedControlTypePropertyId:
			{
				std::wstring roleText = derived->GetRoleText();
				if (!roleText.empty())
				{
					retVal->bstrVal = SysAllocStringLen(roleText.data(), static_cast<UINT>(roleText.size()));
					retVal->vt = VT_BSTR;
				}
				break;
			}

			case UIA_HasKeyboardFocusPropertyId:
				retVal->boolVal = derived->HasFocus() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsKeyboardFocusablePropertyId:
				retVal->boolVal = derived->IsFocusable() ? VARIANT_TRUE : VARIANT_FALSE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsEnabledPropertyId:
				retVal->boolVal = VARIANT_TRUE;
				retVal->vt = VT_BOOL;
				break;

			case UIA_IsOffscreenPropertyId:
				retVal->boolVal = derived->IsVisible() ? VARIANT_FALSE : VARIANT_TRUE;
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
				*retVal = m_sheet->GetProviderFragment().detach();
				break;

			case NavigateDirection_NextSibling:
				if (part >= 0)
				{
					*retVal = m_sheet->GetPartFragment(part + 1).detach();
				}
				break;

			case NavigateDirection_PreviousSibling:
				if (part == 0)
				{
					*retVal = m_sheet->GetLastChildFragment().detach();
				}
				else if (part > 0)
				{
					*retVal = m_sheet->GetPartFragment(part - 1).detach();
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
		int rid[] = { UiaAppendRuntimeId, m_sheet->GetUniqueId(), derived->GetKind(), derived->GetRow(), derived->GetColumn() };

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
			*retVal = m_sheet->GetCellBounds(derived->GetRow(), derived->GetColumn());
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
		if (m_sheet == nullptr || m_sheet->GetRoot() == nullptr)
		{
			return S_OK;
		}
		m_sheet->GetRoot()->GetProviderFragmentRoot().query_to(retVal);
		return S_OK;
	}
	CATCH_RETURN();

protected:
	// Whether the spreadsheet is still there and this part's row and column
	// still exist.
	bool IsAvailable()
	{
		if (m_sheet == nullptr || !m_sheet->IsAvailableForQueries())
		{
			return false;
		}
		return static_cast<DerivedT*>(this)->Exists();
	}

	SpreadsheetControl* m_sheet;
};

/*
	A cell of a Quorum Spreadsheet: a data item in the grid, named with what
	a screen reader says for it (its text or "blank"), said with its name,
	such as "C2", in place of "data item", with its row and
	column and their headers, the selection item pattern (selecting a cell
	moves the current cell there, as the arrow keys do) and the value
	pattern (setting it is an edit that can be undone, as typing is).
*/
class SpreadsheetCellProvider : public SpreadsheetPartT<SpreadsheetCellProvider, IGridItemProvider, ITableItemProvider, ISelectionItemProvider, IValueProvider>
{
public:
	SpreadsheetCellProvider(_In_ SpreadsheetControl* sheet, int row, int column);

	// SpreadsheetPartT
	int GetPart();
	int GetRow() const noexcept;
	int GetColumn() const noexcept;
	bool Exists();
	bool IsVisible();
	int GetKind() const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	std::wstring GetName();
	std::wstring GetRoleText();
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	bool HasFocus();
	bool IsFocusable() const noexcept;

	// IRawElementProviderFragment: moves the current cell here.
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

	// IValueProvider
	IFACEMETHODIMP SetValue(LPCWSTR value) noexcept override;
	IFACEMETHODIMP get_Value(BSTR* pRetVal) noexcept override;
	IFACEMETHODIMP get_IsReadOnly(BOOL* pRetVal) noexcept override;
private:
	int m_row;
	int m_column;
};

/*
	A column's letters ("C") or a row's number ("3") in a Quorum
	Spreadsheet: a header item, said as "row" or "column" ("row, selected"
	when the whole row is selected). While whole rows are selected, the
	current row's number has the focus; while whole columns are, the current
	column's letters.
*/
class SpreadsheetHeaderProvider : public SpreadsheetPartT<SpreadsheetHeaderProvider>
{
public:
	// A column's letters when isColumn is true, otherwise a row's number.
	SpreadsheetHeaderProvider(_In_ SpreadsheetControl* sheet, bool isColumn, int index);

	// SpreadsheetPartT
	int GetPart();
	int GetRow() const noexcept;
	int GetColumn() const noexcept;
	bool Exists();
	bool IsVisible();
	int GetKind() const noexcept;
	CONTROLTYPEID GetControlType() const noexcept;
	std::wstring GetName();
	std::wstring GetRoleText();
	bool IsPatternSupported(PATTERNID patternId) const noexcept;
	bool HasFocus();
	bool IsFocusable() const noexcept;
private:
	bool m_isColumn;
	int m_index;
};
