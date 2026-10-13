; Switch_It_Up.asm

#include C:\68HCS12\registers.inc

; Author:       Vasukumar Patel
; S/N:          041153940
; Date:         17th July 2025

; Purpose:      To Display Patterns Associated with SW2, SW3, SW4 and SW5 on
;               LEDs on Port B on Dragon 12+ Trainer

; Operation:    Once the SW number has been determined, the pattern unique to it
;               is output on Port B (PORTB) by calling the internal subprocess
;               Display_Value, which also delays the output by 1/8 sec in order
;               to avoid flicker between values. The program then Loops back to
;               the beginning to get another SW value and the process
;               loops forever.

;                Note that if no switches are pressed, the display
;               remains blank. See code for SW5
;
FLICKER_DELAY   equ     125     ; 125 ms or 1/8 of a second
STACK           equ     $2000

; When one of switch S2 - S4 is pressed, these values are input on Port H (PTH)
; Note that only ONE bit can be a zero (0) at any one time
SW2_VAL equ     %11110111       ; decimal value for SW2
SW3_VAL equ     %11111011       ; decimal value for SW3
SW4_VAL equ     %11111101       ; decimal value for SW4
SW5_VAL equ     %11111110       ; decimal value for SW5

; These are the values to be output on Port B Leds (PORTB)
ALL_OFF equ     %00000000         ; Binary Value to off all LEDS
PATTERN_2 equ   %00000010         ; value for pattern 2
PATTERN_3 equ   %00000011         ; value for pattern 3
PATTERN_4 equ   %00000100         ; value for pattern 4
PATTERN_5 equ   %00000101         ; value for pattern 5

        org     $2000             ; memory address of the program
Start   lds     #STACK            ;  start label, and stack pointer, and immidate addressing mode
        jsr     Config_SWs_and_LEDs ; this menemonic tranfer the program to a sub-program, and after finishing sub-program, it starts at the main menu

LEDsOff ldab    #ALL_OFF        ; immidate mode and loading the value to off the LEDS
        jsr     Display_Value   ; to LEDs
Get_SW  ldaa    PTH             ; get data from Switches

SW2     cmpa    #SW2_VAL        ; Is SW2 presssed? bit 3 = 0 ?
        bne     SW3             ; No, get another SW value
        ldab    #PATTERN_2      ; Yes, so set up to display Switch #: 2 base 2
        jsr     Display_Value   ; to LEDs
        bra     Get_SW          ; check again for presseed switch
        
SW3     cmpa    #SW3_VAL        ; it compares the immidate value with the switch's input value which is given by user
        bne     SW4             ; then it checks the z falg, and checks if is it true or false, and if it is not equal to, it goes to the next label
        ldab    #PATTERN_3      ; if switch value matches witch the program value, the pattern value will be activated. It adds the value from memory (LDAB)
        jsr     Display_Value   ; it jumps to a subroutine program
        bra     Get_SW          ; it branches with the given label

SW4     cmpa    #SW4_VAL         ; it compares the immidate value with the switch's input value which is given by user
        bne     SW5               ; then it checks the z falg, and checks if is it true or false, and if it is not equal to, it goes to the next label
        ldab    #PATTERN_4         ; if switch value matches witch the program value, the pattern value will be activated. It adds the value from memory (LDAB)
        jsr     Display_Value        ; it jumps to a subroutine program
        bra     Get_SW                 ; it branches with the given label

SW5     cmpa    #SW5_VAL         ; it compares the immidate value with the switch's input value which is given by user
        bne     LEDsOff            ; then it checks the z falg, and checks if is it true or false, and if it is not equal to, it goes to the next label
        ldab    #PATTERN_5          ; if switch value matches witch the program value, the pattern value will be activated. It adds the value from memory (LDAB)
        jsr     Display_Value       ; it jumps to a subroutine program
        bra     Get_SW             ; it branches with the given label

Display_Value
        stab    PORTB           ; Value to LEDs
        ldaa    #FLICKER_DELAY  ; Delay 1/8 th of a second
        jsr     Delay_ms        ; to avoid flicker
        rts

#include C:\68HCS12\Lib\Config_SWs_and_LEDs.asm
#include C:\68HCS12\Lib\Delay_ms.asm

        end