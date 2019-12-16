dispatch receiver：就是声明这个扩展方法所在的类。即：在哪个类中声明，那个类就是你的分发接收者.
extension receiver:就是你实际扩展的那个类。举个例子：你针对Int类扩展了一个方法add，这个add方法的扩展接收者就是Int类实例。

reference: https://kotlinlang.org/docs/reference/extensions.html
The instance of the class in which the extension is declared is called dispatch receiver, and the instance of the receiver type of the extension method is called extension receiver.

注意：这里 这里的 in which = where ,