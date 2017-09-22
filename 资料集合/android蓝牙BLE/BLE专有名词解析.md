#BLE(Bluetooth Low Energy)
蓝牙4.0核心profile，主要特点是快速搜索，快速连接，超低功耗保持连接和数据传输，缺点：数据传输速率低，由于其具有低功耗特点，所以经常用在可穿戴设备之中

##profile
可以理解为一种规范，一个标准的通信协议，其存在于手机中，蓝牙组织规定了一些标准的profile:HID OVER GATT ,防丢器等，每个profile中包含了多个service。

##service
由UUID作为唯一标示符
可以理解为一个服务，在BLE从机中有多个服务，电量信息，系统服务信息等，每一个service中包含了多个characteristic特征值，每一个具体的characteristic特征值才是BLE通信的主题。


##characteristic (特征值)
由UUID作为唯一标示符，
BLE主机从机通信均是通过characteristic进行，可以将其理解为一个标签，通过该标签可以读取或写入相关信息。一个Characteristic包含一个Value(用于存储uuid),一个Properties和多个Descriptor

###property
特征值的属性,常用的:read, write, notify,Indicating
具体有：read, read_encrypted, read_encrypted_mitm, write, 
write_encrypted, write_encrypted_mitm, write_signed, write_signed_MITM,
broadcast, extended_props, indicate, notify, read , signed_write, write,write_no_response
##descriptor
由UUID作为唯一标示符
一个Descriptor包含一个Value



###UUID(统一标识码)
service和characteristic均需要这个唯一的UUID进行标识。

