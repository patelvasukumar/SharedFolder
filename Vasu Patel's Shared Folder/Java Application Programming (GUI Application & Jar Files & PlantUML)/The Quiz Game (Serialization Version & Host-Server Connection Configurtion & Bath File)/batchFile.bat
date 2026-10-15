@echo off
setlocal EnableExtensions

cd /d "%~dp0"

echo ............................................
echo ..     ALGONQUIN COLLEGE - JAP - 25F      ..
echo ............................................

REM ===== CONFIG =====
set "SRC_DIR=src"
set "BIN_DIR=bin"
set "IMG_DIR=images"
set "MAIN_CLASS=MainView.Main"
set "JAR_NAME=A4_Vasu940_Kavya941.jar"

echo.
echo Cleaning old build...
if exist "%BIN_DIR%" rd /s /q "%BIN_DIR%"
if exist "%JAR_NAME%" del /q "%JAR_NAME%" >nul 2>&1

mkdir "%BIN_DIR%"

echo.
echo Compiling...

REM ===== SAFE COMPILATION (handles spaces correctly) =====
for /r "%SRC_DIR%" %%f in (*.java) do (
    javac -d "%BIN_DIR%" -cp "%SRC_DIR%" "%%f"
    if errorlevel 1 goto :build_failed
)

echo.
echo Copying images...
if exist "%IMG_DIR%" xcopy /e /i /y "%IMG_DIR%" "%BIN_DIR%\images\" >nul

if exist "playerDataSave.ser" copy /y "playerDataSave.ser" "%BIN_DIR%\" >nul

echo.
echo Creating JAR...

echo Main-Class: %MAIN_CLASS%> manifest.txt

pushd "%BIN_DIR%"
jar cfm "..\%JAR_NAME%" "..\manifest.txt" .
popd

del manifest.txt

echo.
echo Running JAR...
java -jar "%JAR_NAME%"

goto :end

:build_failed
echo.
echo Build failed. Fix Java errors above.
pause
exit /b

:end
echo.
echo Done!
pause
endlocal