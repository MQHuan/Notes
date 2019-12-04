 # define __user  __attribute__((noderef, address_space(1)))
__user这个特性，即__attribute__((noderef, address_space(1)))，是用来修饰一个变量的，这个变量必须是非解除参考（no dereference）的，即这个变量地址必须是有效的，而且变量所在的地址空间必须是1，即用户程序空间的。
这里把程序空间分成了3个部分，0表示normal space，即普通地址空间，对内核代码来说，当然就是内核空间地址了。1表示用户地址空间，这个不用多讲，还有一个2，表示是设备地址映射空间，例如硬件设备的寄存器在内核里所映射的地址空间。

__attribute__是gnu c编译器的一个功能，它用来让开发者使用此功能给所声明的函数或者变量附加一个属性，以方便编译器进行错误检查，其实就是一个内核检查器。如函数

static int hello_write(struct file *file, const char __user * buf, size_t count, loff_t *ppos){......}

参考：
https://blog.csdn.net/u012308586/article/details/89309857