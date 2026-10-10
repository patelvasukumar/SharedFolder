#!/bin/sh -u
PATH=/bin:/usr/bin ; export PATH 
umask 022

if test $# -eq 0 || test $(($# % 2)) -eq 0 ; then

	echo "Error"

	exit 1

fi

middle=$((($# + 1)/2))
index=1

while test "$#" -gt 0; do

	if test $index -eq $middle ; then
		echo "$1"
		break
	fi

	shift

	index=$((index+1))
done	






