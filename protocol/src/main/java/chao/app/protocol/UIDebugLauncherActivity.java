package chao.app.protocal;

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

import chao.app.protocol.UIDebugHelper;
import chao.app.protocol.annotations.DebugClass;
import chao.app.protocol.annotations.DebugSwitchON;
import chao.app.protocol.annotations.MainClass;

/**
 * @author chao.qin
 * @since 2017/3/26
 */

public class UIDebugLauncherActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    static final String EXTRA_TARGET_CLASS = "target";
    static final String EXTRA_MAIN_CLASS = "main";

    private Class mTargetClazz;
    private Class<? extends Activity> mMainClass;

    public static void startDebugActivity(Activity activity, final Class clazz, final Class<? extends Activity> main) {
        Intent intent = new Intent(activity, UIDebugLauncherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(UIDebugLauncherActivity.EXTRA_TARGET_CLASS, clazz);
        intent.putExtra(UIDebugLauncherActivity.EXTRA_MAIN_CLASS, main);
        activity.startActivity(intent);
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean debugOn = false;

        Intent intent = getIntent();
        if (intent != null) {
            mTargetClazz = (Class) intent.getSerializableExtra(EXTRA_TARGET_CLASS);
            mMainClass = (Class<? extends Activity>) intent.getSerializableExtra(EXTRA_MAIN_CLASS);
            if (mTargetClazz != null) {
                debugOn = true;
            }
        }

        if (mTargetClazz == null) {
            mTargetClazz = getTargetClassFromAnnotation();
        }
        if (mMainClass == null) {
            mMainClass = getMainClassFromAnnotation();
        }

        debugOn = debugOn || getDebugSwitchFromAnnotation();

        if (!debugOn) {
            if (!onCreate(savedInstanceState,false)) {
                UIDebugHelper.showUI(this, mMainClass);
                finish();
            }
            return;
        }

        setContentView(R.layout.uidebug_main);
        View layout = findViewById(R.id.uidebug_main_layout);
        TextView debugTip = (TextView) layout.findViewById(R.id.debug_tip);
        String mainName = "null";
        if (mMainClass != null) {
            mainName = mMainClass.getSimpleName();
        }
        debugTip.setText(getString(R.string.debug_tip,mTargetClazz.getSimpleName(),mainName));

        layout.setOnClickListener(this);
        layout.setOnLongClickListener(this);

        UIDebugHelper.showUI(this,mTargetClazz);

        onCreate(savedInstanceState,true);
    }

    /**
     * 替代Activity的onCreate();
     *
     * @param savedInstanceState 对应Activity的savedInstanceState参数
     * @return 如果需要自己处理onCreate()且不需要UIDebugLauncherActivity的默认方式：打开MainClazz,请return true,否则返回false.
     *
     * 比如：你需要开一个Launcher开屏界面并延时5s打开首页，可以通过覆写
     *
     *
     */
    protected boolean onCreate(Bundle savedInstanceState,boolean debugOn) {
        return false;
    }

    private boolean getDebugSwitchFromAnnotation() {
        DebugSwitchON debugSwitch = getClass().getAnnotation(DebugSwitchON.class);
        return debugSwitch != null && debugSwitch.value();
    }

    private Class getMainClassFromAnnotation() {
        MainClass mainClassAnnotation = getClass().getAnnotation(MainClass.class);
        if (mainClassAnnotation != null) {
            return mainClassAnnotation.value();
        }
        return null;
    }

    private Class getTargetClassFromAnnotation() {
        DebugClass targetClassAnnotation = getClass().getAnnotation(DebugClass.class);
        if (targetClassAnnotation != null) {
            return targetClassAnnotation.value();
        }
        return null;
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

    @Override
    public void onClick(View v) {
        UIDebugHelper.showUI(this,mTargetClazz);
    }

    @Override
    public boolean onLongClick(View v) {
        UIDebugHelper.showUI(this,mMainClass);
        return true;
    }
}
