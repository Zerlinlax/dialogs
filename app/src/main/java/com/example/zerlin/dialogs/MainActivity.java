package com.example.zerlin.dialogs;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setView();
    }

    private void initView(){
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonX = findViewById(R.id.buttonX);
    }

    private void setView(){
        SetOnClickListener setOnClickListener = new SetOnClickListener();
        button1.setOnClickListener(setOnClickListener);
        button2.setOnClickListener(setOnClickListener);
        button3.setOnClickListener(setOnClickListener);
        button4.setOnClickListener(setOnClickListener);
        button5.setOnClickListener(setOnClickListener);
        button6.setOnClickListener(setOnClickListener);
        button7.setOnClickListener(setOnClickListener);
        button8.setOnClickListener(setOnClickListener);
        button9.setOnClickListener(setOnClickListener);
        buttonX.setOnClickListener(setOnClickListener);

    }

    private class SetOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.button1:
                    showNormalDialog_1();
                    break;
                case R.id.button2:
                    showNormalDialog_2();
                    break;
                case R.id.button3:
                    showListDialog();
                    break;
                case R.id.button4:
                    showSingleDialog();
                    break;
                case R.id.button5:
                    showMultiDialog();
                    break;
                case R.id.button6:
                    showProgressDialog_1();
                    break;
                case R.id.button7:
                    showProgressDialog_2();
                    break;
                case R.id.button8:
                    showInputDialog();
                    break;
                case R.id.button9:
                    //自定义一个类继承于Dialog类，构造器中绑定布局
                    //设定自定义对话框的风格（不显示标题栏，不显示背景）
                    //设置按钮点击事件
                    //实例化对话框，并显示
                    MyDialog myDialog = new MyDialog(MainActivity.this);
                    myDialog.show();
                    break;
                case R.id.buttonX:
                    showArrayDialog();

            }
        }


    }

    private void showNormalDialog_1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //返回值又是当前的builder对象，所以可以连起来
        builder.setTitle("选项").setMessage("您是否退出当前程序")
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        } ).setNeutralButton("取消",null).show();
    }

    private void showNormalDialog_2(){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("选项");
        dialog.setMessage("请打分");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"5分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "感谢您的支持", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "3分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "再见", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void showListDialog(){
        final String[] arrays = {"5分","3分","1分"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选项")
                //.setMessage("请给我们的服务打分")，列表对话框没有setMessage的
                .setItems(arrays, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您选中了"+arrays[i], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showSingleDialog(){
        final int[] index = {0};
        final String[] arrays = {"未闻花名","妄想学生会","干物妹小埋","加速世界","白色相簿"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您最喜欢的动漫是:")
                .setSingleChoiceItems(arrays, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        index[0] = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您最喜欢的动漫是"+arrays[index[0]], Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showMultiDialog(){
        final String[] games = {"英雄联盟","逆战","穿越火线","反恐精英go","绝地求生","QQ飞车"};
        final boolean[] booleans = {false,false,false,false,false,false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您喜欢的网络游戏有那些？")
                .setMultiChoiceItems(games, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        String string;
                        if(b) {
                            string = "喜欢";
                        }else{
                            string = "不喜欢";
                        }
                        Toast.makeText(MainActivity.this, "您"+string+games[i], Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder("您喜欢的游戏有：");
                        //AS自动帮我们修改了booleans的值
                        for (int j = 0;j<booleans.length;j++) {
                            if(booleans[j]){
                                stringBuilder.append(games[j]).append(" ");
                            }
                        }
                        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showProgressDialog_1(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("我是一个圆形等待对话框");
        dialog.setMessage("加载中...");
        dialog.setCancelable(true);//默认为true的
        dialog.show();
        //dialog.dismiss();

    }

    private void showProgressDialog_2(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("下载中");
        dialog.setMessage("请等待");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(false);//默认为false不模糊
        dialog.setCancelable(false);
        dialog.show();
        //dialog.setProgress(30);//必须在show()的下面，否则没用
        new Thread(){
            @Override
            public void run(){
                super.run();
                for(int i = 0;i < 100;i++){
                    dialog.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }.start();

    }

    private void showInputDialog(){
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("输入你的名字")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您输入的是"+editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showArrayDialog(){
        final String[] language = {"Java","C++","Python","JavaScript","Kotlin"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, R.layout.layout,R.id.text,language);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(R.drawable.suki)//用于在title上添加图片
                .setTitle("你最喜欢的编程语言:")
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您选择了"
                                +language[i], Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

    }
}
