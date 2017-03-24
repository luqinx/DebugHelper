package chao.app.uidebug;

import android.util.Log;

import chao.app.protocol.protocol.ILog;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public class LogHelperImp implements ILog {

    @Override
    public int e(String tag, String log) {
        return Log.e(tag, log);
    }

    @Override
    public int v(String tag, String log) {
        return Log.v(tag, log);
    }

    @Override
    public int d(String tag, String log) {
        return Log.d(tag, log);
    }

    @Override
    public int i(String tag, String log) {
        return Log.i(tag, log);
    }

    @Override
    public int w(String tag, String log) {
        return Log.w(tag, log);
    }
}
