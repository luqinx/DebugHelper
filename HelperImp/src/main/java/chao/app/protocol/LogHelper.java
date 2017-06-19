package chao.app.protocol;

import chao.app.protocol.protocol.ILog;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public class LogHelper {

    private static final String TAG = LogHelper.class.getSimpleName();
    private static ILog mLog = DebugHelper.getLogHelper();

    static {
        if (mLog == null) {
            mLog = new MockLog();
        }
    }


    private static class MockLog implements ILog {

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

    public static int e(String tag, String... logs) {
        return e(tag,appendLogs(logs));
    }

    public static int v(String tag, String... logs) {
        return v(tag,appendLogs(logs));
    }

    public static int d(String tag, String... logs) {
        return d(tag,appendLogs(logs));
    }

    public static int i(String tag, String... logs) {
        return i(tag,appendLogs(logs));
    }

    public static int w(String tag, String... logs) {
        return w(tag,appendLogs(logs));
    }

    public static void debug(String... logs) {
        d(TAG,appendLogs(logs));
    }

    private static String appendLogs(String... logs) {
        if (logs == null || logs.length == 0) {
            return "";
        }
        String result = "";
        for (String log: logs) {
            result =result + ", " + log;
        }
        return result.replaceFirst(", ","");
    }
}
