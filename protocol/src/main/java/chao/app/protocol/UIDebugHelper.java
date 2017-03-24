package chao.app.protocol;

import android.app.Activity;

import java.lang.reflect.Field;

import chao.app.protocol.protocol.IUIDebug;
import chao.app.protocol.utils.ReflectUtil;


/**
 * @author chao.qin
 * @since 2017/3/23
 */

public class UIDebugHelper {

    private static final String DEBUG_CLASS_NAME = "chao.app.uidebug.UIDebugHelperImp";
    private static final String DEBUG_FIELD_NAME = "debugImp";

    public static DebugInfo newDebugInfo() {
        return new DebugInfo();
    }

    public static class DebugInfo {
        /*
         * DEBUG_CLASS 可以是Activity子类 ,android.app.Fragment子类或android.support.v4.app.Fragment子类
         */
        private Class debugClazz;
        private Class<? extends Activity> mainClazz; //一般指定首页Activity的class，调试也长按可以进入mainClazz指定的activity
        private Activity fromActivity;


        public DebugInfo debugClass(Class _debugClazz) {
            debugClazz = _debugClazz;
            return this;
        }

        public DebugInfo mainClass(Class<? extends Activity> _mainClazz) {
            mainClazz = _mainClazz;
            return this;
        }

        public DebugInfo fromActivity(Activity activity) {
            fromActivity = activity;
            return this;
        }

    }

    /**
     *
     */
    public static void enterDebugMode(DebugInfo info) {
        try {
            Class c = Class.forName(DEBUG_CLASS_NAME);
            Field field = ReflectUtil.getFiled(c, DEBUG_FIELD_NAME);
            IUIDebug debug = ReflectUtil.getValue(null, field);
            if (debug != null) {
                debug.enterDebugMode(info.fromActivity, info.debugClazz, info.mainClazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
