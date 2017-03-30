package chao.app.uidebug;

import android.app.Activity;

import chao.app.protocol.protocol.IUIDebug;


/**
 * @author chao.qin
 * @since 2017/3/23
 */

class UIDebugHelperImp implements IUIDebug{

    @Override
    public void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz) {
        UIDebugLauncherActivity.startDebugActivity(activity, debugClazz, mainClazz);
    }
}
