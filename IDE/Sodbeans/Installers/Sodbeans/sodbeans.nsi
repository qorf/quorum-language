; sodbeans.nsi
;
; The sodbeans installation script.
!include "MUI2.nsh"
!include "winmessages.nsh"

 ; HKLM (all users) vs HKCU (current user) defines
 !define env_hklm 'HKLM "SYSTEM\CurrentControlSet\Control\Session Manager\Environment"'
 !define env_hkcu 'HKCU "Environment"'
;--------------------------------
; The name of the installer
!define MYNAME "Sodbeans 8.0"
Name "${MYNAME}"

; The file to write
; This has been changed from "Sodbeans.exe" to "SodbeansInstaller.exe" so that it does not conflict with the actual
; name of the real sodbeans executable. This is important, because if JAWS/NVDA scripts are installed for Sodbeans,
; it will unintentionally silence the installer. 
OutFile "Sodbeans 8.0.exe"

InstallDir "$PROGRAMFILES64\Sodbeans 8.0"


; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\Sodbeans 8.0" "Install_Dir"

; Request application privileges for Windows Vista
RequestExecutionLevel admin

;--------------------------------
;Interface Configuration

  !define MUI_WELCOMEFINISHPAGE_BITMAP "Welcome.bmp"
  !define MUI_HEADERIMAGE
  !define MUI_HEADERIMAGE_BITMAP "Header.bmp" ; optional
  !define MUI_ABORTWARNING
  !define MUI_FINISHPAGE_RUN
  !define MUI_FINISHPAGE_RUN_TEXT "Start Sodbeans 8.0"
  !define MUI_FINISHPAGE_RUN_FUNCTION "LaunchSodbeans"
  
 ;------------------------------
; Bundling configuration
!define NETInstaller "dotNetFx35setup.exe" ; .NET 3.5.
!define JDKInstaller "jdk-8u131-windows-i586.exe" ; JDK 8 update 131, 32-bit.
;!define CPPInstaller "vcredist_x86.exe" ; VS 2010 redistributable 32-bit.
!define JDKInstaller64 "jdk-8u131-windows-x64.exe" ; JDK 8 update 131, 64-bit.
;!define CPPInstaller64 "vcredist_x64.exe" ; VS 2010 redistributable 64-bit.

; Macros
!macro WriteToFile NewLine File String
  !if `${NewLine}` == true
  Push `${String}$\r$\n`
  !else
  Push `${String}`
  !endif
  Push `${File}`
  Call WriteToFile
!macroend
!define WriteToFile `!insertmacro WriteToFile false`
!define WriteLineToFile `!insertmacro WriteToFile true`

;--------------------------------
; Pages
Icon "icon.ico"
!define MUI_ICON "icon.ico"
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "License.txt"
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
 
 
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

Var VSRedistributableActual ; this flag is to the actual VS installer
Var JDKInstallerActual ; this flag is to the actual JDK installer

;--------------------------------
; The stuff to install
Section "Sodbeans (required)" Sodbeans_Sec
  ; Work with the appropriate registry.
  IfFileExists $WINDIR\SYSWOW64\*.* Is64bit Is32bit
  Is32bit:
    SetRegView 32
    StrCpy $VSRedistributableActual ${CPPInstaller}
    StrCpy $JDKInstallerActual ${JDKInstaller}
    GOTO End32Bitvs64BitCheck
 
  Is64bit:
    SetRegView 64
    StrCpy $VSRedistributableActual ${CPPInstaller64}
    StrCpy $JDKInstallerActual ${JDKInstaller64}
 
End32Bitvs64BitCheck:
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; Pre-installation checks
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  
  ; Check for .NET
  Call IsDotNETInstalled
  Pop $R3
  IntCmp $R3 0 dotNetNotInstalled dotNetInstalled dotNetInstalled
  dotNetNotInstalled:
    ; else - install .NET (should be bundled)
    File /oname=$TEMP\${NETInstaller} ${NETInstaller}
    DetailPrint "Starting Microsoft .NET Framework Setup..."
    ExecWait "$TEMP\${NETInstaller}"

  ; dotNET is already installed - check for the JDK.	
  dotNetInstalled:
  ClearErrors
  ReadRegStr $1 HKLM "SOFTWARE\JavaSoft\Java Development Kit" "CurrentVersion"
  ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Development Kit\$1" "JavaHome"

  IfErrors 0 JDKFound
  ; Copy the JDK installer and run it.
  File /oname=$TEMP\${JDKInstaller} ${JDKInstaller}
  File /oname=$TEMP\${JDKInstaller64} ${JDKInstaller64}
  DetailPrint "Starting Java Development Kit Installer..."
  ExecWait "$TEMP\$JDKInstallerActual"
  
  

  JDKFound:
  ; Check for the C++ redistributable.
  IfFileExists "C:\Windows\System32\MSVCR100.DLL" CPPFound CPPNotFound
  
  CPPNotFound:
  ; C++ Redistributable not found - install it.
  ;File /oname=$TEMP\${CPPInstaller} ${CPPInstaller}
  ;File /oname=$TEMP\${CPPInstaller64} ${CPPInstaller64}
  ;DetailPrint "Starting C++ Redistributable Installer..."
  ;ExecWait "$TEMP\$VSRedistributableActual"
  
  ; C++ Redistributable found - skip
  CPPFound:
  
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  ;; Installation Details
  ;;;;;;;;;;;;;;;;;;;;;;;;;
  SectionIn RO
  
  ;MessageBox MB_OK "JDK Path | $TEMP\$JDKInstallerActual"
  ;MessageBox MB_OK "Visual Studio Path | $TEMP\$VSRedistributableActual"

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File /nonfatal /r "sodbeans\*.*"
  
  ; Write the installation path into the registry
  WriteRegStr HKLM "SOFTWARE\Sodbeans 8.0" "Install_Dir" "$INSTDIR"

  ; Write additional installation information into the registry.
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0" "Publisher" "Quorum Language Project"
  
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0" "DisplayName" "Sodbeans 8.0"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
  ; Now we need to update the Sodbeans.conf file to have the proper jdk_home.
  UpdateConfFile:
  ReadRegStr $1 HKLM "SOFTWARE\JavaSoft\Java Development Kit" "CurrentVersion"
  ReadRegStr $2 HKLM "SOFTWARE\JavaSoft\Java Development Kit\$1" "JavaHome"
  ;MessageBox MB_OK "JDK Home $2"
  
  ; the jdk_home is now stored in $2.
  
  ; Delete the old .conf file.
  Delete "$INSTDIR\etc\sodbeans.conf"
  ;MessageBox MB_OK "File Deleted"
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# ${HOME} will be replaced by user home directory according to platform` 
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `default_userdir="${HOME}/.sodbeans/dev"`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `default_mac_userdir="${HOME}/Library/Application Support/.sodbeans/dev"`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# options used by the launcher by default, can be overridden by explicit`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# command line switches`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `default_options="--branding sodbeans -J-client -J-Xmx2g -J-Xss2m -J-Xms32m"`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# for development purposes you may wish to append: -J-Dnetbeans.logger.console=true -J-ea`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# default location of JDK/JRE, can be overridden by using --jdkhome`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `jdkhome="$2"`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `# clusters' paths separated by path.separator (semicolon on Windows, colon on Unices)`
  ${WriteLineToFile} `$INSTDIR\etc\sodbeans.conf` `#extra_clusters=`
  ;MessageBox MB_OK "File Written"
SectionEnd

;--------------------------------
; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts" StartMenu_Sec
  CreateDirectory "$SMPROGRAMS\Sodbeans 8.0"
  CreateShortCut "$SMPROGRAMS\Sodbeans 8.0\Uninstall Sodbeans 8.0.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\Sodbeans 8.0\Sodbeans 8.0.lnk" "$INSTDIR\bin\sodbeans.exe" "" "$INSTDIR\bin\sodbeans.exe" 0
  CreateShortCut "$DESKTOP\Sodbeans 8.0.lnk" "$INSTDIR\bin\sodbeans.exe" "" "$INSTDIR\bin\sodbeans.exe" 0

SectionEnd

;--------------------------------
; Jaws and NVDA Scripts 
; These scripts put these particular engines into sleep mode when using Sodbeans.
; These scripts should be named the same as the sodbeans executable--if it's Sodbeans.exe, the files
; should be named sodbeans.jcf and sodbeans.py.
Section "Jaws and NVDA Scripts" Scripts_Sec
	; Install the JAWS script. This makes a couple of assumptions:
	; 1) JAWS 9.0, 12.0 and/or JAWS 13.0 is installed
	; 2) The language is english-us (enu).
	; This should cover 99% of cases, but there may be cases where it won't work.
	; These commands should be /nonfatal, because JAWS or NVDA may not be installed, which
	; is OK.
	
	; JAWS 9-14 scripts.
    SetOutPath "$APPDATA\Freedom Scientific\JAWS\9.0\Settings\enu"
	File /nonfatal "sodbeans.jcf"
	
	SetOutPath "$APPDATA\Freedom Scientific\JAWS\10.0\Settings\enu"
	File /nonfatal "sodbeans.jcf"
	
	SetOutPath "$APPDATA\Freedom Scientific\JAWS\11.0\Settings\enu"
	File /nonfatal "sodbeans.jcf"
	
    SetOutPath "$APPDATA\Freedom Scientific\JAWS\12.0\Settings\enu"
	File /nonfatal "sodbeans.jcf"

    SetOutPath "$APPDATA\Freedom Scientific\JAWS\13.0\Settings\enu"
	File /nonfatal "sodbeans.jcf"
	
    SetOutPath "$APPDATA\Freedom Scientific\JAWS\14.0\Settings\enu"
    File /nonfatal "sodbeans.jcf"

    SetOutPath "$APPDATA\Freedom Scientific\JAWS\15.0\Settings\enu"
    File /nonfatal "sodbeans.jcf"

    SetOutPath "$APPDATA\Freedom Scientific\JAWS\16.0\Settings\enu"
    File /nonfatal "sodbeans.jcf"

    SetOutPath "$APPDATA\Freedom Scientific\JAWS\17.0\Settings\enu"
    File /nonfatal "sodbeans.jcf"

    SetOutPath "$APPDATA\Freedom Scientific\JAWS\18.0\Settings\enu"
    File /nonfatal "sodbeans.jcf"

    ; NVDA Script
    SetOutPath "$APPDATA\nvda\appModules"
    File /nonfatal "sodbeans.py"
SectionEnd

; /o Flag makes this section not checked by default.
Section /o "Clean Up Old Versions" Delete_Old_Sec
    RMDir /r "$APPDATA\.sodbeans"
    RMDir /r "$LOCALAPPDATA\.sodbeans"
SectionEnd

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Section Descriptions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
LangString DESC_Section1 ${LANG_ENGLISH} "The core Sodbeans binaries."
LangString DESC_Section2 ${LANG_ENGLISH} "A shortcut to Sodbeans and its uninstaller will be placed in the Start Menu."
LangString DESC_Section3 ${LANG_ENGLISH} "Scripts that improve compatibility between Jaws, NVDA and Sodbeans."
LangString DESC_Section4 ${LANG_ENGLISH} "Deletes any old information left over from previous Sodbeans installations."
!insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
  !insertmacro MUI_DESCRIPTION_TEXT ${Sodbeans_Sec} $(DESC_Section1)
  !insertmacro MUI_DESCRIPTION_TEXT ${StartMenu_Sec} $(DESC_Section2)
  !insertmacro MUI_DESCRIPTION_TEXT ${Scripts_Sec} $(DESC_Section3)
  !insertmacro MUI_DESCRIPTION_TEXT ${Delete_Old_Sec} $(DESC_Section4)
!insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------
; Uninstaller
Section "Uninstall"
  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Sodbeans 8.0"
  DeleteRegKey HKLM "SOFTWARE\Sodbeans 8.0"

  ; Remove files and uninstaller
  Delete $INSTDIR\*.*

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\Sodbeans 8.0\*.*"
  Delete "$DESKTOP\Sodbeans 8.0.lnk"

  ; Remove directories used
  RMDir "$SMPROGRAMS\Sodbeans 8.0"
  RMDir /r "$INSTDIR"
   
  ; Delete JAWS and NVDA scripts.
  Delete "$APPDATA\Freedom Scientific\JAWS\9.0\Settings\enu\sodbeans.jcf" ; JAWS 9
  Delete "$APPDATA\Freedom Scientific\JAWS\10.0\Settings\enu\sodbeans.jcf" ; JAWS 10
  Delete "$APPDATA\Freedom Scientific\JAWS\11.0\Settings\enu\sodbeans.jcf" ; JAWS 11
  Delete "$APPDATA\Freedom Scientific\JAWS\12.0\Settings\enu\sodbeans.jcf" ; JAWS 12
  Delete "$APPDATA\Freedom Scientific\JAWS\13.0\Settings\enu\sodbeans.jcf" ; JAWS 13
  Delete "$APPDATA\Freedom Scientific\JAWS\14h.0\Settings\enu\sodbeans.jcf" ; JAWS 14
  Delete "$APPDATA\Freedom Scientific\JAWS\15h.0\Settings\enu\sodbeans.jcf" ; JAWS 15
  Delete "$APPDATA\Freedom Scientific\JAWS\16h.0\Settings\enu\sodbeans.jcf" ; JAWS 16
  Delete "$APPDATA\Freedom Scientific\JAWS\17h.0\Settings\enu\sodbeans.jcf" ; JAWS 17
  Delete "$APPDATA\Freedom Scientific\JAWS\18h.0\Settings\enu\sodbeans.jcf" ; JAWS 18
  Delete "$APPDATA\nvda\appModules\sodbeans.py" ; NVDA

  ; Delete old app data.
  RMDir /r "$APPDATA\.sodbeans"
  RMDir /r "$LOCALAPPDATA\.sodbeans"
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
    ; As a temporary fix, the installer assumes the user does not have .NET, regardless of what this function finds.
    ;StrCpy $0 1
    StrCpy $0 0
 
  done:
    Pop $4
    Pop $3
    Pop $2
    Pop $1
    Exch $0
FunctionEnd

; Launches the Sodbeans executable.
Function LaunchSodbeans
	Exec "$INSTDIR\bin\sodbeans.exe"
FunctionEnd

; Function for writing Sodbeans.conf.
Function WriteToFile
Exch $0 ;file to write to
Exch
Exch $1 ;text to write
 
  FileOpen $0 $0 a #open file
  FileSeek $0 0 END #go to end
  FileWrite $0 $1 #write to file
  FileClose $0
 
Pop $1
Pop $0
FunctionEnd
