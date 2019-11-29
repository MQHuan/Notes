简单说 就是用 bsdiff 生成 两个包的差分包，用bspatch合成旧包和差分包就是新包
android端调用bspatch就需要用源码通过jni的方式调用


Android App增量更新实现
什么是增量更新？
增量更新就是原有app的基础上只更新发生变化的地方，其余保持不变。

增量更新的原理
通过某种算法找出新版本和旧版本不一样的地方（这个过程也叫做差分），然后将不一样的地方抽取出来形成所谓的更新补丁（patch），也称之为差分包。客户端在检测到更新的时候，只需要下载差分包到本地，然后将差分包合并至本地的安装包，形成新版本的安装包，文件校验通过后再执行安装即可。

差分包的生成与合并



如何生成差分包以及合并差分包
借助开源库bsdiff来生成差分包和合并差分包。bsdiff下载地址：https://www.pokorra.de/coding/bsdiff.html
先下载bsdiff_win_exe.zip，解压到本地。

然后，我们打出一个基准安装包old.apk。对源码做修改后，再打出一个新的安装包new.apk。old.apk相当于老版本的应用，而new.apk相当于新版本的应用。接下来，我们利用bsdiff来生成差分包patch.patch

生成差分包
将上面的old.apk和new.apk放入bsdiff解压后的目录，然后在控制台中执行命令bsdiff old.apk new.apk patch.patch,稍等一会便可以生成差分包patch.patch，如下


合并差分包
合并old.apk和patch.patch，生成新的安装包new.apk。只要此处合并出来的new.apk和上面我们自己打出来的new.apk一样，那么就可以认为它就是我们需要的新版本安装包。
我们来看看如何合并。将old.apk和patch.patch放入bsdiff文件夹，合并之前为： 

然后执行命令bspatch old.apk new.apk patch.patch，稍等一会之后便可以看到合并出的new.apk.如下：

合并出来的new.apk应该和我们自己打出来的new.apk是一模一样的，这可以通过验证两者的md5来认定。
这就是增量更新的整个过程。

App端增量更新实践
Android App客户端实现增量更新需要使用ndk开发，app要自行编译bspatch.c来实现合并差分包。这里我们首先要下载bsdiff的源码以及bszip的源码。
1.编写PatchUtils类
PatchUtils中只有一个natvie方法patch(String oldApkPath,String newApkPath,String patchPath)用于实现增量包的合并：
2.public class PatchUtils {

   static {
      System.loadLibrary("bsdiff-lib");
   }

   public static  native int patch(String oldApkPath, String newApkPath, String patchPath);
}
3.编写C代码
在实现PatchUtils之前，我们需要将bspatch.c以及bzip的相关代码拷贝到jni目录下（bzip只保留.h头文件和.c文件）。并将bspatch.c中的main()方法名修改为applypatch ()，并且修改其中bzip的引入头为#include "bzip2/bzlib.h".目录结构如下：


接下来我们就可以在PatchUtils.c中实现相关的代码了：
JNIEXPORT jint JNICALL Java_com_urovo_jniinterface_PatchUtils_patch
  (JNIEnv *env, jclass cls,
         jstring old, jstring new, jstring patch){
   int argc = 4;
   char * argv[argc];
   argv[0] = "bspatch";
   argv[1] = (char*) ((*env)->GetStringUTFChars(env, old, 0));
   argv[2] = (char*) ((*env)->GetStringUTFChars(env, new, 0));
   argv[3] = (char*) ((*env)->GetStringUTFChars(env, patch, 0));

   printf("old apk = %s \n", argv[1]);
   printf("patch = %s \n", argv[3]);
   printf("new apk = %s \n", argv[2]);

   int ret = applypatch(argc, argv);
   printf("patch result = %d ", ret);
   (*env)->ReleaseStringUTFChars(env, old, argv[1]);
   (*env)->ReleaseStringUTFChars(env, new, argv[2]);
   (*env)->ReleaseStringUTFChars(env, patch, argv[3]);
   return ret;
}
CMake ndk编译配置：
# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             bsdiff-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/jni/com_urovo_jniinterface_PatchUtils.c
             src/main/jni/bzip2/blocksort.c
             src/main/jni/bzip2/bzip2.c
             src/main/jni/bzip2/bzip2recover.c
             src/main/jni/bzip2/bzlib.c
             src/main/jni/bzip2/compress.c
             src/main/jni/bzip2/crctable.c
             src/main/jni/bzip2/decompress.c
             src/main/jni/bzip2/huffman.c
             src/main/jni/bzip2/randtable.c
             )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

include_directories(src/main/jni/)

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       bsdiff-lib

                       # Links the target library to the log library
                       # included in the NDK.
                      ${log-lib} )

最后在app moudle中的build.gradle中添加ndk配置
//ndk配置
        ndk {
            //选择要添加的对应cpu类型的.so库。
            abiFilters 'armeabi', 'armeabi-v7a'
            // 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
        }

4.合并差分包
上面的步骤做完之后，就可以通过PatchUtils.patch()来合并当前安装包和差分包了。
这里，我们假设差分包已经从服务器下载到本地了。
首先来看如何获取当前安装包。我们安装的应用通常在、data/app下，可以通过一下代码获取其路径：
public static String getApkInstalledSrc(){
    return BaseApplication.context().getApplicationInfo().sourceDir;
}

下面就可以通过PatchUtils.patch(String oldApkPath,String newApkPath,String pathPath)来进行合并了。此处需要注意两点：
1.合并的地方建议放在外置存储（SDcard）当中
2.合并的过程比较耗时，需要放到子线程中进行。
5.安装
任何更新包在下载完成后首先要做的就是进行ＭＤ５校验，以便确认该更新包是正规途径下载而来的。同样，对于合并之后的更新包，首先要做的事情也是进行ＭＤ5校验，校验通过之后，再进行安装：
public static void installAPK(Context context, File file) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.fromFile(file),
            "application/vnd.android.package-archive");
    context.startActivity(intent);
}
至此，增量更新已经完成。现在可以把增量包以及合并之后的安装包进行删除了。