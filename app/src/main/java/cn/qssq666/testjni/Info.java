package cn.qssq666.testjni;

import android.util.Log;

/**
 * Created by qssq on 2018/8/2 qssq666@foxmail.com
 */
public class Info {
    private static final String TAG = "Info";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    String title;

    public Info(String title, int age, long birthday, int sex) {
        this.title = title;
        this.age = age;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Info(String title, int age) {

        Log.w(TAG,"age:"+age+",title:"+title);
        this.title = title;
        this.age = age;
    }

    public Info(int age, int sex) {

        this.age = age;
        this.sex = sex;
    }

    int age;

    public String getNickname() {
        return nickname;
    }


    public  String getNickName(String postfix) {
        Log.w(TAG,"getnickname _arg:"+postfix);
        return "default_nickname"+postfix;
    }



    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    String nickname;
    long birthday;
    int sex;

    public String getArg1() {
        return arg1;
    }

    String arg1;
    String arg2;
    @Override
    public String toString() {
        return "Info{" +
                "title='" + title + '\'' +
                "昵称='" + getNickname() + '\'' +
                "昵称_='" + getNickName("_by") + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", sex=" + sex +
                '}';
    }


    public static void testPringStackTrace(){
        Log.getStackTraceString(new Exception());

    }

    public static Info createObj(){
        return new Info("这是我的标题",15);
    }
}
