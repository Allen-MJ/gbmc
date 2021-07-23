package alen.mj.base;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alen.mj.base.db.DataHelper;
import alen.mj.base.entry.gwyinfo;
import alen.mj.base.utils.ShowZip2DataDBHelpor;
import allen.frame.AllenIMBaseActivity;
import allen.frame.tools.MsgUtils;
import allen.frame.tools.StringUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UpdateActivity extends AllenIMBaseActivity {

    private List<String> files;
    private List<gwyinfo> list;

    @Override
    protected boolean isStatusBarColorWhite() {
        return false;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.gwy_welcome;
    }

    @Override
    protected void initBar() {

    }

    @Override
    protected void initUI(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void addEvent() {
        showProgressDialog("检测本地数据包...");
        loadData();
    }

    /**
     * 查询压缩包文件
     * @return
     */
    private List<String> getGBMCZips(){
        File file = Environment.getExternalStorageDirectory();
        File[] childs = file.listFiles();
        Pattern p = Pattern.compile("^XML\\d{14}.zip$");
        List<String> files = new ArrayList<>();
        if(childs!=null&&childs.length>0){
            for(int i=0;i<childs.length;i++){
                String name = childs[i].getName();
                if(StringUtils.notEmpty(name)){
                    if(name.length()>=21){
                        int start = name.length()-21;
                        Matcher m = p.matcher(name.substring(start));
                        if(m.matches()){
                            files.add(childs[i].getAbsolutePath());
                        }
                    }
                }
            }
            return files;
        }
        return null;
    }

    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = DataHelper.getInstance().getMcList();
                files = getGBMCZips();
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 10:
                    dismissProgressDialog();
                    startActivity(new Intent(context, GwyMainActivity.class));
                            finish();
                    break;
                case 0:
                    dismissProgressDialog();
                    if(files==null||files.size()==0){
                        if(list.size()==0){
                            MsgUtils.showNotOutMDMessage(context, "请先下载数据包!",
                                    "确定", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    }, "", null);
                        }else{
                            sendEmptyMessage(10);
                        }
                    }else{
                        MsgUtils.showNotOutMDMessage(context, "有新的数据包，是否更新?",
                                "更新数据", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        showProgressDialog("更新数据包...");
                                        new ShowZip2DataDBHelpor(UpdateActivity.this)
                                                .doUnZip2AnalysisData(handler,files);
                                    }
                                }, "跳过更新", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sendEmptyMessage(10);
                                    }
                                });
                    }
                    break;
            }
        }
    };
}
