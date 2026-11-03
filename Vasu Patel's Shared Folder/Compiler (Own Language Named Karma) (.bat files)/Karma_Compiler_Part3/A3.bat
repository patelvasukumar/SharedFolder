@echo off
echo --------------------------------------------
echo -      ALGONQUIN COLLEGE - COM - 25F       -
echo --------------------------------------------
echo.
echo  ...=====..................................
echo  ..==......................................
echo  .==.......===...=.=..==..=.=..=...==..==..
echo  ..==.....==.==.=.=.=.=.=.=.=..==..=..==...
echo  ...====...===..=.=.=.=...=.==.===.=...==..
echo.
echo  [A3 - Scanner]
echo  [Team: VASU PATEL / KAVYA VASANI]
echo --------------------------------------------

set "arg=%1"
if "%arg%"=="" set "arg=CODED.txt"

gcc Compilers.c Main1Coder.c Step1Coder.c Main2Reader.c Step2Reader.c Main3Scanner.c Step3Scanner.c -o compilers

timeout /t 1 /nobreak >nul

compilers 3 %arg% > out.txt 2> err.txt

timeout /t 1 /nobreak >nul

dir out.txt err.txt
type out.txt