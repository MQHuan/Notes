step1 :
Add following code in build.gradle of Project
 classpath "com.tencent.tinker:tinker-patch-grable-plugin:${TINKER_VERSION}"

step2 :
添加依赖
    provided("com.tencent.tinker:tinker-android-anno:${TINKER_VERSION}") { changing = true }
    compile("com.tencent.tinker:tinker-android-lib:${TINKER_VERSION}") { changing = true }
    compile "com.android.support:multidex:1.0.1"

step3 :
在 defaultConfig中添加
 multiDexEnabled true

step4:
复制一堆的gradle代码
注意:
坑1：修改ignoreWarning = true 和 tinkerId = "1.0.0"(这个要和前面一大堆代码中的versionName对应的)
坑2：keystore地址需要修改
signingConfigs {
	release {
		...
		storeFile file("/home/你的release.keystore地址")
        }
        debug {
            storeFile file("/home/mai/.android/debug.keystore")//这是linux默认的，修改成你的keystore地址
        }
}

