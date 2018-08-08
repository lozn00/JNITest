package cn.qssq666.testjni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
                Toast.makeText(MainActivity.this, "xxxx",Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.btn_exit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit1();
                Toast.makeText(MainActivity.this, "test2",Toast.LENGTH_LONG).show();

            }
        });
        findViewById(R.id.btn_exit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit2();
                Toast.makeText(MainActivity.this, "test2",Toast.LENGTH_LONG).show();

            }
        });

        findViewById(R.id.btn_exit3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.exit3();
                Toast.makeText(MainActivity.this, "test2",Toast.LENGTH_LONG).show();

            }
        });
        findViewById(R.id.btn_c_repleace_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = 30;
                testCRepleaceValue(value);
                Toast.makeText(MainActivity.this, "传递后结果:" + value, Toast.LENGTH_SHORT).show();
            }

        });


    }

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
}
