@echo off
echo '--------------------------------------------'
echo '-      ALGONQUIN COLLEGE - COM - 25S       -'
echo '--------------------------------------------'
echo '-                                          -'
echo '-    ====                                  -'
echo '-   =                                      -'
echo '-  =       ==   = =  ==  = =   =   ==  ==  -'
echo '-   =     =  = = = = = = = =   ==  =  ==   -'
echo '-    ====  ==  = = = =   = === === =   ==  -'
echo '-                                          -'
echo '--------------------------------------------'
echo '-  [A5: Writer - Team: VASU PATEL / KAVYA VASANI]  -'
echo '--------------------------------------------'

set "arg=%1"
if "%arg%"=="" set "arg=CODED.txt"

del compilers.exe CODED.txt out.txt err.txt

gcc Compilers.c Main1Coder.c Step1Coder.c Main2Reader.c Step2Reader.c Main3Scanner.c Step3Scanner.c Main4Parser.c Step4Parser.c Main5Writer.c Step5Writer.c -o compilers.exe

timeout /t 1 /nobreak >nul 
compilers.exe 1 1 README2.txt %arg%

timeout /t 1 /nobreak >nul
compilers.exe 5 %arg% > out.txt 2> err.txt

timeout /t 1 /nobreak >nul
dir out.txt err.txt

type out.txt