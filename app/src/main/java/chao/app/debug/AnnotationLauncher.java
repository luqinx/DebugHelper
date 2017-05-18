package chao.app.debug;

import chao.app.protocal.UIDebugLauncherActivity;
import chao.app.protocol.annotations.DebugClass;
import chao.app.protocol.annotations.DebugSwitchON;
import chao.app.protocol.annotations.MainClass;

/**
 * @author chao.qin
 * @since 2017/3/30.
 */

@DebugSwitchON(true)
@DebugClass(SecondFragment.class)
@MainClass(SecondActivity.class)
public class AnnotationLauncher extends UIDebugLauncherActivity {
}
