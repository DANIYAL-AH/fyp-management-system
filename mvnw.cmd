@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars:
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to wait for a key stroke before ending
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM ----------------------------------------------------------------------------

@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@REM set title of command window
title %0
@REM enable echoing by setting MAVEN_BATCH_ECHO to 'on'
@if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre
@REM check for pre script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"
if exist "%HOME%\mavenrc_pre.cmd" call "%HOME%\mavenrc_pre.cmd"
:skipRcPre

@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

for %%i in (java.exe) do set "JAVACMD=%%~$PATH:i"
if not "%JAVACMD%" == "" goto init

echo.
echo Error: JAVA_HOME not found in your environment. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" set "JAVACMD=%JAVA_HOME%\bin\java.exe"
if exist "%JAVA_HOME%\bin\java.exe" goto init

echo.
echo Error: JAVA_HOME is set to an invalid directory. >&2
echo JAVA_HOME = "%JAVA_HOME%" >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
echo.
goto error

:init
@REM Find the project base dir
set "MAVEN_PROJECTBASEDIR=%~dp0"
@REM TRIM BENCH
set "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%"

@REM Resolve any "." and ".." in MAVEN_PROJECTBASEDIR
pushd "%MAVEN_PROJECTBASEDIR%"
set "MAVEN_PROJECTBASEDIR=%CD%"
popd

@REM ----------------------------------------------------------------------------
@REM Find the wrapper.properties
@REM ----------------------------------------------------------------------------
set "MAVEN_WRAPPER_PROPERTIES=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties"

@REM ----------------------------------------------------------------------------
@REM Find the wrapper.jar
@REM ----------------------------------------------------------------------------
set "MAVEN_WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set "MAVEN_WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain"

@REM ----------------------------------------------------------------------------
@REM Check if the wrapper jar exists, if not, download it
@REM ----------------------------------------------------------------------------
if exist "%MAVEN_WRAPPER_JAR%" goto wrapperJarFound

echo Couldn't find %MAVEN_WRAPPER_JAR%, downloading it ...

@REM ----------------------------------------------------------------------------
@REM Check if the wrapper properties exists, if not, error
@REM ----------------------------------------------------------------------------
if not exist "%MAVEN_WRAPPER_PROPERTIES%" (
    echo Error: %MAVEN_WRAPPER_PROPERTIES% not found. >&2
    goto error
)

@REM ----------------------------------------------------------------------------
@REM Read wrapperUrl from wrapper.properties
@REM ----------------------------------------------------------------------------
for /f "tokens=1,2 delims==" %%A in (%MAVEN_WRAPPER_PROPERTIES%) do (
    if "%%A"=="wrapperUrl" set "WRAPPER_URL=%%B"
)

if "%WRAPPER_URL%"=="" (
    echo Error: wrapperUrl not specified in %MAVEN_WRAPPER_PROPERTIES%. >&2
    goto error
)

@REM ----------------------------------------------------------------------------
@REM Download the wrapper jar using PowerShell
@REM ----------------------------------------------------------------------------
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%MAVEN_WRAPPER_JAR%'}"
if %ERRORLEVEL% neq 0 (
    echo Error: Failed to download maven-wrapper.jar from %WRAPPER_URL% >&2
    goto error
)

:wrapperJarFound

@REM ----------------------------------------------------------------------------
@REM Run the Maven Wrapper
@REM ----------------------------------------------------------------------------
"%JAVACMD%" ^
  -classpath "%MAVEN_WRAPPER_JAR%" ^
  "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" ^
  %MAVEN_WRAPPER_LAUNCHER% %*

if %ERRORLEVEL% neq 0 goto error
goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_SKIP_RC%" == "" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
if exist "%HOME%\mavenrc_post.bat" call "%HOME%\mavenrc_post.bat"
if exist "%HOME%\mavenrc_post.cmd" call "%HOME%\mavenrc_post.cmd"
:skipRcPost

@REM pause the script if MAVEN_BATCH_PAUSE is set to 'on'
if "%MAVEN_BATCH_PAUSE%" == "on" pause

if "%MAVEN_TERMINATE_CMD%" == "on" exit %ERROR_CODE%

exit /B %ERROR_CODE%
