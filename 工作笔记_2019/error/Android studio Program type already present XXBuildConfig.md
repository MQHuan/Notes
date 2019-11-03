Android studio 抛错，是因为有2个module在 AndroidManifest.xml 里面具有一样的package name,修改不同名字即可。

 

还有一种情况是多个module对同一个library(eg: jar)引入多次，此时，可以一个module引入，另外一个module引入上一个module即可。