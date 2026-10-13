; Add_Odd.assm

Add_Odd
                        pshb
                        ldab    #2
                        mul
                        cpd     #9
                        bhi     goToA
backToTrOne             pulb
                        psha
                        ldab    #2
                        mul
                        cpd     #9
                        bhi     goToB
                        pshd
                        pula
                        pulb
                
backToTrTwo             pula
                        aba
                        rts

goToA                   pshx
                        ldx     #10
                        idiv
                        leax    d,x
                        pshx
                        pulb
                        pula
                        pulx
                        bra     backToTrOne

goToB                   pshx
                        ldx     #10
                        idiv
                        leax    d,x
                        pshx
                        pula
                        pulb
                        pulx
                        bra     backToTrTwo


                        rts                                     ; Sum of Odd Digit returned

                        end