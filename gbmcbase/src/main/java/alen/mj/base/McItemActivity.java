package alen.mj.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.List;

import alen.mj.base.adapter.McItermAdapter;
import alen.mj.base.db.DataHelper;
import alen.mj.base.entry.gwyinfo;
import allen.frame.AllenIMBaseActivity;
import allen.frame.tools.Logger;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class McItemActivity extends AllenIMBaseActivity {
    @BindView(R2.id.toolbar)
    Toolbar bar;
    @BindView(R2.id.recycler)
    RecyclerView recycler;

    private McItermAdapter adapter;
    private List<gwyinfo> list;
    private gwyinfo info;
    private String type;
    private String code,name;
    private int index = 0;
    private String[][] pars;

    @Override
    protected boolean isStatusBarColorWhite() {
        return false;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.gwy_main;
    }

    @Override
    protected void initBar() {
        info = (gwyinfo) getIntent().getSerializableExtra("entry");
        type = getIntent().getStringExtra("type");
        pars = new String[10][2];
        code = info.getNodeMap().get(gwyinfo.B0111);
        name = info.getNodeMap().get(gwyinfo.B0101);
        pars[0][0] = code;
        pars[0][1] = name;
        setToolbarTitle(bar,getTitleByPars());
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initUI(@Nullable Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(context,4);
        recycler.setLayoutManager(manager);
        adapter = new McItermAdapter();
        recycler.setAdapter(adapter);
        loadData();
    }

    @Override
    protected void addEvent() {
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index>0){
                    index-=1;
                    setToolbarTitle(bar,getTitleByPars());
                    loadData();
                }else{
                    finish();
                }
            }
        });
    }

    private String getTitleByPars(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            if(i<=index){
                sb.append("-"+pars[i][1]);
            }
        }
        return sb.toString().replaceFirst("-", "");
    }

    private void loadData(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                list = DataHelper.getInstance().getMcList(pars[index][0]);
                Logger.e("debug", "size:"+list.size());
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    adapter.setData(list);
                    break;
                default:
                    break;
            }
        };
    };
}
