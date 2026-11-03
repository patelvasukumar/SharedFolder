; Indexed_Addressing_Examples2.asm
        org     $1000
Array   db      $FF, $03, $F9, $80, $92, $88, $55, $AA
EndArray

        org     $2000

        ldx     #Array  ; point X to Array
        ldaa    2,x+    ; AFTER the execution of this line of code (LOC)
                        ; A = ?
                        ; X = ?

        ldab    3,x+    ; AFTER the execution of this LOC
                        ; B = ?
                        ; X = ?

        ldd     4,x-    ; AFTER the execution of this LOC
                        ; A = ?
                        ; B = ?
                        ; X = ?

        ldx     #EndArray  ; point X to one past the end of the Array
        ldaa    2,-x    ; AFTER the execution of this line of code (LOC)
                        ; X = ?
                        ; A = ?


        ldab    3,-x    ; AFTER the execution of this LOC
                        ; X = ?
                        ; B = ?


        ldd  	3,+x    ; AFTER the execution of this LOC
                        ; X = ?
			; A = ?
                        ; B = ?

	end
