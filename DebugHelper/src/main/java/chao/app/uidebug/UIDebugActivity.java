package chao.app.uidebug;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

public final class UIDebugActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    static final String EXTRA_TARGET_CLASS = "target";
    static final String EXTRA_MAIN_CLASS = "main";

    private Class mTargetClazz;
    private Class<? extends Activity> mMainClass;

    static void startDebugActivity(Activity activity, final Class clazz, final Class<? extends Activity> main) {
        Intent intent = new Intent(activity, UIDebugActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(UIDebugActivity.EXTRA_TARGET_CLASS, clazz);
        intent.putExtra(UIDebugActivity.EXTRA_MAIN_CLASS, main);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        mTargetClazz = (Class) intent.getSerializableExtra(EXTRA_TARGET_CLASS);
        mMainClass = (Class<? extends Activity>) intent.getSerializableExtra(EXTRA_MAIN_CLASS);

        setContentView(R.layout.uidebug_main);
        View layout = findViewById(R.id.main_layout);
        TextView debugTip = (TextView) layout.findViewById(R.id.debug_tip);
        debugTip.setText(getString(R.string.debug_tip,mTargetClazz.getSimpleName(),mMainClass.getSimpleName()));

        layout.setOnClickListener(this);
        layout.setOnLongClickListener(this);

        show(this,mTargetClazz);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(R.string.alter_message_exit_confirm)
                    .setTitle(R.string.alter_title_exit_confirm)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .create();
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    private static void show(Activity activity, Class clazz) {

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

    @Override
    public void onClick(View v) {
        show(this,mTargetClazz);
    }

    @Override
    public boolean onLongClick(View v) {
        if (mMainClass == null) {
            return false;
        }
        Intent intent = new Intent(this,mMainClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return true;
    }
}
