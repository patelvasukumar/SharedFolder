@echo off

echo ............................................
echo ..     ALGONQUIN COLLEGE - JAP - 25F      ..
echo ............................................
echo.
echo   ........=====...........................
echo   ......=+===.............................
echo   ......===+=........=+...................
echo   .......===.........=+...=+.=+..+=...+=..
echo   ...=========....==.=+.+=.=.+=+===.+=.=..
echo   ....==========...==+..====...==...====..
echo   ..==.=======............................
echo   ...=============........................
echo.
echo.         [Team: VASU PATEL / KAVYA VASANI]
echo ............................................

REM ====== CONFIG ======
set SRC_DIR=src
set DOC_DIR=doc
set BIN_DIR=bin
set IMG_DIR=images
set MAIN_CLASS=MainView.Main
set JAR_NAME=A4_Vasu940_Kavya941.jar

REM ====== JAVADOC ======
echo.
echo Starting Javadoc...
rd /s /q %DOC_DIR% 2>nul
javadoc -d %DOC_DIR% %SRC_DIR%\MainView\*.java

REM ====== COMPILE ======
echo.
echo Compiling...
rd /s /q %BIN_DIR% 2>nul
mkdir %BIN_DIR%

REM Compile ALL packages (important for your structure)
javac -d %BIN_DIR% ^
%SRC_DIR%\MainView\*.java ^
%SRC_DIR%\MainView\Play\*.java ^
%SRC_DIR%\MainView\Design\*.java ^
%SRC_DIR%\Controller\Play\*.java ^
%SRC_DIR%\Controller\Design\*.java ^
%SRC_DIR%\Model\Design\*.java ^
%SRC_DIR%\Model\Exception\*.java ^
%SRC_DIR%\CommonComponents\*.java ^
%SRC_DIR%\util\*.java

REM ====== COPY FILES ======
echo.
echo Copying images...
xcopy /s /y %IMG_DIR% %BIN_DIR%\images\ >nul

REM Copy serialized file if exists
if exist playerDataSave.ser copy /y playerDataSave.ser %BIN_DIR%\ >nul

REM ====== CREATE JAR ======
echo.
echo Creating JAR...

echo Main-Class: %MAIN_CLASS% > manifest.txt

cd %BIN_DIR%
jar cfm ../%JAR_NAME% ../manifest.txt *
cd ..

del manifest.txt

REM ====== RUN ======
echo.
echo Running JAR...
java -jar %JAR_NAME%

echo.
echo Done!
pause