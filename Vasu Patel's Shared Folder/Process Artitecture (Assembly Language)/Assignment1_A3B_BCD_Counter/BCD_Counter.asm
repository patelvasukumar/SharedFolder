; BCD_Counter.asm
; Author:               D. Haley, Faculty
; Modified by:          Vasukumar Patel, Sabit Naim
; Student Number(s):     041153940, 041155239
; Course:               CST8216
; Date:                 24th July 2025
;
; Purpose       BCD Counter $00 - $15 (BCD) using Hex Displays
;               and a single register, Accumulator A, for the count
;               The range of counting can be altered by changing the
;               FIRST_BCD and END_BCD constants
;
; ***** DO NOT CHANGE ANY CODE BETWEEN THESE MARKERS *****
; Library Routines  - to be used in your solution
;

Config_Hex_Displays         equ        $2117
Delay_Ms                    equ        $211F
Hex_Display                 equ        $2139
Extract_Msb                 equ        $2144
Extract_Lsb                 equ        $2149

; Program Constants - to be used in your solution
STACK           equ     $2000
                                ; Port P (PPT) Display Selection Values
DIGIT3_PP0      equ     %1110   ; Left-most display MSB
DIGIT2_PP1      equ     %1101   ; 2nd from left-most display LSB

; Delay Subroutine Value  - to be used in your solution
DVALUE  equ     #250            ; Delay value (base 10) 0 - 255 ms
                                ; 125 = 1/8 second <- good for Dragon12 Board

; ***** END OF DO NOT CHANGE ANY CODE BETWEEN THESE MARKERS *****

; BCD Count constants  - to be used in your solution
; Changing these values will change the Starting and End BCD counts

FIRST_BCD        equ     $00     ; Starting BCD count
END_BCD          equ     $15     ; Ending BCD count 0

        org     $2000
Start   lds     #STACK          ; Initialize the Stack

        jsr     Config_Hex_Displays ; Use the Hex Displays to display the count
        ; Continually Count FIRST_BCD ... END_BCD ... FIRST_BCD ... END_BCD
        ldaa    #FIRST_BCD     ; loading the first bcd value in accumulator A with immidate addressing mode
Loop:   psha                    ; pusing the value on the stack
        jsr     Extract_Msb       ; extract MSB
        ldab    #DIGIT3_PP0       ; loading value 2 with immidate addressing mode
        jsr     Hex_Display      ; displaying HEX value
        ldaa    #DVALUE            ; loading delay value into accumulator A with immidate mode
        jsr     Delay_Ms           ; going into subrotine
        pula                        ; taking the value from stack
        psha
        jsr     Extract_Lsb         ; extract LSB
        ldab    #DIGIT2_PP1          ; loading into accumulator B
        jsr     Hex_Display         ; going into subroute to display the value
        ldaa    #DVALUE             ; using immidate mode to load the value
        jsr     Delay_Ms           ; going into subroute

        pula                       ; taking the value from stack and adding it on accumulator A
        adda    #$01                ; adding 1 into A
        daa                          ; using daa adjusting the numbers
        cmpa    #END_BCD              ; compare B with the value by using immidate mode
        bls     Loop                  ; branching to the loop if value is lower or same
        ldaa    #FIRST_BCD            ; loading into accumulator A with immidate mode
        bra     Loop                  ; branch to the loop

	end                           ; end of the program