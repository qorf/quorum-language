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
  !define MUI_FINISHPAGE_RUN
  !define MUI_FINISHPAGE_RUN_TEXT "Open Quorum Getting Started Guide"
  !define MUI_FINISHPAGE_RUN_FUNCTION "LaunchDocumentation"

;------------------------------
; Bundling configuration
!define NETInstaller "dotNetFx40_Client_setup.exe" ; must reflect filename in this directory!
!define JREInstaller "JavaSetup6u27.exe" ; must reflect filename in this directory!
;--------------------------------
; Pages

!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "License.txt"
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
  
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
 
;Page components
;Page directory
;Page instfiles

;UninstPage uninstConfirm
;UninstPage instfiles

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;-------------------------------
Section "Quorum (required)" Quorum_Sec
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; Pre-installation checks
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  
  ; Check for .NET
  Call IsDotNETInstalled
  Pop $R3
  StrCmp $R3 0 +3
    Goto dotNetInstalled
    ; else - install .NET (should be bundled)
    File /oname=$TEMP\${NETInstaller} ${NETInstaller}
    DetailPrint "Starting Microsoft .NET Framework Setup..."
    ExecWait "$TEMP\${NETInstaller}"

  ; dotNET is already installed - check Java.	
  dotNetInstalled:
  ; Check for Java
  StrCpy $1 "SOFTWARE\JavaSoft\Java Runtime Environment"
  StrCpy $2 0
  ReadRegStr $2 HKLM "$1" "CurrentVersion"
  StrCmp $2 "" DetectTry2
  ReadRegStr $5 HKLM "$1\$2" "JavaHome"
  
  StrCmp $5 "" DetectTry2
  Goto done

  DetectTry2:
  ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Development Kit" "CurrentVersion"
  StrCmp $2 "" NoJava
  ReadRegStr $5 HKLM "SOFTWARE\JavaSoft\Java Development Kit\$2" "JavaHome"
  StrCmp $5 "" NoJava JavaFound

  NoJava:
    File /oname=$TEMP\${JREInstaller} ${JREInstaller}
    DetailPrint "Starting Java Runtime Environment Installer..."
    ExecWait "$TEMP\${JREInstaller}"
  JavaFound:
  
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; Installation Details
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  SectionIn RO
  
  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File /nonfatal /r "Quorum\*.*"
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\Quorum "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "DisplayName" "Quorum 1.0"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Quorum1" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
  ; Write QUORUM_INSTALLATION_PATH environment variable
  WriteRegExpandStr ${env_hklm} QUORUM_INSTALLATION_PATH $INSTDIR
  SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
  
  ; Install the "quorum.bat" file, so users can type "quorum" on the command line.
  SetOutPath $WINDIR
  File "quorum.bat"
SectionEnd

;--------------------------------
; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts" StartMenu_Sec
  CreateDirectory "$SMPROGRAMS\Quorum"
  CreateShortCut "$SMPROGRAMS\Quorum\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0

SectionEnd

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Section Descriptions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
LangString DESC_Section1 ${LANG_ENGLISH} "The core Quorum binaries required to compile Quorum applications."
LangString DESC_Section2 ${LANG_ENGLISH} "A shortcut to the uninstaller will be placed in the Start Menu."

!insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
  !insertmacro MUI_DESCRIPTION_TEXT ${Quorum_Sec} $(DESC_Section1)
  !insertmacro MUI_DESCRIPTION_TEXT ${StartMenu_Sec} $(DESC_Section2)
!insertmacro MUI_FUNCTION_DESCRIPTION_END

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
  Delete "$WINDIR\quorum.bat"
  
  ; Remove directories used
  RMDir "$SMPROGRAMS\Quorum"
  RMDir /r "$INSTDIR"
  
  ; Remove environment variable QUORUM_INSTALLATION_PATH.
  DeleteRegValue ${env_hklm} QUORUM_INSTALLATION_PATH
  SendMessage ${HWND_BROADCAST} ${WM_WININICHANGE} 0 "STR:Environment" /TIMEOUT=5000
SectionEnd

; Function to check for .NET installation
Function IsDotNETInstalled
  Push $0
  Push $1
  Push $2
  Push $3
  Push $4
 
  ReadRegStr $4 HKEY_LOCAL_MACHINE \
    "Software\Microsoft\.NETFramework" "InstallRoot"
  # remove trailing back slash
  Push $4
  Exch $EXEDIR
  Exch $EXEDIR
  Pop $4
  # if the root directory doesn't exist .NET is not installed
  IfFileExists $4 0 noDotNET
 
  StrCpy $0 0
 
  EnumStart:
 
    EnumRegKey $2 HKEY_LOCAL_MACHINE \
      "Software\Microsoft\.NETFramework\Policy"  $0
    IntOp $0 $0 + 1
    StrCmp $2 "" noDotNET
 
    StrCpy $1 0
 
    EnumPolicy:
 
      EnumRegValue $3 HKEY_LOCAL_MACHINE \
        "Software\Microsoft\.NETFramework\Policy\$2" $1
      IntOp $1 $1 + 1
       StrCmp $3 "" EnumStart
        IfFileExists "$4\$2.$3" foundDotNET EnumPolicy
 
  noDotNET:
    StrCpy $0 0
    Goto done
 
  foundDotNET:
    StrCpy $0 1
 
  done:
    Pop $4
    Pop $3
    Pop $2
    Pop $1
    Exch $0
FunctionEnd

; Launches appropriate documentation
Function LaunchDocumentation
	ExecShell "open" "https://sourceforge.net/apps/trac/quorum/wiki/begin"
FunctionEnd