package chao.app.debug;

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
}
