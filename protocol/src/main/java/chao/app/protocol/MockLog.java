package chao.app.protocol;

import chao.app.protocol.protocol.ILog;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

class MockLog implements ILog {
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
