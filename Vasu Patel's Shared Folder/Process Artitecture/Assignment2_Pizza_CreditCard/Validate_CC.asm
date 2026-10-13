; Validate_CC.asm

Validate:
         pshx
         tfr     a,d
         ldx     #10
         idiv
         cpd     #0
         beq     Valid
         pulx
         rts

Valid:   ldaa #1
         pulx
         rts                                     ; Valid or Invalid Indicator returned

         end