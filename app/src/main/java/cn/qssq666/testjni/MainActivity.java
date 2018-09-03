package cn.qssq666.testjni;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.qssq666.testjni.aaa.InfoXXX;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("mylib");
    }

    private EditText evPath;
    private EditText evKey;
    private EditText evValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = ",1+5=" + calcAdd(1, 5);
                message += ",a+b=" + calcAddStr("a", "b");
   /*             Info info=new Info(30,0);
                String s = info.toString();*/


                Info info = new Info("xxx", 2);

                String s = info.toString();
                Toast.makeText(MainActivity.this, "" + getMessage() + "," + getMessage1() + message + "," + s, Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btn_throwerror).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.testThrowError(); //libart.so (_ZN3art8CheckJNI10FatalErrorEP7_JNIEnvPKc+648)

            }
        });
        findViewById(R.id.tv_create_obj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info obj = Info.createObj();
                Toast.makeText(MainActivity.this, "obj:" + obj, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_print_stack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Thread.currentThread().pr
                Info.testPringStackTrace();
//            android.util.Log

            }
        });

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit();


            }


        });
        findViewById(R.id.btn_c_exist1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exist1();
                Toast.makeText(MainActivity.this, "xxxx", Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.btn_exit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit1();
                Toast.makeText(MainActivity.this, "test2", Toast.LENGTH_LONG).show();

            }
        });
        findViewById(R.id.btn_exit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit2();
                Toast.makeText(MainActivity.this, "test2", Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.btn_exit3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit3();
                Toast.makeText(MainActivity.this, "test3", Toast.LENGTH_LONG).show();

            }
        });
        findViewById(R.id.btn_c_repleace_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testCRepleaceValue(value);
                Toast.makeText(MainActivity.this, "传递后结果:" + value, Toast.LENGTH_SHORT).show();
            }

        });


        findViewById(R.id.btn_execute_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = 30;
                execute_c();
                Toast.makeText(MainActivity.this, "exec over :", Toast.LENGTH_SHORT).show();


            }
        });

        findViewById(R.id.btn_jvm_ver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jvmVersion = getJVMVersion();
                Toast.makeText(MainActivity.this, "jvm version:" + jvmVersion, Toast.LENGTH_SHORT).show();
            }
        });
        evPath = (EditText) findViewById(R.id.ev_path);
        evKey = (EditText) findViewById(R.id.ev_key);
        evValue = (EditText) findViewById(R.id.ev_value);

        findViewById(R.id.btn_check_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = evPath.getText().toString();
                if (TextUtils.isEmpty(path)) {
                    path = "/data/local/tmp/frida-server";
                }
                boolean exist = checkFileExist(path);
                Toast.makeText(MainActivity.this, "file is exist?" + exist, Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btn_test_moni_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moni_exit();
            }
        });


        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.sendBroadcast(new Intent("HELLO"));
                showToast();
            }
        });

        findViewById(R.id.btn_package).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "apppackage:" + getApplicationPackage(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_read_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = readConfig(evKey.getText().toString());

                Toast.makeText(MainActivity.this, "value:" + s, Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btn_write_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int code = 0;
                try {
                    code = writeConfig(evKey.getText().toString(), evValue.getText().toString());
                    Toast.makeText(MainActivity.this, "set code result:" + code, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "set code fail:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        findViewById(R.id.btn_print_c_str).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, "c:" + printStr(), Toast.LENGTH_SHORT).show();


            }
        });
//        nable to add window -- token null is not for an application


//        testDialog();
    }

    private void testDialog() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] arrs = new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
            this.requestPermissions(arrs, 1);
            askForPermission();
        }
        final Dialog dialog = new Dialog(getApplicationContext(), R.style.dialog_full_x);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        InfoXXX xxx=new InfoXXX(1,1);
        xxx.getAge();
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);// permission denied for window type 2008
        dialog.setContentView(R.layout.dialog_test);
/*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {


            @Override
            public void run() {

                dialog.hide();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.show();
                handler.postDelayed(this, 1800);
            }
        }, 2000);*/

        this.sendBroadcast(new Intent("HELLO"));
    }


    /**
     * 请求用户给予悬浮窗的权限
     */
    public void askForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            } else {
            }
        }
    }

    int value = 30;

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String getMessage();

    public native int calcAdd(int value, int value1);

    public native String calcAddStr(String value, String value1);

    public native String getMessage1();

    public native int exit();

    public native void exist1();

    public native void exit1();

    public native void exit2();

    public native void exit3();

    public native int testCRepleaceValue(int i);

    public native static void testThrowError();

    public native static int getJVMVersion();


    public native int execute_c();

    public native void moni_exit();

    public native boolean checkFileExist(String path);


    public native void showToast();

    public native String readConfig(String key);

    public native int writeConfig(String key, String value) throws Exception;

    public native String getApplicationPackage();

    public native String printStr();

}
