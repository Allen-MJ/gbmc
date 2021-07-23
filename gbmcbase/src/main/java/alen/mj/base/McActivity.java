package alen.mj.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import alen.mj.base.adapter.UnitsLevelAdapter;
import alen.mj.base.db.DataHelper;
import alen.mj.base.entry.M01;
import alen.mj.base.entry.UnitslevelEntry;
import alen.mj.base.entry.gwyinfo;
import alen.mj.base.utils.Constants;
import allen.frame.AllenIMBaseActivity;
import allen.frame.tools.Logger;
import allen.frame.tools.StringUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class McActivity extends AllenIMBaseActivity {

    @BindView(R2.id.toolbar)
    Toolbar bar;
    @BindView(R2.id.web)
    WebView wv;
    @BindView(R2.id.side)
    RecyclerView side;
    @BindView(R2.id.drawer)
    DrawerLayout drawer;
    private gwyinfo info;
    private String json;
    private String code, key, name, type, oldSearchStr;
    private String ucode;//B0111
    private UnitsLevelAdapter adapter;
    private List<UnitslevelEntry> list;
    private SharedPreferences shared;

    @Override
    protected boolean isStatusBarColorWhite() {
        return false;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.gwy_mc_layout;
    }

    @Override
    protected void initBar() {
        info = (gwyinfo) getIntent().getSerializableExtra("entry");
        type = getIntent().getStringExtra("type");
        setToolbarTitle(bar,info.getNodeMap().get(gwyinfo.B0101));
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initUI(@Nullable Bundle savedInstanceState) {
        shared = actHelper.getSharedPreferences();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        side.setLayoutManager(manager);
        adapter = new UnitsLevelAdapter(type);
        side.setAdapter(adapter);
    }

    @Override
    protected void addEvent() {
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setItemClickListener(new UnitsLevelAdapter.OnitemClickListener() {
            @Override
            public void itemClick(UnitslevelEntry entry,int position) {
                loadmc();
            }

            @Override
            public void leafClick(UnitslevelEntry entry,int position) {

            }
        });
        loadData();
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = DataHelper.getInstance()
                        .getUnitsLevelList(info.getNodeMap().get(gwyinfo.B0111), type, 0);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private List<M01> mcs;
    private void loadmc(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray array = new JSONArray();
                mcs = DataHelper.getInstance().getPerMcList(ucode, code, key, type);
                int size = mcs==null?0:mcs.size();
                Logger.e("debug", "size:"+size);
                for (int i = 0; i < size;i++) {
                    M01 m = mcs.get(i);
                    JSONObject item = new JSONObject();
                    try {
                        item.put("id", "" + (i+1));
                        item.put("cadrescode", m.getM0100());
                        item.put("cadresname",m.getM0101());
                        Logger.e("debug", "姓名:"+m.getM0101());
                        item.put("levelname", m.getM0102());
                        item.put("oldpost", m.getM0103());
                        item.put("leaddate", m.getM0104());
                        item.put("dczjdydate",m.getM0105());
                        item.put("cadressex", m.getM0106());
                        item.put("nation", m.getM0107());
                        item.put("birthday", m.getM0108());
                        item.put("birthplace",m.getM0109());
                        item.put("fulllearn", m.getM0110());
                        item.put("fullwork", m.getM0111());
                        item.put("jib", m.getM0112());
                        item.put("joinwork", m.getM0113());
                        item.put("addparttime",m.getM0114());
                        item.put("remark", m.getM0115());
                        item.put("img", StringUtils.empty(m.getA5714())?"kq.jpg":"file://"+ Constants.getPhotoPath()+"/"+m.getA5714());
                        item.put("zx", m.getZx());
                        array.put(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                json = StringUtils.replaceJson(array.toString());
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    // 第一次加载
    private void fristLoadmcTopData(){
        Logger.e("debug", ""+(list==null?0:list.size()));
        if((list==null?0:list.size())==0){
            return;
        }
        key = "";
//        searet.setText("");
        code = list.get(0).getCode();
        name = list.get(0).getName();
        if("23".equals(type)){
            ucode = list.get(0).getId();
        }else{
            ucode = info.getNodeMap()
                    .get(gwyinfo.B0111);
        }
        setToolbarTitle(bar,name);
        // 第一个选中
        list.get(0).setCheck(true);
        adapter.notifyDataSetChanged();

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray array = new JSONArray();
                mcs = DataHelper.getInstance(context).getPerMcList(ucode, code, key, type);
                int size = mcs==null?0:mcs.size();
                Logger.e("debug", "size:"+size);
                for (int i = 0; i < size;i++) {
                    M01 m = mcs.get(i);
                    JSONObject item = new JSONObject();
                    try {
                        item.put("id", "" + (i+1));
                        item.put("cadrescode", m.getM0100());
                        item.put("cadresname",m.getM0101());
                        item.put("levelname", m.getM0102());
                        item.put("oldpost", m.getM0103());
                        item.put("leaddate", m.getM0104());
                        item.put("dczjdydate",m.getM0105());
                        item.put("cadressex", m.getM0106());
                        item.put("nation", m.getM0107());
                        item.put("birthday", m.getM0108());
                        item.put("birthplace",m.getM0109());
                        item.put("fulllearn", m.getM0110());
                        item.put("fullwork", m.getM0111());
                        item.put("jib", m.getM0112());
                        item.put("joinwork", m.getM0113());
                        item.put("addparttime",m.getM0114());
                        item.put("remark", m.getM0115());
                        item.put("img", StringUtils.empty(m.getA5714())?"kq.jpg":"file://"+Constants.getPhotoPath()+"/"+m.getA5714());
                        item.put("zx", m.getZx());
                        array.put(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                json = StringUtils.replaceJson(array.toString());
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    adapter.setData(list);
                    handler.sendEmptyMessageDelayed(3, 100);
                    break;
                case 1:
                    wv.addJavascriptInterface(new WebScript(), "weblist");
                    wv.setWebChromeClient(client);
                    wv.setWebViewClient(webViewClient);
                    wv.loadUrl("file:///android_asset/index.html");
                    break;
                case 2:
                    if(shared.getInt("mode", 0)==0){
                        wv.loadUrl("javascript:showGbmc('" + json + "')");
                    }else{
                        wv.loadUrl("javascript:showgbmcpic('" + json + "')");
                    }
                    break;
                case 3:
                    fristLoadmcTopData();
                    break;
                default:
                    break;
            }
        };
    };

    private WebChromeClient client = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    };
    // webview 的帮助加载类
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            handler.sendEmptyMessage(2);
        }
    };

    class WebScript{
        public WebScript(){
        }
        // 开始调用函数跳转
        @JavascriptInterface
        public void showPersonInfo(final int position) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, RmActivity.class);
                    intent.putExtra("code", ucode);
                    intent.putExtra("id", mcs.get(position).getM0100());
                    intent.putExtra("name", mcs.get(position).getM0101());
                    intent.putExtra("key", key);
                    intent.putExtra("ucode", code);
                    intent.putExtra("type", type);
                    intent.putExtra("index", position);
                    startActivity(intent);
                }
            });
        }
    }
}
