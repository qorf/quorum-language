// Runner.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <windows.h>

int main(int argc, char* argv[])
{
	const DWORD size = 1000;
	wchar_t buffer[size];
	GetModuleFileName(NULL, buffer, size);

	wchar_t terminate[1];
	wcsncpy_s(terminate, L"\0", 1);
	int pathSize = -1;
	for (int i = 0; i < size; i++) {
		if (buffer[i] == terminate[0]) {
			pathSize = i;
			i = size + 1;
		}
	}
	wchar_t jar[4];
	wcsncpy_s(jar, L"jar", 4);

	buffer[pathSize - 3] = jar[0];
	buffer[pathSize - 2] = jar[1];
	buffer[pathSize - 1] = jar[2];

	//char charBuffer[1000];
	//int value = wcstombs_s(charBuffer, buffer, pathSize - 1);
	std::cout << buffer << std::endl;
	char str[10000];
	strcpy_s(str, "java -jar Quorum.jar ");
	for (int i = 1; i < argc; ++i) {
		std::cout << argv[i] << "\n";
		strcat_s(str, argv[i]);
		strcat_s(str, " ");
	}
	strcat_s(str, " & ");
	system(str);
}

//VOID startup(LPCTSTR lpApplicationName)
//{
//	// additional information
//	STARTUPINFO si;
//	PROCESS_INFORMATION pi;
//
//	// set the size of the structures
//	ZeroMemory(&si, sizeof(si));
//	si.cb = sizeof(si);
//	ZeroMemory(&pi, sizeof(pi));
//
//	// start the program up
//	CreateProcess(lpApplicationName,   // the path
//		argv[1],        // Command line
//		NULL,           // Process handle not inheritable
//		NULL,           // Thread handle not inheritable
//		FALSE,          // Set handle inheritance to FALSE
//		0,              // No creation flags
//		NULL,           // Use parent's environment block
//		NULL,           // Use parent's starting directory 
//		&si,            // Pointer to STARTUPINFO structure
//		&pi             // Pointer to PROCESS_INFORMATION structure (removed extra parentheses)
//	);
//	// Close process and thread handles. 
//	CloseHandle(pi.hProcess);
//	CloseHandle(pi.hThread);
//}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
