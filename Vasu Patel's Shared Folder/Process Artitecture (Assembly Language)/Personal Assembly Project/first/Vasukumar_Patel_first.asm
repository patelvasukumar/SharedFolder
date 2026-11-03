; First.asm
;
; Author:       	Vasukumar Patel
; Student Number:       041153940
; Date:                 5th June 2025
;
; Purpose:              Add the following values: $25 + $37 - $1
;
        org     $1000   ; Origin for program Date
x:      db      $25     ; First added is at location x
y:      db      $37     ; Second added is at location y
z:      ds      1       ; Sum will be stored at location z
        org     $2000   ; Origin for program Instructions
        ldaa    x       ; Load value at p into accumulator x
        adda    y       ; Add value at q into accumulator y
        deca            ; Decrement x
        staa    z       ; Stroe accumulator a at location z
        end