#include "WindowRoot.h"
#include "WindowRootProvider.h"
#include "RootItemTImpl.h"
// Includes used for logging.
#include <ctime>
#include <chrono>
#include <algorithm>
#include <iomanip>
#include <sstream>
#include <fstream>

constexpr auto c_propName = L"Quorum.WindowRootObject";

namespace
{

void DisconnectAndDestroyRecursive(_In_ Item* item)
{
	auto child = item->GetFirstChild();
	while (child != nullptr)
	{
		auto next = child->GetNextSibling();
		DisconnectAndDestroyRecursive(child);
		child = next;
	}

	item->Disconnect();
	delete item;
}

} // anonymous namespace

WindowRoot::WindowRoot(HWND hwnd, WCHAR* name)
	: RootItemT(nullptr /* env */, name /* name */, L"" /* description */, nullptr /* jItem */)
	, m_hwnd(hwnd)
	, m_isWindowFocused(::GetFocus() == hwnd)
{
	SetProp(hwnd, c_propName, reinterpret_cast<HANDLE>(this));
	m_originalWndProc = reinterpret_cast<WNDPROC>(SetWindowLongPtr(hwnd, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(StaticOverrideWindowProc)));

	// Make a new folder with the results of this session.
	std::time_t currentTime = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
	//std::string folderString = std::ctime(&currentTime);
	//std::replace(folderString.begin(), folderString.end(), ' ', '_');
	//std::replace(folderString.begin(), folderString.end(), ':', '-');
	//int size_needed = MultiByteToWideChar(CP_UTF8, 0, &folderString[0], (int)folderString.size(), NULL, 0);
	//std::wstring folder(size_needed, 0);
	//MultiByteToWideChar(CP_UTF8, 0, &folderString[0], (int)folderString.size(), &folder[0], size_needed);
	//std::wstring folder(std::put_time(std::gmtime(&currentTime), L"%Y-%M-%d %H:%M:%S"));
	std::wstringstream stream;
	stream << std::put_time(std::localtime(&currentTime), L"%m-%d-%Y %H-%M-%S");
	std::wstring result = log_baseDirectory + L"/" + stream.str();

	if (CreateDirectory(result.c_str(), NULL))
	{
		std::wcout << L"Wrote output directory to " << result << std::endl;
		log_currentDirectory = result;
	}
	else
	{
		std::wcout << L"An error occurred while writing the output directory to " << result << std::endl;
		DWORD error = GetLastError();
		if (error == ERROR_ALREADY_EXISTS)
			std::wcout << L"DIRECTORY ALREADY EXISTS." << std::endl;
		else if (error == ERROR_PATH_NOT_FOUND)
			std::wcout << L"PATH NOT FOUND." << std::endl;
		else
			std::wcout << L"UNKNOWN ERROR: " << error << std::endl;
	}
}

WindowRoot::~WindowRoot()
{
	SetWindowLongPtr(m_hwnd, GWLP_WNDPROC, reinterpret_cast<LONG_PTR>(m_originalWndProc));
	RemoveProp(m_hwnd, c_propName);
}

HWND WindowRoot::GetHwnd() const noexcept
{
	return m_hwnd;
}

bool WindowRoot::IsHostFocused() const noexcept
{
	return m_isWindowFocused;
}

/* static */LRESULT CALLBACK WindowRoot::StaticOverrideWindowProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	const auto self = reinterpret_cast<WindowRoot*>(GetProp(hwnd, c_propName));
	FAIL_FAST_IF_NULL(self);
	return self->OverrideWindowProc(msg, wParam, lParam);
}

LRESULT WindowRoot::OverrideWindowProc(UINT msg, WPARAM wParam, LPARAM lParam) noexcept
{
	// Increment the value in the map associated with this key.
	// If no value exists yet, this will initialize it to 0 and then increment it to 1.
	log_messages[msg]++;
	log_messagesSnapshot[msg]++;

	if ((msg == WM_GETOBJECT) && !m_isDisconnecting)
	{
		return UiaReturnRawElementProvider(m_hwnd, wParam, lParam, GetProvider().get());
	}
	else if ((msg == WM_SETFOCUS) && !m_isWindowFocused)
	{
		m_isWindowFocused = true;
		NotifyHostFocusGained();
		// Let the message pass on to the original window proc.
	}
	else if ((msg == WM_KILLFOCUS) && m_isWindowFocused)
	{
		m_isWindowFocused = false;
		NotifyHostFocusLost();
		// Let the message pass on to the original window proc.
	}
	else if (msg == WM_KEYDOWN)
	{
		// Extra logging is performed here.
		Log_WriteSnapshot();

		// We need to let Quorum know that we need to process this keyboard input before we continue pumping messages.
		
		JNIEnv* env = GetJNIEnv();
		if (env != NULL)
		{
			env->CallStaticVoidMethod(JavaClass_AccessibilityManager.me, JavaClass_AccessibilityManager.PauseEventPolling);
		}

		// We still let this message pass on to the original window procedure so GLFW can handle it.
	}

	return CallWindowProc(m_originalWndProc, m_hwnd, msg, wParam, lParam);
}

void WindowRoot::DisconnectAndDestroyAll()
{
	std::cout << "Writing logging info to disk..." << std::endl;
	// We need to record the last snapshot, since there isn't a KEYDOWN event to capture this one.
	Log_WriteSnapshot();

	std::wstring fileName(L"/Results.txt");
	std::wofstream file(log_currentDirectory + fileName);

	int count = 0, totalCount = 0;
	for (std::map<UINT, int>::iterator iterator = log_messages.begin(); iterator != log_messages.end(); iterator++)
	{
		file << iterator->second << L"\t -- \t" << Log_GetMessageName(iterator->first) << std::endl;
		totalCount += iterator->second;
		count++;
	}

	file << L"Total events = " << totalCount << L", unique events = " << count << std::endl;
	file.close();

	std::cout << "Finished writing to file." << std::endl;

	m_isDisconnecting = true;
	DisconnectAndDestroyRecursive(this);
}

void WindowRoot::Log_WriteSnapshot()
{
	log_snapshots++;
	std::wstring prefix(L"/Snapshot");
	std::wstring fileName(prefix + std::to_wstring(log_snapshots) + L".txt");
	std::wofstream file(log_currentDirectory + fileName);

	int count = 0, totalCount = 0;
	for (std::map<UINT, int>::iterator iterator = log_messagesSnapshot.begin(); iterator != log_messagesSnapshot.end(); iterator++)
	{
		file << iterator->second << L"\t -- \t" << Log_GetMessageName(iterator->first) << std::endl;
		totalCount += iterator->second;
		count++;
	}

	file << L"Events = " << totalCount << L", unique events = " << count << std::endl;
	log_messagesSnapshot.clear();
	file.close();
}

std::wstring WindowRoot::Log_GetMessageName(UINT key)
{
	if (log_messageTranslations.count(key))
	{
		std::wstring prefix(log_messageTranslations[key]);
		std::wstring result(prefix + L" (" + std::to_wstring(key) + L")");
		return result;
	}
	else
	{
		std::wstring prefix(L"Unknown (");
		std::wstring result(prefix + std::to_wstring(key) + L")");
		return result;
	}
}
