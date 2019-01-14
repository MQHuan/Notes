2017/1/5 Refactoring Note One

原则： 让项目更简洁，更易于维护

应用系统模块：用户，设备，运动数据，社交
用户模块：待实现 根据用户绑定数据，不同的用户登陆显示不同的数据
设备模块
运动数据模块:待实现 离线记录运动数据，联网时上传功能，可能要添加一个离线状态和在线状态


第一个任务：确定重构的项目目录

目前手环部分的代码目录是
activiy
bean
biz : Business层，就是控制层(业务逻辑层)，主要作用就是协调model层和view层的直接调用和转换
download
iview
model
presenter
utils
view
widget

更改想法： 
* 我们的项目主要通过fragment做界面，没必要有一个activity目录，删掉。
* bean: 存放一些数据模型，主要用于model和presenter层的交互，把bean目录放到model目录下
* biz:这个目录跟presenter重合了，分解删掉
* download：下载更像一个工具，放到工具类utils
* iview: 定义死接口有点不够灵活，改起来不舒服，线程管理有点烦，慢慢改回匿名回调好了，删掉
* model:MVP的M
* presenter:MVP的P
* utils:工具类
* view:MVP的V
* widget:自定义控件类，并到view下面

更改完的目录结构为：
* model
   * bean
* presenter
* view
   * weight
   * event:主要用于处理界面点击的EventBus的event放在这里,目前根据系统模块分吧：用户，设备，运动数据，社交，每个模块一个event
* base
* utils

第二个任务：先精简抽取代码，再做改动，先精简recyclerview
有Recyclerview的View:
DashboardActivityFragment
WeightDiaryFragment
MeasureHeartRateFragment
DashboardHealthFragment
DashboardFragment
SettingsFragment
HelpFragment
ContactNewFriendFragment
ContactFragment
ContactAddFriendFragment
LCIMConversationFragment
SocialFragment
BaseConversationListFragment
AppMessageMoreFragment
AppMessageManagerFragment
SearchBandFragment
SmartRemindFragment

SettingsAdapter
HealthAdapter
SelectableAdapter
DividerItemDecoration
CardSectionAdapter
SettingsSectionAdapter
HeartRateAdapter
SocialAdapter
DeviceAdapter
WEightDiaryAdapter
ChatAdapter
LoadaMoreAdapter
HelpAdapter
RemindAdapter
CardAdapter
第一步，抽取共性的Adapter (备注：这其实就是适配器设计模式，作用是使原本因接口不匹配而无法一起工作的两个类能够一起工作)
* Adapter的作用是适配指定数据给指定View,这个行为具有共性可以抽取成一个公共类
思路： 
1. 给指定数据给指定View,是一个共性行为，抽取，给什么View赋予什么数据是个特定行为，下发给ViewHodler实现
       * 给Adapter一个指定父类是BaseViewHodler的泛型,利用多态调用BaseViewHodler的子类的产生View
       * 输入数据给Adapter时也用泛型
       * 绑定数据也交给ViewHodler实现，利用多态调用BaseViewHodler的子类的给自己产生的View绑定数据
       * 未解决难点如果不同类型的View item ,就有多个ViewHodler,recyclerview的一个ViewHolder只能配对一个layout布局
2. ViewHodler主要实现两个功能：Creat View 和 Bind Data , 每个非抽象的BaseViewHodler子类都要声明并实现一个静态的变量名为“HOLDER_CREATOR”的的Feild.
3. item的点击事件在ViewHodler中用EventBus处理
4. 为BaseAdapter添加item选择功能，BaseAdapter继承SelectableAdapter类，item选择的具体方法都在SelectableAdapter里面e.g.   
 选择和取消选择调用：adapter.toggleSelection(i);    判断是否选择调用：adapter.isSelected
5. 添加多类型item的Adapter,方法： 复写RecyclerView.adapter中的getItemViewType(int position),给不同item设定viewType，
复写LCIMCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)，根据不同的viewType返回不同的ViewHolder。
即每个不同类型的view都需要有一个viewHolder,ViewHolder自己设定自己的ViewType。多类型item的Adapter排列方式不同，可能需要根据不同的样式设定不同的adapter。S



Adapter的简化：项目中之前用adpter的套用实现recyclerView不同类型item的效果，改成直接用不同的ViewHodler实现


第三个任务：UTC那个恶心的onResult 用EventBus 发出去，减轻BandActivity,和BandService ,合并多余的Fragment, 例如帮助里面说明fragment，一个帮助说明一个fragment实在没必要，样式一样的完全可以合并

第四个任务：把运动统计页面的ViewPagerAdapter抽取出来放在单独的文件里面

第五个任务： 把所以fragment改回V4包的





