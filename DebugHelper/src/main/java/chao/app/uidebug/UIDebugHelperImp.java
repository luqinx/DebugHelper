package chao.app.uidebug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import chao.app.protocol.protocol.IUIDebug;


/**
 * @author chao.qin
 * @since 2017/3/23
 */

class UIDebugHelperImp implements IUIDebug{

    @Override
    public void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz) {
        UIDebugLauncherActivity.startDebugActivity(activity, debugClazz, mainClazz);
    }

    @Override
    public void show(Context context, Class clazz) {

        if (android.app.Fragment.class.isAssignableFrom(clazz)) {
            showAppFragment(context,clazz);
        } else if (android.support.v4.app.Fragment.class.isAssignableFrom(clazz)) {
            showSupportFragment(context,clazz);
        } else if (Activity.class.isAssignableFrom(clazz)) {
            showActivity(context,clazz);
        }
    }

    private static void showAppFragment(Context context, Class fragment) {
        Intent intent = DebugFragmentContainer.buildContainerIntent(context, fragment);
        context.startActivity(intent);
    }

    private static void showSupportFragment(Context context, Class fragment) {
        Intent intent = DebugSupportFragmentContainer.buildContainerIntent(context, fragment);
        context.startActivity(intent);
    }
    private static void showActivity(Context context, Class targetActivity) {
        Intent intent = new Intent(context,targetActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
