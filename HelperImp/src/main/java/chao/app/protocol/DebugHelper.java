package chao.app.protocol;

import android.content.Context;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import chao.app.protocol.protocol.IDebugHelper;
import chao.app.protocol.protocol.ILog;
import chao.app.protocol.protocol.IUIDebug;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

public class DebugHelper {

    private static IDebugHelper sDebugHelper;

    private static final String DEBUG_HELPER_PROXY = "chao.app.uidebug.DebugHelperProxy";

    static {
        try {
            Class c = Class.forName(DEBUG_HELPER_PROXY);
            Constructor constructor = c.getDeclaredConstructor();
            constructor.setAccessible(true);
            sDebugHelper = (IDebugHelper) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    static ILog getLogHelper() {
        if (sDebugHelper == null) {
            return new MockLog();
        }
        return sDebugHelper.getLogHelper();
    }

    static IUIDebug getUIDebugHelper() {
        if (sDebugHelper == null) {
            return new MockUIDebugHelper(); 
        }
        return sDebugHelper.getUIDebugHelper();
    }

    public static void showUI(Context context,Class clazz) {
        showUI(context,clazz,null);
    }

    public static void showUI(Context context, Class clazz, Bundle bundle) {
        getUIDebugHelper().show(context,clazz,bundle);
    }
}
