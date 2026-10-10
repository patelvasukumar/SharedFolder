#!/bin/sh -u
PATH=/bin:/usr/bin ; export PATH
umask 022

if test $(($# % 2)) -ne 0 ; then

    	echo "error"
    	exit 1

fi

while test "$#" -gt 0 ; do
    
	echo $(($1 * $2)) >> multiplied.txt
    	shift 2

done
