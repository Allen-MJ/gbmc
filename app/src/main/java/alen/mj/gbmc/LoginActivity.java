package alen.mj.gbmc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import alen.mj.base.UpdateActivity;
import alen.mj.base.db.DataHelper;
import alen.mj.base.utils.Constants;
import allen.frame.AllenIMBaseActivity;
import allen.frame.AllenManager;
import allen.frame.tools.EncryptUtils;
import allen.frame.tools.MsgUtils;
import allen.frame.tools.StringUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class LoginActivity extends AllenIMBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar bar;
    @BindView(R.id.psw_first_tv)
    AppCompatEditText pswFirstTv;
    @BindView(R.id.psw_second_tv)
    AppCompatEditText pswSecondTv;
    @BindView(R.id.psw_third_tv)
    AppCompatEditText pswThirdTv;
    @BindView(R.id.psw_forth_tv)
    AppCompatEditText pswForthTv;
    @BindView(R.id.psw_fifth_tv)
    AppCompatEditText pswFifthTv;
    @BindView(R.id.psw_sixth_tv)
    AppCompatEditText pswSixthTv;
    @BindView(R.id.number_one_tv)
    AppCompatTextView numberOneTv;
    @BindView(R.id.number_two_tv)
    AppCompatTextView numberTwoTv;
    @BindView(R.id.number_three_tv)
    AppCompatTextView numberThreeTv;
    @BindView(R.id.number_four_tv)
    AppCompatTextView numberFourTv;
    @BindView(R.id.number_five_tv)
    AppCompatTextView numberFiveTv;
    @BindView(R.id.number_six_tv)
    AppCompatTextView numberSixTv;
    @BindView(R.id.number_seven_tv)
    AppCompatTextView numberSevenTv;
    @BindView(R.id.number_eight_tv)
    AppCompatTextView numberEightTv;
    @BindView(R.id.number_nine_tv)
    AppCompatTextView numberNineTv;
    @BindView(R.id.all_null_tv)
    AppCompatTextView allNullTv;
    @BindView(R.id.number_zero_tv)
    AppCompatTextView numberZeroTv;
    @BindView(R.id.delete_psw_tv)
    AppCompatTextView deletePswTv;

    private String logpsw;
    private SharedPreferences shared;
    private AppCompatEditText[] inputs;

    public static final int REQUEST_CAMERA_PERMISSION = 1003;

    @Override
    protected boolean isStatusBarColorWhite() {
        return false;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBar() {
        AllenManager.init(getApplication());
        DataHelper.init(getApplicationContext());
        setToolbarTitle(bar,"干部名册");
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initUI(@Nullable Bundle savedInstanceState) {
        shared = actHelper.getSharedPreferences();
        inputs = new AppCompatEditText[]{
                pswFirstTv,pswSecondTv,pswThirdTv,pswForthTv,pswFifthTv,pswSixthTv
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CAMERA_PERMISSION);
            return;
        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION: {
                if (checkIsOk(grantResults)) {
                } else {
                    finish();
                }
                break;
            }
        }
    }

    private boolean checkIsOk(int[] grantResults) {
        boolean isok = true;
        for (int i : grantResults) {
            isok = isok && (i == PackageManager.PERMISSION_GRANTED);
        }
        return isok;
    }

    @Override
    protected void addEvent() {
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgUtils.showMDMessage(context, "温馨提示", "退出应用程序?", "确定", new DialogInterface.OnClickListener() {
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

    @OnClick({R.id.number_one_tv, R.id.number_two_tv, R.id.number_three_tv, R.id.number_four_tv,
            R.id.number_five_tv, R.id.number_six_tv, R.id.number_seven_tv, R.id.number_eight_tv,
            R.id.number_nine_tv, R.id.all_null_tv, R.id.number_zero_tv, R.id.delete_psw_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.number_one_tv:
                inputPsw("1");
                break;
            case R.id.number_two_tv:
                inputPsw("2");
                break;
            case R.id.number_three_tv:
                inputPsw("3");
                break;
            case R.id.number_four_tv:
                inputPsw("4");
                break;
            case R.id.number_five_tv:
                inputPsw("5");
                break;
            case R.id.number_six_tv:
                inputPsw("6");
                break;
            case R.id.number_seven_tv:
                inputPsw("7");
                break;
            case R.id.number_eight_tv:
                inputPsw("8");
                break;
            case R.id.number_nine_tv:
                inputPsw("9");
                break;
            case R.id.all_null_tv:
                for(int i=0;i<6;i++){
                    inputs[i].setText("");
                }
                break;
            case R.id.number_zero_tv:
                inputPsw("0");
                break;
            case R.id.delete_psw_tv:
                deletePsw();
                break;
        }
    }

    private void inputPsw(String number){
        int i=0;
        while (i<6&&StringUtils.notEmpty(inputs[i].getText().toString())) {
            i=i+1;
        }
        if(i>5){
            return;
        }else{
            inputs[i].setText(number);
            if(i==5){
                checkPsw();
            }
        }
    }

    private void checkPsw(){
        logpsw = shared.getString(Constants.CHECK_CODE_1, "");
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<6;i++){
            sb.append(inputs[i].getText().toString().trim());
        }
        String psw = EncryptUtils.MD5Encoder(sb.toString());
        if(StringUtils.empty(logpsw)){
            SharedPreferences.Editor editor = shared.edit();
            editor.putString(Constants.CHECK_CODE_1, psw);
            editor.commit();
            startActivity(new Intent(context, UpdateActivity.class));
            finish();
        }else if(psw.equals(logpsw)){
            startActivity(new Intent(context, UpdateActivity.class));
            finish();
        }else{
            MsgUtils.showShortToast(context,"密码输入错误!");
            for(int i=0;i<6;i++){
                inputs[i].setText("");
            }
        }
    }

    private void deletePsw(){
        int i=0;
        while (i<6&&StringUtils.notEmpty(inputs[i].getText().toString())) {
            i=i+1;
        }
        if(i==0){
            return;
        }else{
            inputs[i-1].setText("");
        }
    }

    @Override
    public void onBackPressed() {
        MsgUtils.showMDMessage(context, "温馨提示", "退出应用程序?", "确定", new DialogInterface.OnClickListener() {
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
}