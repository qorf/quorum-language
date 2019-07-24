#pragma once

#include "WindowRoot.h"
#include "ProviderT.h"

class WindowRootProvider : public ProviderT<WindowRootProvider, WindowRoot>
{
public:
	WindowRootProvider(_In_ WindowRoot* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;

	// IRawElementProviderSimple override
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal) noexcept override;
};
