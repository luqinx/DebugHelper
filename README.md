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
第一步： 添加DebugHelper和protocol两个Module到你的项目，并添加依赖：
```
 ...

 compile project(':protocol')

 debugCompile project(':DebugHelper') //DebugHelper使用debugCompile而不是compile以确保不会被编译到release版本中
 ...
```

第二步： 在launcher activity的onCreate中启动uidebug模式

```
     /*
      * DEBUG_CLASS 可以是Activity子类 ,android.app.Fragment子类或android.support.v4.app.Fragment子类
      */
      private static final Class DEBUG_CLASS = SecondFragment.class;    // android.app.Fragment 的子类
  //    private static final Class DEBUG_CLASS = SecondActivity.class;  //或 Activity 的子类
  //    private static final Class DEBUG_CLASS = SecondSupportFragment.class;  //或 android.support.v4.app.Fragment 的子类


  private static final boolean UI_DEBUG_ENABLED = BuildConfig.DEBUG && true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LogHelper.e("chao.qin","this is a error test");
        LogHelper.v("chao.qin","this is a verbose test");
        LogHelper.i("chao.qin","this is a info test");
        LogHelper.w("chao.qin","this is a waring test");
        LogHelper.d("chao.qin","this is a debug test");

        UIDebugHelper.DebugInfo debugInfo = UIDebugHelper.newDebugInfo()
                .debugClass(DEBUG_CLASS)  // 要调试的界面，可以是activity或者fragment
                .fromActivity(this)       //提供context
                .mainClass(MainActivity.class); // 一般指定首页Activity的class，调试也长按可以进入mainClazz指定的activity


        if (UI_DEBUG_ENABLED) {
            UIDebugHelper.enterDebugMode(debugInfo);
            return;
        }

        //这里做开屏界面等
        MainActivity.show(this);
        finish();
    }
```
