#!/bin/sh
#This script takes any generated documentation from the compiler and places it
#into the existing webpage.


echo "Copying documentation."
cp -r Run/Documents/Libraries/ ../../quorum-support/QuorumWebsite/html/Libraries
cp Run/Documents/libraries.html ../../quorum-support/QuorumWebsite/html

echo "Finished."