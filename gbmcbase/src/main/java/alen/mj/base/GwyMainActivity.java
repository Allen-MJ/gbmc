package alen.mj.base;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.List;

import alen.mj.base.adapter.McAdapter;
import alen.mj.base.db.DataHelper;
import alen.mj.base.entry.gwyinfo;
import allen.frame.AllenIMBaseActivity;
import allen.frame.AllenManager;
import allen.frame.tools.Logger;
import allen.frame.tools.MsgUtils;
import allen.frame.tools.OnAdapterItemClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class GwyMainActivity extends AllenIMBaseActivity {

    @BindView(R2.id.toolbar)
    Toolbar bar;
    @BindView(R2.id.recycler)
    RecyclerView recycler;

    private McAdapter adapter;
    private List<gwyinfo> list;
    private int[] colors;

    @Override
    protected boolean isStatusBarColorWhite() {
        return false;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.gwy_main;
    }

    @Override
    public void onBackPressed() {
        MsgUtils.showNotOutMDMessage(context, "退出应用程序?", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AllenManager.getInstance().exitApp();
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void initBar() {
        setToolbarTitle(bar,getString(R.string.app_name));
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initUI(@Nullable Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(context,5);
        recycler.setLayoutManager(manager);
        colors = new int[]{
                Color.parseColor("#44bdb3"),Color.parseColor("#d29e25"),Color.parseColor("#97aa1a"),
                Color.parseColor("#f67402"),Color.parseColor("#49b73c"),Color.parseColor("#0aa5ca"),
                Color.parseColor("#78bab9"),Color.parseColor("#c70307"),Color.parseColor("#da6542"),
                Color.parseColor("#15add4"),Color.parseColor("#97aa1a"),Color.parseColor("#79be30"),
                Color.parseColor("#2a82ec"),Color.parseColor("#0fa430"),Color.parseColor("#5942b4"),
                Color.parseColor("#b82c59"),Color.parseColor("#a316a2"),Color.parseColor("#656fc9"),
                Color.parseColor("#2988ff"),Color.parseColor("#4ab60b"),Color.parseColor("#009a16"),
                Color.parseColor("#906e40"),Color.parseColor("#6e09bd"),Color.parseColor("#ae642d"),
        };
        adapter = new McAdapter(colors);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void addEvent() {
        loadData();
        adapter.setItemClickListener(new OnAdapterItemClickListener<gwyinfo>() {
            @Override
            public void itemClick(View view, int index, gwyinfo gwyinfo) {
                String type = gwyinfo.getNodeMap().get(gwyinfo.TYPE);
                if("23".equals(type)){
                    startActivity(new Intent(context, McActivity.class)
                            .putExtra("entry", gwyinfo)
                            .putExtra("type", type));
                }else{
                    if("0".equals(gwyinfo.getNodeMap().get(gwyinfo.NUM))){
                        startActivity(new Intent(context, McActivity.class)
                                .putExtra("entry", gwyinfo)
                                .putExtra("type", type));
                    }else{
                        startActivity(new Intent(context, McItemActivity.class)
                                .putExtra("entry", gwyinfo)
                                .putExtra("type", type));
                    }
                }
            }
        });
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgUtils.showNotOutMDMessage(context, "退出应用程序?", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        AllenManager.getInstance().exitApp();
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void loadData(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                list = DataHelper.getInstance().getMcList();
                Logger.e("debug", "size:"+list.size());
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    adapter.setData(list);
                    break;
            }
        }
    };

}
