#!/bin/sh -u
PATH=/bin:/usr/bin ; export PATH
umask 022

while test "$#" -gt 0 ; do
  
	value="$1"
	
      	if test "$value" -gt 0  2>/dev/null; then
   
	   echo "$value" >> positives.txt
	fi
  
shift

done
