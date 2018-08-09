package cn.qssq666.testjni;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("mylib");
    }

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
        final EditText evPath = (EditText) findViewById(R.id.ev_path);

        findViewById(R.id.btn_check_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = evPath.getText().toString();
                if(TextUtils.isEmpty(path)){
                    path="/data/local/tmp/frida-server";
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
                showToast();
            }
        });

        findViewById(R.id.btn_package).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"apppackage:"+getApplicationPackage(), Toast.LENGTH_SHORT).show();
            }
        });
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


    public native  void showToast();
    public native  String getApplicationPackage();

}
