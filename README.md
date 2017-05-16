## DebugHelper  调试助手

DebugHelper是一个debug调试工具，目前有辅助界面调试和调试log输出2块功能。DebugHelper可以只编译到debug包中，gradle编译出的release安装包不会包含DebugHelper


#### 1. 辅助界面调试

DebugHelper提供的UIDebugHelper是一个能有效提高界面开发调试效率的工具。
app启动时，UIDebugHelper会直接启动你指定的Activity或者Fragment，而不是通过一步一步点击最后才进入到指定的调试页面。
另外，进入页面按返回键会回退到一个空白页，空白页单击重新进入调试界面，空白页长按会进入到指定主页。
这样的好处有：
   1) 避免了每次启动app后频繁的点击操作，减少了操作耗时。

   2)  ![debug按钮](./images/img_as_debug.png)如果通过AS调试按钮进入到app，app的速度会显著降低，这时候如果有UIDebugHelper,将大大提升调试效率

   3) 通过空白页与调试页面来回切换，可以很方便的调试类，变量或方法的初始化。

   4）使用![attatch按钮](./images/img_as_attach_debug.png)替代![debug按钮](./images/img_as_debug.png)能提升调试效率

##### 请尝试使用UIDebugHelper来调试你的界面，你一定会喜欢上它

#### 2. 调试Log输出
      我们调试过程中经常会通过打印log来查看一些调试信息，可能会有一些调试信息由于没有清理干净一直保留在代码中，这就有可能导致敏感信息被泄漏。
    DebugHelper提供了一个LogHelper类，用法和android.util.Log一样。但是release版本安装包中LogHelper不会打印出任何log，这样就能有效的
    避免敏感信息泄漏的问题。



### 使用方式
[![](https://jitpack.io/v/chaooooooo/DebugHelper.svg)](https://jitpack.io/#chaooooooo/DebugHelper)
第一步:
    在根目录的build.gradle中添加jitpack仓库

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
第二步:
    项目中添加依赖：
 
	dependencies {
	        debugCompile 'com.github.chaooooooo.DebugHelper:DebugHelper:v0.1.8' //使用debugComile可以保证release版本编译的时候这个包不会被编译到apk中
	        compile 'com.github.chaooooooo.DebugHelper:protocol:v0.1.8' //一个轻量的协议
	}


辅助界面调试：
    新建一个Activity并继承 UIDebugLauncherActivity，可以通过注解的方式指定MainClass,DebugClass和调试开关,这样当app启动时将会直接打开debugClass指定的Activity or Fragment
如果配合Android Studio的Instant Run,界面调试将变得非常方便
```
@DebugSwitchON(true)
@DebugClass(SecondFragment.class)
@MainClass(SecondActivity.class)
public class SampleLauncher extends UIDebugLauncherActivity {
}
```

使用LogHelper来输出log不用担心log泄漏问题:

```
//LogHelper测试
LogHelper.e(TAG, "this is a error test");
LogHelper.v(TAG, "this is a verbose test");
LogHelper.i(TAG, "this is a info test");
LogHelper.w(TAG, "this is a waring test");
LogHelper.d(TAG, "this is a debug test");
```

使用DebugHelper.showUI(Class clazz)便捷方法打开一个Activity or Fragment

```
DebugHelper.showUI(this,SecondActivity.class);
DebugHelper.showUI(this,SecondFragment.class);
DebugHelper.showUI(this,SecondSupportFragment.class);


DebugHelper.showUI(this,SecondActivity.class,new Bundle());
DebugHelper.showUI(this,SecondFragment.class,new Bundle());  // Fragment是 android.app.Fragment 的子类
DebugHelper.showUI(this,SecondSupportFragment.class,new Bundle());   //Fragment是 android.support.v4.app.Fragment 的子类
```