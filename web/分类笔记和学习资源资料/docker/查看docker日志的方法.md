docker logs 查看实时日志
```
docker logs -f -t --since="2017-05-31" --tail=10 edu_web_1
```
 
```
--since : 此参数指定了输出日志开始日期，即只输出指定日期之后的日志。

-f : 查看实时日志

-t : 查看日志产生的日期

-tail=10 : 查看最后的10条日志。

edu_web_1 : 容器名称
```