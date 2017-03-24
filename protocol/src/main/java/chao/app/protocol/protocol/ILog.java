package chao.app.protocol.protocol;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public interface ILog {
    /**
     * error logs
     */
    int e(String tag,String log);

    /**
     * verbose logs
     */
    int v(String tag,String log);

    /**
     * debug logs
     */
    int d(String tag,String log);

    /**
     * info logs
     */
    int i(String tag,String log);

    /**
     * warning logs
     */
    int w(String tag,String log);
}
