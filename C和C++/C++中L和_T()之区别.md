L"我的字符串" 表示将ANSI字符串转换成unicode的字符串，就是每个字符占用两个字节。  
  strlen("asd") = 3;   
  strlen(L"asd") = 6;  


_T宏可以把一个引号引起来的字符串，根据你的环境设置，使得编译器会根据编译目标环境选择合适的（Unicode还是ANSI）字符处理方式  
  如果你定义了UNICODE，那么_T宏会把字符串前面加一个L。这时 _T("ABCD") 相当于 L"ABCD" ，这是宽字符串。 
  如果没有定义，那么_T宏不会在字符串前面加那个L，_T("ABCD") 就等价于 "ABCD"  

http://www.cnblogs.com/duyy/p/3689130.html