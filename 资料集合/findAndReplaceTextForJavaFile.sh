#!/bin/sh  　格式：sed 's/要替换的字符串/新的字符串/g'   （要替换的字符串可以用正则表达式）

sed -i s/$1/$2/g `grep $1 -rl --include="*.java" $3`



