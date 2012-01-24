; quorum.nsi
;
; The quorum installation script.

!include "MUI2.nsh"
!include "winmessages.nsh"

 ; HKLM (all users) vs HKCU (current user) defines
 !define env_hklm 'HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment"'
 !define env_hkcu 'HKCU "Environment"'
 
;--------------------------------
; The name of the installer
Name "Quorum 1.0"

; The file to write
OutFile "Quorum.exe"

; The default installation directory
InstallDir $PROGRAMFILES\Quorum

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\Quorum" "Install_Dir"

; Request application privileges for Windows Vista
RequestExecutionLevel admin

;--------------------------------
;Interface Configuration

  !define MUI_WELCOMEFINISHPAGE_BITMAP "Welcome.bmp"
  !define MUI_HEADERIMAGE
  !define MUI_HEADERIMAGE_BITMAP "Header.bmp" ; optional
  !define MUI_ABORTWARNING

;--------------------------------
; Pages

!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "License.txt"
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
  
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

;Page components
;Page directory
;Page instfiles

;UninstPage uninstConfirm
;UninstPage instfiles

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
; The stuff to install
Section "Quorum (required)"
  SectionIn RO
  
  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File /nonfatal /r "Quorum\*.*"
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\Quorum "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "DisplayName" "Quorum 1.0"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Qourum1" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
  ; Write QUORUM_INSTALLATION_PATH environment variable
  WriteRegExpandStr ${env_hklm} QUORUM_INSTALLATION_PATH $INSTDIR
  SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
  
  ; Install the "quorum.bat" file, so users can type "quorum" on the command line.
  SetOutPath "C:\Windows\System32"
  File "quorum.bat"
SectionEnd

;--------------------------------
; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"
  CreateDirectory "$SMPROGRAMS\Quorum"
  CreateShortCut "$SMPROGRAMS\Quorum\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0

SectionEnd

;--------------------------------
; Uninstaller
Section "Uninstall"

	
  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum"
  DeleteRegKey HKLM SOFTWARE\Quorum

  ; Remove files and uninstaller
  Delete $INSTDIR\*.*

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\Quorum\*.*"

  ; Remove quorum.bat.
  Delete "C:\Windows\System32\quorum.bat"
  
  ; Remove directories used
  RMDir "$SMPROGRAMS\Quorum"
  RMDir /r "$INSTDIR"
  
  ; Remove environment variable QUORUM_INSTALLATION_PATH.
  DeleteRegValue ${env_hklm} QUORUM_INSTALLATION_PATH
  SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
SectionEnd

