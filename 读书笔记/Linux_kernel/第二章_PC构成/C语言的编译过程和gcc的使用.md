####gcc编译C的四个阶段： 预处理阶段，编译阶段，汇编阶段和链接阶段
eg:
![](/home/mai/Work/Notes/读书笔记/Linux_kernel/第二章_PC构成/C语言的编译过程.png) 

* 在前处理阶段：gcc会把C程序传递给C前处理器CPP,对C语言程序中指示符和宏进行替换处理，输出纯C语言代码
* 编译阶段：gcc把C语言程序编译生成对应的与机器相关的as汇编语言代码
* 汇编阶段：as汇编器会把汇编代码转换成机器指令，并以特定的二进制格式输出保存在目标文件中
* 链接阶段：最后GNU ld链接器把程序的相关目标文件组合链接在一起，生成程序的可执行映像文件


###gcc的命令行格式：
```
gcc [选项] [-o outfile] infile ...

gcc -o hello hello.c            // 编译hello.c程序，生成执行文件hello
gcc -S -o hello.s hello.c    // 编译hello.c程序， 生成对应汇编程序hello.s
gcc -c -o hello.o hello.c    // 编译hello.c程序， 生成对应目标文件hello.o而不链接
```

