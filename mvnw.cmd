@echo off
setlocal

set "MAVEN_PROJECTBASEDIR=%~dp0"
:: Remove trailing backslash
if "%MAVEN_PROJECTBASEDIR:~-1%"=="\" set "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%"

set "WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set "WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain"

if not exist "%WRAPPER_JAR%" (
    echo "Wrapper jar not found at %WRAPPER_JAR%"
    exit /b 1
)

java "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" -cp "%WRAPPER_JAR%" %WRAPPER_LAUNCHER% %*

endlocal
