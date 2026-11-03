; Reverse.asm

; Modified by:          Sabit Naim, Vasukumar Patel
; Student Number(s):    041155239, 041153940
; Course:               CST8216
; Date:                 26th July 2025
;
; Purpose       To copy and reverse and array, while performing the following
;               additional operations to decrypt the Secret Message. After
;               reading in a value, we decrypt the value by
;                a. dividing the value by 3
;               b. then adding 32 to the value
;               c. storing the value

; Decryption Constants
DIVISOR         equ     3       ; Value in supplied array will be divided by
                                ; this value
ADDED_VALUE     equ     32      ; Divided value will have 32 added to it
loop_num        ds      1       ; Number of loops

                org     $1000   ; Origin memory address starting from $1000.
Source_Array
#include A3B_Array.txt          ; Importing the secret message array file.
End_Source_Array
                org     $1020   ; Secret Message will appear
                                ; starting here
Destination_Array
                ds      End_Source_Array-Source_Array   ; auto calculate Array Size
End_Destination_Array

                org     $2000
                lds     #$2000                          ; Initialize Stack
                ldx     #Source_Array                   ; Loading the starting address of the array in X
                ldy     #End_Destination_Array-1        ; Loading the array ending address of the array in Y
                clr     loop_num                        ; Initializing number of loops
Loop:
                ldaa    1,x+                             ; Loading from array into A
                clrb                                    ; Clearing B
                ldx     #DIVISOR                        ; Loading 3 in X
                idiv                                    ; Perform division, quotient in X, result in D
                xgdx                                    ; Exchanging the values in D and X
                adda    #ADDED_VALUE                    ; Add 32 to the array
                staa    1,y-                             ; Storing the new values in A
                cpx   #End_Source_Array	  ; Comparing with total array size
                blo     Loop                            ; Loop if number of loops lower than array size

                swi
                end