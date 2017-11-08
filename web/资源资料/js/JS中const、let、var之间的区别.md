###const
```
const定义的变量不可以修改，而且必须初始化
const b = 1;    //正确
const b;    //错误，必须初始化
console.log('函数外const定义b：' +　ｂ);/有输出值
//b = 5;
//console.log('函数外修改const定义b:' + b);//无法输出
```
###var
```
var定义的变量可以修改，如果不初始化会输出undefined，不会报错
var a = 1;
// var a;//不会报错
console.log('函数外var定义a:' + a);//可以输出a=1
function change(){  
    a = 4;
    console.log('函数内var定义a:' + a);//可以输出a=4
}
change();
console.log('函数调用后var定义a为函数内部修改值：' + a);//可以输出a=4
```
###let
```
let是块级作用域，函数内部使用let定义后，对函数外部无影响
let c = 3;
console.log('函数外let定义c：' + c);//输出c=3
funcion change(){   
    let c = 6;
    console.log('函数内let定义c：' + c);//输出c=6
}
change();
console.log('函数调用后let定义c不受函数内部定义影响：' + c);//输出c=3
```