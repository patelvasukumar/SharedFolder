#!/bin/sh -u
PATH=/bin:/usr/bin ; export PATH
umask 022

#!/bin/sh -u
umask 022

while test "$#" -ne 0 ; do
  
	if test $(($1 % 2)) -eq 0 ; then
    	
	echo "$1" >> even.txt
  	
	fi

shift

done
