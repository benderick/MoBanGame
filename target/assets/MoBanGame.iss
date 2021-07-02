#define MyAppName "MoBanGame"
#define MyAppVersion "2.0"
#define MyAppPublisher "ACME"
#define MyAppURL ""
#define MyAppExeName "MoBanGame.exe"
#define MyAppFolder "MoBanGame"
#define MyAppLicense ""
#define MyAppIcon "C:\Users\benderick\IdeaProjects\MoBanGame\target\assets\MoBanGame.ico"

[Setup]
AppId={{{#MyAppName}}}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={autopf}\{#MyAppFolder}
DisableDirPage=yes
DisableProgramGroupPage=yes
DisableFinishedPage=yes
PrivilegesRequired=admin
PrivilegesRequiredOverridesAllowed=commandline
LicenseFile={#MyAppLicense}
SetupIconFile={#MyAppIcon}
UninstallDisplayIcon={app}\{#MyAppExeName}
Compression=lzma
SolidCompression=yes
ArchitecturesInstallIn64BitMode=x64

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\benderick\IdeaProjects\MoBanGame\target\MoBanGame\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent runascurrentuser
