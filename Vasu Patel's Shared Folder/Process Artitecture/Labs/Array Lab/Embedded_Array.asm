; Embedded_Array.asm
; Purpose       Example on how to Iterate through an embedded array
;               using a counter variable for loop control
;
; Example:      for ( i = ARRAY_LEN ; i > 0 ; i-- )
;                 action code

ARRAY_LEN       equ     End_Source-Source  ; dynamic creation of array length

        org      $1000
Source  db       $24, $FF, $6F, $4C, $E5  ; array of unsigned values
End_Source

        org     $1020           ; Dynamically create Result array
Result                          ; with the same length as Source
        ds      ARRAY_LEN

                                ; It will cause a Phasing Error
        org     $2000
        ldab    #ARRAY_LEN      ; Counter variable

        ldx     #Source         ; Point to start of source array
Loop    ldaa    1,x+            ; Get a value, increment pointer
        decb                    ; Decrement counter
        cmpb    #$00            ; Done?
        bne     Loop            ; No, so get another value
                                ; Yes
        ; more code would go here
        end



        
        