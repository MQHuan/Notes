public static void printColl(ArrayList<?> al){
                Iterator<?> it = al.iterator();
                while(it.hasNext())
                {
                        System.out.println(it.next().toString());
                }
？和T都表示不确定的类型  但如果是T的话 函数里面可以对T进行操作 比如while里面可以这样写
T t = it.next();
System.out.println(t);

T自定义泛型和?通配符泛型
1.在整个类中只有一处使用了泛型,使用时注意加了泛型了参数不能调用与参数类型有关的方法比如“+”，比如打印出任意参数化类型集合中的所有内容，就适合用通配符泛型<?>
public static void printCollecton(Collection <?> collection)
{
for(Object obj: collection)
{
System.out.println(obj);
}
}
2. 当一个类型变脸用来表达两个参数之间或者参数与返回值之间的关系时，即统一各类型变量在方法签名的两处被使用，或者类型变量在方法体代码中也被使用而不仅 仅在签名的时候使用，这是应该用自定义泛型<T>。泛型方可以调用一些时间类型的方法。比如集合的add方法。
public static <T> T autoConvertType(T obj)
{
     return(T)obj;
}

泛型三种：
          [1]ArrayList<T> al=new ArrayList<T>();指定集合元素只能是T类型
          [2]ArrayList<?> al=new ArrayList<?>();集合元素可以是任意类型，这种没有意义，一般是方法中，只是为了说明用法
          [3]ArrayList<? extends E> al=new ArrayList<? extends E>();
            泛型的限定：
               ? extends E:接收E类型或者E的子类型。
               ？super E:接收E类型或者E的父类型。