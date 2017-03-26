package chao.app.uidebug;

import chao.app.protocol.protocol.IDebugHelper;
import chao.app.protocol.protocol.ILog;
import chao.app.protocol.protocol.IUIDebug;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

public class DebugHelperProxy implements IDebugHelper{

    public ILog getLogHelper() {
        return new LogHelperImp();
    }

    public IUIDebug getUIDebugHelper() {
        return UIDebugHelperImp.newUIDebug();
    }

//    private static class DebugHelperImp implements IDebugHelper {
//
//        @Override
//        public ILog getLogHelper() {
//            return ;
//        }
//
//        @Override
//        public IUIDebug getUIDebugHelper() {
//            return null;
//        }
//    }
}
