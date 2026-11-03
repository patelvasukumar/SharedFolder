; Array_From_File.asm
; Purpose       Example on how to Iterate through an array read in from a file
;               using a pointer for loop control
;
; Example:      Point to start of source array
;               DO
;                 action code
;               UNTIL one address past end of source array

       org      $1000
Source
#include The_Array.txt                   ; array of unsigned values
End_Source

        org     $1020             ; Dynamically create Result array
Result  ds      End_Source-Source ; with the same size as Source
End_Result

        org     $2000
        ldx     #Source         ; Point to start of source array
Loop    ldaa    1,x+            ; Get a value, increment pointer
        cpx     #End_Source     ; Done?
        bne     Loop            ; No, get another value
        ; more code would go here
       end
       