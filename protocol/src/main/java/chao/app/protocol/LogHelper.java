package chao.app.protocol;

import chao.app.protocol.protocol.ILog;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public class LogHelper {

    private static final String LOG_CLASS_NAME = "chao.app.uidebug.LogHelperImp";

    private static ILog mLog;

    static {
        new LogHelper();
    }

    private LogHelper() {
        try {
            Class c = Class.forName(LOG_CLASS_NAME);
            mLog = (ILog) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (mLog == null) {
            mLog = new MockLog();
        }
    }

    private class MockLog implements ILog {

        @Override
        public int e(String tag, String log) {
            return 0;
        }

        @Override
        public int v(String tag, String log) {
            return 0;
        }

        @Override
        public int d(String tag, String log) {
            return 0;
        }

        @Override
        public int i(String tag, String log) {
            return 0;
        }

        @Override
        public int w(String tag, String log) {
            return 0;
        }
    }


    public static int e(String tag,String log) {
        return mLog.e(tag,log);
    }

    public static int d(String tag,String log) {
        return mLog.d(tag,log);
    }

    public static int i(String tag,String log) {
        return mLog.i(tag,log);
    }

    public static int w(String tag,String log) {
        return mLog.w(tag,log);
    }

    public static int v(String tag,String log) {
        return mLog.v(tag,log);
    }

}
