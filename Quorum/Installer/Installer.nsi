;NSIS Modern User Interface
;Quorum Studio Installer Script



;--------------------------------
;Include Modern UI and the path adjusting code.

  !include "MUI2.nsh"
  !include "EnvVarUpdate.NSH"

;--------------------------------
;General

  ;Name and file
  !define VERSION "7.0.5"
  !define REGISTRY_KEY "Software\Quorum"

  Name "Quorum Studio ${VERSION}"
  Caption "Quorum"
  Icon "quorum.ico"
  OutFile "Quorum ${VERSION}.exe"

  ;Default installation folder
  InstallDir "$PROGRAMFILES64\Quorum"
  
  ;Get installation folder from registry if available
  InstallDirRegKey HKCU ${REGISTRY_KEY} ""

  ;Request application privileges for Windows Vista
  RequestExecutionLevel admin

;--------------------------------
;Variables

  Var StartMenuFolder

;--------------------------------
;Interface Settings

  !define MUI_ABORTWARNING

;--------------------------------
;Pages

  !insertmacro MUI_PAGE_LICENSE "License.txt"
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  
  ;Start Menu Folder Page Configuration
  !define MUI_STARTMENUPAGE_REGISTRY_ROOT "HKCU" 
  !define MUI_STARTMENUPAGE_REGISTRY_KEY ${REGISTRY_KEY}
  !define MUI_STARTMENUPAGE_REGISTRY_VALUENAME "Start Menu Folder"
  
  !insertmacro MUI_PAGE_STARTMENU Application $StartMenuFolder
  
  !insertmacro MUI_PAGE_INSTFILES
  
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Installer Sections

XPStyle on

Function .onInit
	# the plugins dir is automatically deleted when the installer exits
	InitPluginsDir
	File /oname=$PLUGINSDIR\Quorum.bmp "Quorum.bmp"

	splash::show 1000 $PLUGINSDIR\Quorum

	Pop $0 ; $0 has '1' if the user closed the splash screen early,
			; '0' if everything closed normally, and '-1' if some error occurred.
FunctionEnd

Section "Core" SecDummy

  SetOutPath "$INSTDIR"
  
  ;ADD YOUR OWN FILES HERE...
  File Quorum.bat
  File quorum.ico
  File /nonfatal /r "..\Quorum\*.*"
  ;File /nonfatal /r "..\Library"

  ;Store installation folder
  WriteRegStr HKCU ${REGISTRY_KEY} "" $INSTDIR
  
  ;Create uninstaller
  WriteUninstaller "$INSTDIR\Uninstall.exe"
  
  !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
    
    ;Create shortcuts
     
    CreateDirectory "$SMPROGRAMS\$StartMenuFolder"
    CreateShortcut "$SMPROGRAMS\$StartMenuFolder\Uninstall.lnk" "$INSTDIR\Uninstall.exe"
    ${EnvVarUpdate} $0 "PATH" "A" "HKLM" "$PROGRAMFILES64\Quorum"   

    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum" \
                 "DisplayName" "Quorum ${VERSION}"
    WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum" \
                 "UninstallString" "$\"$INSTDIR\uninstall.exe$\""
  !insertmacro MUI_STARTMENU_WRITE_END

SectionEnd

;--------------------------------
;Descriptions

    ;!define MUI_FINISHPAGE_RUN "$INSTDIR\Quorum.exe"
    ;!insertmacro MUI_PAGE_FINISH

  ;Language strings
  LangString DESC_SecDummy ${LANG_ENGLISH} "Introduction."

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${SecDummy} $(DESC_SecDummy)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END
  
;--------------------------------
;Uninstaller Section

Section "Uninstall"

  ;ADD YOUR OWN FILES HERE...

  Delete "$INSTDIR\Uninstall.exe"

  RMDir /r "$INSTDIR"
  ${un.EnvVarUpdate} $0 "PATH" "R" "HKLM" "$SMPROGRAMS\Quorum" 
  !insertmacro MUI_STARTMENU_GETFOLDER Application $StartMenuFolder
    
  Delete "$SMPROGRAMS\$StartMenuFolder\Uninstall.lnk"
  RMDir "$SMPROGRAMS\$StartMenuFolder"

  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum"
  
  DeleteRegKey /ifempty HKCU ${REGISTRY_KEY}

SectionEnd