package chao.app.debug;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import chao.app.protocol.DebugHelper;
import chao.app.uidebug.UIDebugLauncherActivity;
import chao.app.uidebug.annotations.DebugClass;
import chao.app.uidebug.annotations.DebugSwitchON;
import chao.app.uidebug.annotations.MainClass;

/**
 * @author chao.qin
 * @since 2017/3/30.
 */

@DebugSwitchON(true)
@DebugClass(SecondFragment.class)
@MainClass(SecondActivity.class)
public class AnnotationLauncher extends UIDebugLauncherActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        DebugHelper.showUI(this,SecondFragment.class);
        DebugHelper.showUI(this,SecondActivity.class);
        DebugHelper.showUI(this,SecondSupportFragment.class);

        DebugHelper.showUI(this,SecondFragment.class,new Bundle());
        DebugHelper.showUI(this,SecondActivity.class,new Bundle());
        DebugHelper.showUI(this,SecondSupportFragment.class,new Bundle());


    }
}
