package cn.qssq666.testjni;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

/**
 * Created by qssq on 2018/8/29 qssq666@foxmail.com
 */
public class MainActivity1 extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Dialog dialog=new Dialog(this);

        dialog.show();
    }
}
