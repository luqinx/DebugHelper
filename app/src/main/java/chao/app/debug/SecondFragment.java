package chao.app.debug;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chao.app.protocol.LogHelper;

/**
 * @author chao.qin
 * @since 2017/3/24
 */

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Second Fragment!");

        //LogHelper测试
        LogHelper.e("chao.qin","this is a error test");
        LogHelper.v("chao.qin","this is a verbose test");
        LogHelper.i("chao.qin","this is a info test");
        LogHelper.w("chao.qin","this is a waring test");
        LogHelper.d("chao.qin","this is a debug test");

        return textView;
    }
}
