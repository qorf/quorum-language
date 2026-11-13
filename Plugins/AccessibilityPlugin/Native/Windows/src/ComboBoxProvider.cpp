#include "ComboBoxProvider.h"
#include "TextRangeProvider.h"

ComboBoxProvider::ComboBoxProvider(ComboBoxControl* control) : ProviderT(control)
{
}

bool ComboBoxProvider::IsPatternSupported(PATTERNID patternId) const noexcept
{
	return (patternId == UIA_ValuePatternId || patternId == UIA_ExpandCollapsePatternId || patternId == UIA_TextPatternId);
}

CONTROLTYPEID ComboBoxProvider::GetControlType() const noexcept
{
	return UIA_ComboBoxControlTypeId;
}

// =========== ITextProvider, as the text field's provider gives it.

IFACEMETHODIMP ComboBoxProvider::GetSelection(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept
{
	*retVal = NULL;
	if (m_control == NULL || m_control->GetMe() == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}

	Range caretRange = m_control->GetSelectionRange();
	ITextRangeProvider* selectionRangeProvider = new TextRangeProvider(m_control, caretRange);
	if (selectionRangeProvider == NULL)
	{
		return E_OUTOFMEMORY;
	}

	HRESULT hr = S_OK;
	*retVal = SafeArrayCreateVector(VT_UNKNOWN, 0, 1);
	if (*retVal == NULL)
	{
		hr = E_OUTOFMEMORY;
	}
	else
	{
		long index = 0;
		hr = SafeArrayPutElement(*retVal, &index, selectionRangeProvider);
		if (FAILED(hr))
		{
			SafeArrayDestroy(*retVal);
			*retVal = NULL;
		}
	}
	selectionRangeProvider->Release();
	return hr;
}

IFACEMETHODIMP ComboBoxProvider::GetVisibleRanges(_Outptr_result_maybenull_ SAFEARRAY** retVal) noexcept
{
	*retVal = NULL;
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::RangeFromChild(_In_opt_ IRawElementProviderSimple* childElement, _Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept
{
	UNREFERENCED_PARAMETER(childElement);
	*retVal = NULL;
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::RangeFromPoint(UiaPoint screenLocation, _Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept
{
	UNREFERENCED_PARAMETER(screenLocation);
	*retVal = nullptr;
	return E_NOTIMPL;
}

IFACEMETHODIMP ComboBoxProvider::get_DocumentRange(_Outptr_result_maybenull_ ITextRangeProvider** retVal) noexcept
{
	*retVal = NULL;
	if (m_control == NULL || m_control->GetMe() == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	Range fullRange = { 0, m_control->GetSize() };
	*retVal = new TextRangeProvider(m_control, fullRange);
	return (*retVal == NULL) ? E_OUTOFMEMORY : S_OK;
}

IFACEMETHODIMP ComboBoxProvider::get_SupportedTextSelection(_Out_ SupportedTextSelection* retVal) noexcept
{
	*retVal = SupportedTextSelection_Single;
	return S_OK;
}

// =========== IValueProvider: the text in the field.

IFACEMETHODIMP ComboBoxProvider::get_IsReadOnly(BOOL* returnValue) noexcept
{
	*returnValue = FALSE;
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::SetValue(LPCWSTR value) noexcept
{
	if (value == nullptr)
	{
		return E_INVALIDARG;
	}
	if (m_control == NULL || m_control->GetMe() == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_control->SetText(std::wstring(value));
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::get_Value(BSTR* returnValue) noexcept
{
	std::wstring text = L"";
	if (m_control != NULL && m_control->GetMe() != NULL)
	{
		text = m_control->GetText();
	}
	*returnValue = SysAllocStringLen(text.data(), static_cast<UINT>(text.size()));
	return S_OK;
}

// =========== IExpandCollapseProvider: the list below the field.

IFACEMETHODIMP ComboBoxProvider::Expand() noexcept
{
	if (m_control == NULL || m_control->GetMe() == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_control->Open();
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::Collapse() noexcept
{
	if (m_control == NULL || m_control->GetMe() == NULL)
	{
		return UIA_E_ELEMENTNOTAVAILABLE;
	}
	m_control->Close();
	return S_OK;
}

IFACEMETHODIMP ComboBoxProvider::get_ExpandCollapseState(ExpandCollapseState* returnValue) noexcept
{
	*returnValue = ExpandCollapseState_Collapsed;
	if (m_control != NULL && m_control->IsOpen())
	{
		*returnValue = ExpandCollapseState_Expanded;
	}
	return S_OK;
}
