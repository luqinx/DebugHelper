package chao.app.debug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import chao.app.protocol.LogHelper;
import chao.app.protocol.UIDebugHelper;


/**
 * @author chao.qin
 * @since 2017/3/24
 */

public class Launcher extends AppCompatActivity {

    /*
    * DEBUG_CLASS 可以是Activity子类 ,android.app.Fragment子类或android.support.v4.app.Fragment子类
    */
//    private static final Class DEBUG_CLASS = SecondFragment.class;    // android.app.Fragment 的子类
    private static final Class DEBUG_CLASS = SecondActivity.class;  //或 Activity 的子类
//    private static final Class DEBUG_CLASS = SecondSupportFragment.class;  //或 android.support.v4.app.Fragment 的子类

    private static final boolean UI_DEBUG_ENABLED = BuildConfig.DEBUG && true;  //可以通过设置UI_DEBUG_ENABLED启用和关闭UI调试


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
                .mainClass(MainActivity.class); // 长按调试空白页可以进入mainClazz指定的activity,这里指定你首页Activity的class


        if (UI_DEBUG_ENABLED) {
            UIDebugHelper.enterDebugMode(debugInfo);
            return;
        }

        //这里做开屏界面等
        MainActivity.show(this);
        finish();
    }
}
