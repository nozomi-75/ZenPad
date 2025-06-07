$ErrorActionPreference = "Stop"

$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
$ProjectRoot = Join-Path -Path $ScriptDir -ChildPath ".." | Resolve-Path
Set-Location -Path $ProjectRoot

Write-Host "Current Working Directory set to: $ProjectRoot"
Write-Host "ZenPad project root should be located here."

Write-Host "`nChecking for Maven installation..."
try {
    $mavenVersionOutput = (mvn -version 2>&1 | Out-String).Trim()
    if ($LASTEXITCODE -ne 0) {
        throw "Maven command 'mvn -version' failed with exit code $LASTEXITCODE. Please ensure Maven is installed and added to your system's PATH."
    }
    Write-Host "Maven found:"
    Write-Host $mavenVersionOutput.Split("`n")[0]
} catch {
    Write-Error $_.Exception.Message
    exit 1
}

Write-Host "`nChecking for JDK installation with jpackage..."
try {
    $jpackageVersionOutput = (jpackage --version 2>&1 | Out-String).Trim()
    if ($LASTEXITCODE -ne 0) {
        throw "jpackage command 'jpackage --version' failed with exit code $LASTEXITCODE. This usually means the JDK (which includes jpackage) is not installed or not added to your system's PATH."
    }
    Write-Host "jpackage found (indicating JDK presence):"
    Write-Host $jpackageVersionOutput.Split("`n")[0]
} catch {
    Write-Error $_.Exception.Message
    exit 1
}

Write-Host "`nEnvironment checks passed. Proceeding with build..."

Write-Host "`nBuilding project with Maven...`n"
mvn clean package

Write-Host "`nSearching for JAR file in target..."
$jarFile = Get-ChildItem -Path target -Filter "*-jar-with-dependencies.jar" -File -Recurse | Select-Object -First 1
if (-not $jarFile) {
    Write-Error "JAR file not found after Maven build. Looked in: $($ProjectRoot)\target"
    exit 1
}
$jarName = $jarFile.Name

Write-Host "`nCreating installable package with jpackage..."

$jpackageArgs = @(
  "--name", "ZenPad",
  "--app-version", "1.2-SNAPSHOT",
  "--input", "target",
  "--main-jar", "$jarName",
  "--main-class", "zenpad.core.ZenPad",
  "--dest", ".",
  "--vendor", "Zens",
  "--description", "A beginner-friendly tool for exploring and learning code.",
  "--copyright", "©2025 Zens.",
  "--license-file", "LICENSE"
)

if ($IsWindows) {
    $jpackageArgs += "--icon", "src\main\resources\icons\64x64.ico"
} elseif ($IsLinux) {
    $jpackageArgs += "--icon", "src/main/resources/icons/64x64.png"
    $jpackageArgs += "--linux-shortcut"
    $jpackageArgs += "--linux-package-name", "zenpad"
}

jpackage @jpackageArgs

$jpackageExitCode = $LASTEXITCODE
if ($jpackageExitCode -ne 0) {
    Write-Error "jpackage failed with exit code $jpackageExitCode. See above messages for details."
    exit 1
}

Write-Host "`nSearching for the generated package file in current directory..."
if ($IsWindows) {
    $packageFile = Get-ChildItem -Path . -Filter "ZenPad*.msi" -File -Recurse | Select-Object -First 1
} elseif ($IsLinux) {
    $packageFile = Get-ChildItem -Path . -Filter "ZenPad*.deb" -File -Recurse | Select-Object -First 1
    if (-not $packageFile) {
        $packageFile = Get-ChildItem -Path . -Filter "ZenPad*.rpm" -File -Recurse | Select-Object -First 1
    }
    if (-not $packageFile) {
        $packageFile = Get-ChildItem -Path . -Filter "ZenPad*" -File -Recurse | Where-Object { $_.BaseName -like "ZenPad*" -and $_.Extension -ne ".jar" } | Select-Object -First 1
    }
}

if ($packageFile) {
    Write-Host "Package built successfully:"
    Write-Host "$($packageFile.FullName)"
} else {
    Write-Error "jpackage reported success, but no 'ZenPad' package file found in the current directory."
    Write-Error "This might indicate a partial failure or a mismatch in expected output."
    exit 1
}