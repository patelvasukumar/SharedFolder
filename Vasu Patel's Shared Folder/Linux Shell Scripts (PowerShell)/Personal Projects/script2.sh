#!/bin/sh
PATH=/bin:/usr/bin ; export PATH
umask 022

maxValue=$1
minValue=$1

while test $# -gt 0 ; do

	if test $maxValue -lt $1 ; then
        maxValue=$1
	fi

    	if test $minValue -gt $1 ; then
        minValue=$1
    	fi
    
	shift

done

echo "The max value is $maxValue"
echo "The min value is $minValue"
