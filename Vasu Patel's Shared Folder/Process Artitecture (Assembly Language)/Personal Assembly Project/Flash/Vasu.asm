        org $1000
p:	db $25
q:      db $37
r:      ds 1

	org $2000
        ldaa p
        adda q
        deca
        staa r
        end