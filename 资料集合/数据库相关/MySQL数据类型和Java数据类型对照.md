<table class="t_table FCK__ShowTableBorders" style="width: 98%;" border="0" cellspacing="0" align="center">
<tbody>
<tr>
<td>类型名称</td>
<td>显示长度</td>
<td>数据库类型</td>
<td>JAVA类型</td>
<td>JDBC类型索引(int)</td>
<td>描述</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>VARCHAR</td>
<td>L+N</td>
<td>VARCHAR</td>
<td>java.lang.String</td>
<td>12</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>CHAR</td>
<td>N</td>
<td>CHAR</td>
<td>java.lang.String</td>
<td>1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>BLOB</td>
<td>L+N</td>
<td>BLOB</td>
<td>java.lang.byte[]</td>
<td>-4</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TEXT</td>
<td>65535</td>
<td>VARCHAR</td>
<td>java.lang.String</td>
<td>-1</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>INTEGER</td>
<td>4</td>
<td>INTEGER UNSIGNED</td>
<td>java.lang.Long</td>
<td>4</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TINYINT</td>
<td>3</td>
<td>TINYINT UNSIGNED</td>
<td>java.lang.Integer</td>
<td>-6</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>SMALLINT</td>
<td>5</td>
<td>SMALLINT UNSIGNED</td>
<td>java.lang.Integer</td>
<td>5</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>MEDIUMINT</td>
<td>8</td>
<td>MEDIUMINT UNSIGNED</td>
<td>java.lang.Integer</td>
<td>4</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>BIT</td>
<td>1</td>
<td>BIT</td>
<td>java.lang.Boolean</td>
<td>-7</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>BIGINT</td>
<td>20</td>
<td>BIGINT UNSIGNED</td>
<td>java.math.BigInteger</td>
<td>-5</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>FLOAT</td>
<td>4+8</td>
<td>FLOAT</td>
<td>java.lang.Float</td>
<td>7</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>DOUBLE</td>
<td>22</td>
<td>DOUBLE</td>
<td>java.lang.Double</td>
<td>8</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>DECIMAL</td>
<td>11</td>
<td>DECIMAL</td>
<td>java.math.BigDecimal</td>
<td>3</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>BOOLEAN</td>
<td>1</td>
<td>同TINYINT</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>ID</td>
<td>11</td>
<td>PK (INTEGER UNSIGNED)</td>
<td>java.lang.Long</td>
<td>4</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>DATE</td>
<td>10</td>
<td>DATE</td>
<td>java.sql.Date</td>
<td>91</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TIME</td>
<td>8</td>
<td>TIME</td>
<td>java.sql.Time</td>
<td>92</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>DATETIME</td>
<td>19</td>
<td>DATETIME</td>
<td>java.sql.Timestamp</td>
<td>93</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>TIMESTAMP</td>
<td>19</td>
<td>TIMESTAMP</td>
<td>java.sql.Timestamp</td>
<td>93</td>
<td>&nbsp;</td>
</tr>
<tr>
<td>YEAR</td>
<td>4</td>
<td>YEAR</td>
<td>java.sql.Date</td>
<td>91</td>
</tr>
</tbody>
</table>
<p><span style="font-size: small;">以上就是Java数据类型和MySql数据类型对应表。</span></p>
<p><span style="font-size: small;">对于bolb，一般用于对图片的数据库存储，原理是把图片打成二进制，然后进行的一种存储方式，在java中对应byte［］数组。</span><br /><br /><span style="font-size: small;">对于boolen类型，在mysql数据库中，个人认为用int类型代替较好，对bit操作不是很方便，尤其是在具有web页面开发的项目中，表示0/1，对应java类型的Integer较好。</span></p>
<p><span style="font-size: small;">
<table border="1">
<tbody>
<tr>
<td><span class="type">BIT(1)</span> (new in MySQL-5.0)</td>
<td><span class="type">BIT</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Boolean</span></code></td>
</tr>
<tr>
<td><span class="type">BIT( &gt; 1)</span> (new in MySQL-5.0)</td>
<td><span class="type">BIT</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">TINYINT</span></td>
<td><span class="type">TINYINT</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Boolean</span></code> if the configuration property <code class="literal"><span style="font-family: NSimsun;">tinyInt1isBit</span></code> is set to <code class="literal"><span style="font-family: NSimsun;">true</span></code> (the default) and the storage size is 1, or <code class="classname"><span style="font-family: NSimsun;">java.lang.Integer</span></code> if not.</td>
</tr>
<tr>
<td><span class="type">BOOL</span>, <span class="type">BOOLEAN</span></td>
<td><span class="type">TINYINT</span></td>
<td>See <span class="type">TINYINT</span>, above as these are aliases for <span class="type">TINYINT(1)</span>, currently.</td>
</tr>
<tr>
<td><span class="type">SMALLINT[(M)] [UNSIGNED]</span></td>
<td><span class="type">SMALLINT [UNSIGNED]</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Integer</span></code> (regardless if UNSIGNED or not)</td>
</tr>
<tr>
<td><span class="type">MEDIUMINT[(M)] [UNSIGNED]</span></td>
<td><span class="type">MEDIUMINT [UNSIGNED]</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Integer,</span></code> if UNSIGNED <code class="classname"><span style="font-family: NSimsun;">java.lang.Long</span></code> (C/J 3.1 and earlier), or <code class="classname"><span style="font-family: NSimsun;">java.lang.Integer</span></code> for C/J 5.0 and later</td>
</tr>
<tr>
<td><span class="type">INT,INTEGER[(M)] [UNSIGNED]</span></td>
<td><span class="type">INTEGER [UNSIGNED]</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Integer</span></code>, if UNSIGNED <code class="classname"><span style="font-family: NSimsun;">java.lang.Long</span></code></td>
</tr>
<tr>
<td><span class="type">BIGINT[(M)] [UNSIGNED]</span></td>
<td><span class="type">BIGINT [UNSIGNED]</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Long</span></code>, if UNSIGNED <code class="classname"><span style="font-family: NSimsun;">java.math.BigInteger</span></code></td>
</tr>
<tr>
<td><span class="type">FLOAT[(M,D)]</span></td>
<td><span class="type">FLOAT</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Float</span></code></td>
</tr>
<tr>
<td><span class="type">DOUBLE[(M,B)]</span></td>
<td><span class="type">DOUBLE</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.Double</span></code></td>
</tr>
<tr>
<td><span class="type">DECIMAL[(M[,D])]</span></td>
<td><span class="type">DECIMAL</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.math.BigDecimal</span></code></td>
</tr>
<tr>
<td><span class="type">DATE</span></td>
<td><span class="type">DATE</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.sql.Date</span></code></td>
</tr>
<tr>
<td><span class="type">DATETIME</span></td>
<td><span class="type">DATETIME</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.sql.Timestamp</span></code></td>
</tr>
<tr>
<td><span class="type">TIMESTAMP[(M)]</span></td>
<td><span class="type">TIMESTAMP</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.sql.Timestamp</span></code></td>
</tr>
<tr>
<td><span class="type">TIME</span></td>
<td><span class="type">TIME</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.sql.Time</span></code></td>
</tr>
<tr>
<td><span class="type">YEAR[(2|4)]</span></td>
<td><span class="type">YEAR</span></td>
<td>If <code class="literal"><span style="font-family: NSimsun;">yearIsDateType</span></code> configuration property is set to false, then the returned object type is <code class="classname"><span style="font-family: NSimsun;">java.sql.Short</span></code>. If set to true (the default) then an object of type <code class="classname"><span style="font-family: NSimsun;">java.sql.Date</span></code> (with the date set to January 1st, at midnight).</td>
</tr>
<tr>
<td><span class="type">CHAR(M)</span></td>
<td><span class="type">CHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code> (unless the character set for the column is <span class="type">BINARY</span>, then <code class="classname"><span style="font-family: NSimsun;">byte[]</span></code> is returned.</td>
</tr>
<tr>
<td><span class="type">VARCHAR(M) [BINARY]</span></td>
<td><span class="type">VARCHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code> (unless the character set for the column is <span class="type">BINARY</span>, then <code class="classname"><span style="font-family: NSimsun;">byte[]</span></code> is returned.</td>
</tr>
<tr>
<td><span class="type">BINARY(M)</span></td>
<td><span class="type">BINARY</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">VARBINARY(M)</span></td>
<td><span class="type">VARBINARY</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">TINYBLOB</span></td>
<td><span class="type">TINYBLOB</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">TINYTEXT</span></td>
<td><span class="type">VARCHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
<tr>
<td><span class="type">BLOB</span></td>
<td><span class="type">BLOB</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">TEXT</span></td>
<td><span class="type">VARCHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
<tr>
<td><span class="type">MEDIUMBLOB</span></td>
<td><span class="type">MEDIUMBLOB</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">MEDIUMTEXT</span></td>
<td><span class="type">VARCHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
<tr>
<td><span class="type">LONGBLOB</span></td>
<td><span class="type">LONGBLOB</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">byte[]</span></code></td>
</tr>
<tr>
<td><span class="type">LONGTEXT</span></td>
<td><span class="type">VARCHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
<tr>
<td><span class="type">ENUM('value1','value2',...)</span></td>
<td><span class="type">CHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
<tr>
<td><span class="type">SET('value1','value2',...)</span></td>
<td><span class="type">CHAR</span></td>
<td><code class="classname"><span style="font-family: NSimsun;">java.lang.String</span></code></td>
</tr>
</tbody>
</table>
</span></p>
<p><span style="font-size: small;">mysql官方文档：</span></p>
<p><span style="font-size: small;"><a href="http://dev.mysql.com/doc/refman/5.0/en/connector-j-reference-type-conversions.html">http://dev.mysql.com/doc/refman/5.0/en/connector-j-reference-type-conversions.html</a></span></p>
</div>
</div>
</td>
</tr>
</tbody>
</table>
<p><br />转自：<a href="http://hi.baidu.com/zdz8207/blog/item/5fc9d209244ed592d0581b56.html">http://hi.baidu.com/zdz8207/blog/item/5fc9d209244ed592d0581b56.html</a></p>   
</div>
