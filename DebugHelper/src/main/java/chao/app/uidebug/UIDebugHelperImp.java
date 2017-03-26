package chao.app.uidebug;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import chao.app.protocol.protocol.IUIDebug;


/**
 * @author chao.qin
 * @since 2017/3/23
 */

class UIDebugHelperImp {

    static DebugImp newUIDebug(){
        return new DebugImp();
    }

    private static class DebugImp implements IUIDebug {

        @Override
        public void enterDebugMode(Activity activity, Class debugClazz, Class<? extends Activity> mainClazz) {
            UIDebugHelperImp.enterDebugMode(activity,debugClazz,mainClazz);
        }
    }

    static void enterDebugMode(final Activity activity, final Class clazz, final Class<? extends Activity> main) {

        activity.setContentView(R.layout.main);
        TextView debugTip = (TextView) activity.findViewById(R.id.debug_tip);
        debugTip.setText(activity.getString(R.string.debug_tip,clazz.getSimpleName(),main.getSimpleName()));
        View layout = activity.findViewById(R.id.main_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(activity, clazz);
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(activity,main);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                return true;
            }
        });
        show(activity, clazz);
    }

    private static void show(Activity activity,Class clazz) {

        if (android.app.Fragment.class.isAssignableFrom(clazz)) {
            showAppFragment(activity,clazz);
        } else if (android.support.v4.app.Fragment.class.isAssignableFrom(clazz)) {
            showSupportFragment(activity,clazz);
        } else if (Activity.class.isAssignableFrom(clazz)) {
            showActivity(activity,clazz);
        }
    }

    private static void showAppFragment(Activity activity, Class fragment) {
        Intent intent = DebugFragmentContainer.buildContainerIntent(activity, fragment);
        activity.startActivity(intent);
    }

    private static void showSupportFragment(Activity activity, Class fragment) {
        Intent intent = DebugSupportFragmentContainer.buildContainerIntent(activity, fragment);
        activity.startActivity(intent);
    }
    private static void showActivity(Activity activity, Class targetActivity) {
        Intent intent = new Intent(activity,targetActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }


}
