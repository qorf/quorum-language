#pragma once

#include "WindowRoot.h"
#include "RootProviderT.h"

class WindowRootProvider : public RootProviderT<WindowRootProvider, WindowRoot>
{
public:
	WindowRootProvider(_In_ WindowRoot* control);

	// ProviderT
	CONTROLTYPEID GetControlType() const noexcept;

	// IRawElementProviderSimple override
	IFACEMETHODIMP get_HostRawElementProvider(_Outptr_result_maybenull_ IRawElementProviderSimple** retVal) noexcept override;
};
