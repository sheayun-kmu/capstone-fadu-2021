#!/bin/sh
git diff --name-only $GIT_PREVIOUS_COMMIT $GIT_COMMIT > list.txt

pwd > path.txt
read value < path.txt
while read line
do 
    clang-format-10 --dry-run "${value}/${line}"
done < list.txt 2>> checked.txt
