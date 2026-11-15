#include "SpreadsheetProvider.h"

#include <vector>

namespace
{

// An array of parts, for the table and selection patterns.
HRESULT PutParts(const std::vector<wil::com_ptr<IRawElementProviderFragment>>& parts, SAFEARRAY** pRetVal)
{
	*pRetVal = nullptr;
	unique_safearray sa{ SafeArrayCreateVector(VT_UNKNOWN, 0, static_cast<ULONG>(parts.size())) };
	RETURN_IF_NULL_ALLOC(sa);
	for (LONG index = 0; index < static_cast<LONG>(parts.size()); index++)
	{
		const auto simple = parts[index].try_query<IRawElementProviderSimple>();
		RETURN_IF_FAILED(SafeArrayPutElement(sa.get(), &index, simple.get()));
	}
	*pRetVal = sa.release();
	return S_OK;
}

} // anonymous namespace

SpreadsheetProvider::SpreadsheetProvider(SpreadsheetControl* control) : ProviderT(control)
{
}

bool SpreadsheetProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_GridPatternId || patternId == UIA_TablePatternId || patternId == UIA_SelectionPatternId);
}

CONTROLTYPEID SpreadsheetProvider::GetControlType() const noexcept
{
	return UIA_DataGridControlTypeId;
}

IFACEMETHODIMP SpreadsheetProvider::Navigate(NavigateDirection direction, _Outptr_result_maybenull_ IRawElementProviderFragment** retVal) noexcept try
{
	*retVal = nullptr;
	if (direction == NavigateDirection_FirstChild)
	{
		Item* first = m_control->GetFirstChild();
		if (first != nullptr)
		{
			*retVal = first->GetProviderFragment().detach();
		}
		else if (m_control->IsAvailableForQueries())
		{
			m_control->RefreshVisibleArea();
			*retVal = m_control->GetPartFragment(0).detach();
		}
		return S_OK;
	}
	if (direction == NavigateDirection_LastChild)
	{
		if (m_control->IsAvailableForQueries())
		{
			m_control->RefreshVisibleArea();
			int count = m_control->GetPartCount();
			if (count > 0)
			{
				*retVal = m_control->GetPartFragment(count - 1).detach();
				return S_OK;
			}
		}
		Item* last = m_control->GetLastChild();
		if (last != nullptr)
		{
			*retVal = last->GetProviderFragment().detach();
		}
		return S_OK;
	}
	return ProviderT::Navigate(direction, retVal);
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetProvider::get_RowCount(int* pRetVal) noexcept
{
	*pRetVal = m_control->GetKnownState().rows;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetProvider::get_ColumnCount(int* pRetVal) noexcept
{
	*pRetVal = m_control->GetKnownState().columns;
	return S_OK;
}

// Any cell shown, on screen or not.
IFACEMETHODIMP SpreadsheetProvider::GetItem(int row, int column, IRawElementProviderSimple** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	if (!m_control->IsAvailableForQueries())
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	if (!m_control->GetKnownState().HasCell(row, column))
	{
		return E_INVALIDARG;
	}
	const auto fragment = m_control->GetCellFragment(row, column);
	if (fragment)
	{
		fragment.query_to(pRetVal);
	}
	return S_OK;
}
CATCH_RETURN();

// The numbers of the rows on screen.
IFACEMETHODIMP SpreadsheetProvider::GetRowHeaders(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	std::vector<wil::com_ptr<IRawElementProviderFragment>> parts;
	if (m_control->IsAvailableForQueries())
	{
		const SpreadsheetState& state = m_control->GetKnownState();
		int count = state.GetVisibleRowCount();
		for (int index = 0; index < count; index++)
		{
			auto header = m_control->GetRowHeaderFragment(state.firstVisibleRow + index);
			if (header)
			{
				parts.push_back(std::move(header));
			}
		}
	}
	return PutParts(parts, pRetVal);
}
CATCH_RETURN();

// The letters of the columns on screen.
IFACEMETHODIMP SpreadsheetProvider::GetColumnHeaders(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	std::vector<wil::com_ptr<IRawElementProviderFragment>> parts;
	if (m_control->IsAvailableForQueries())
	{
		const SpreadsheetState& state = m_control->GetKnownState();
		int count = state.GetVisibleColumnCount();
		for (int index = 0; index < count; index++)
		{
			auto header = m_control->GetColumnHeaderFragment(state.firstVisibleColumn + index);
			if (header)
			{
				parts.push_back(std::move(header));
			}
		}
	}
	return PutParts(parts, pRetVal);
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetProvider::get_RowOrColumnMajor(RowOrColumnMajor* pRetVal) noexcept
{
	*pRetVal = RowOrColumnMajor_RowMajor;
	return S_OK;
}

// The selected cells on screen, and the current cell even when it isn't.
IFACEMETHODIMP SpreadsheetProvider::GetSelection(SAFEARRAY** pRetVal) noexcept try
{
	*pRetVal = nullptr;
	std::vector<wil::com_ptr<IRawElementProviderFragment>> parts;
	if (m_control->IsAvailableForQueries())
	{
		const SpreadsheetState state = m_control->GetKnownState();
		if (state.HasCurrent())
		{
			bool currentAdded = false;
			int rows = state.GetVisibleRowCount();
			int columns = state.GetVisibleColumnCount();
			for (int rowIndex = 0; rowIndex < rows; rowIndex++)
			{
				int row = state.firstVisibleRow + rowIndex;
				for (int columnIndex = 0; columnIndex < columns; columnIndex++)
				{
					int column = state.firstVisibleColumn + columnIndex;
					if (state.Contains(row, column))
					{
						auto cell = m_control->GetCellFragment(row, column);
						if (cell)
						{
							parts.push_back(std::move(cell));
							if (state.IsCurrent(row, column))
							{
								currentAdded = true;
							}
						}
					}
				}
			}
			if (!currentAdded && state.HasCell(state.currentRow, state.currentColumn))
			{
				auto cell = m_control->GetCellFragment(state.currentRow, state.currentColumn);
				if (cell)
				{
					parts.push_back(std::move(cell));
				}
			}
		}
	}
	return PutParts(parts, pRetVal);
}
CATCH_RETURN();

IFACEMETHODIMP SpreadsheetProvider::get_CanSelectMultiple(BOOL* pRetVal) noexcept
{
	*pRetVal = TRUE;
	return S_OK;
}

IFACEMETHODIMP SpreadsheetProvider::get_IsSelectionRequired(BOOL* pRetVal) noexcept
{
	*pRetVal = FALSE;
	return S_OK;
}
