package com.bawei.asus.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {

    private CheckBox quanxuan;
    private ListView listview;
    private CheckAdapter checkAdapter;
    private Button fanxuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quanxuan = (CheckBox) findViewById(R.id.quanxuan);
        listview = (ListView) findViewById(R.id.listview);
        fanxuan = (Button) findViewById(R.id.fanxuan);


        String[] list = {
                "孙悟空", "猪八戒", "唐玄奘", "沙悟净",
                "贾宝玉", "林黛玉", "薛宝钗", "刘姥姥",
                "鲁智深", "宋江", "武松", "李逵",
                "刘备", "关羽", "张飞", "吕布", "谢天谢地"
        };


        checkAdapter = new CheckAdapter(getApplicationContext(), list, false);
        listview.setAdapter(checkAdapter);
        Button btn = (Button) findViewById(R.id.yincang);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        quanxuan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean flag = quanxuan.isChecked();

                for (int i = 0; i < checkAdapter.getSelect().size(); i++) {
                    checkAdapter.getSelect().set(i, flag);
                }
                checkAdapter.notifyDataSetChanged();
            }
        });

        fanxuan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!checkAdapter.getSelect().contains(true)) {
                    // check_all.setChecked(false);
                    Toast.makeText(getApplicationContext(), "请选择0", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < checkAdapter.getSelect().size(); i++) {
                        if (checkAdapter.getSelect().get(i)) {
                            checkAdapter.getSelect().set(i, false);
                        } else {
                            checkAdapter.getSelect().set(i, true);
                        }
                    }
                    if (!checkAdapter.getSelect().contains(true)) {
                        quanxuan.setChecked(false);
                    }
                }
                checkAdapter.notifyDataSetChanged();

            }
        });
    }


    private class CheckAdapter extends BaseAdapter {
        private String[] list;
        private Context context;
        private boolean isHide;
        private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

        public CheckAdapter(Context context, String[] list, Boolean isHide) {
            // TODO Auto-generated constructor stub
            this.list = list;
            this.context = context;
            this.isHide = isHide;
            // 初始化
            for (int i = 0; i < list.length; i++) {
                getSelect().add(false);
            }
        }

        public void setHide(boolean isHide) {
            this.isHide = isHide;
        }


        private List<Boolean> getSelect() {
            return linkedList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item, null);
            }
            CheckBox ck = (CheckBox) convertView.findViewById(R.id.ck);
            TextView tView = (TextView) convertView.findViewById(R.id.tv);

            tView.setText(list[position]);
            tView.setTextColor(Color.BLACK);
            ck.setTextColor(Color.BLACK);

            ck.setChecked(linkedList.get(position));
            // 不能用onCheckChangedListner()复用的时候
            ck.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    linkedList.set(position, !linkedList.get(position));
                    if (linkedList.contains(false)) {
                        quanxuan.setChecked(false);
                    } else {
                        quanxuan.setChecked(true);
                    }
                    // 刷新
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
