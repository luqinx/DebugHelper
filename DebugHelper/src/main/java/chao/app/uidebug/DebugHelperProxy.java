package chao.app.uidebug;

import chao.app.protocol.protocol.IDebugHelper;
import chao.app.protocol.protocol.ILog;
import chao.app.protocol.protocol.IUIDebug;

/**
 *
 * DebugHelperProxy是由反射调用，勿删！！！
 *
 * @author chao.qin
 * @since 2017/3/26
 */

public class DebugHelperProxy implements IDebugHelper{

    public ILog getLogHelper() {
        return new LogHelperImp();
    }

    public IUIDebug getUIDebugHelper() {
        return new UIDebugHelperImp();
    }
}
