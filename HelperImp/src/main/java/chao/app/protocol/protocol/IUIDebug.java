package chao.app.protocol.protocol;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public interface IUIDebug {
    void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz);

    @Deprecated
    void show(Context context, Class clazz);

    void show(Context context, Class clazz, Bundle bundle);
}
