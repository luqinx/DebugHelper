package chao.app.protocol.protocol;

import android.app.Activity;
import android.content.Context;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public interface IUIDebug {
    void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz);

    void show(Context context, Class clazz);
}
