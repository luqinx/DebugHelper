package chao.app.protocol;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import chao.app.protocol.protocol.IUIDebug;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

class MockUIDebugHelper implements IUIDebug {
    @Override
    public void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz) {

    }

    @Override
    public void show(Context context, Class clazz) {

    }

    @Override
    public void show(Context context, Class clazz, Bundle bundle) {

    }


}
