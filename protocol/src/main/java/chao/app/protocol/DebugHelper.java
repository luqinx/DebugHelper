package chao.app.protocol;

import chao.app.protocol.protocol.IDebugHelper;
import chao.app.protocol.protocol.ILog;
import chao.app.protocol.protocol.IUIDebug;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

class DebugHelper {

    private static IDebugHelper sDebugHelper;

    private static final String DEBUG_HELPER_PROXY = "chao.app.uidebug.DebugHelperProxy";

    static {
        try {
            Class c = Class.forName(DEBUG_HELPER_PROXY);
            sDebugHelper = (IDebugHelper) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static ILog getLogHelper() {
        return sDebugHelper.getLogHelper();
    }

    static IUIDebug getUIDebugHelper() {
        return sDebugHelper.getUIDebugHelper();
    }



}
