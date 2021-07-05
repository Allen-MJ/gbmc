package allen.frame;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import allen.frame.tools.StringUtils;
import allen.frame.widget.FloatingView;
import allen.frame.widget.MaterialRefreshLayout;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

public abstract class AllenBaseActivity extends AppCompatActivity {
	
	public ActivityHelper actHelper;
	private ProgressDialog dialog;
	public Context context = this;
	private AppCompatTextView titleat;
	private FloatingView floatingView;
	private int resId = 0;
	private boolean isShow = true;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		AllenManager.init(getApplication());
		actHelper = new ActivityHelper(this);
		AllenManager.getInstance().addActivity(this);
		super.onCreate(savedInstanceState);
		if(isStatusBarColorWhite()){
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			}
		}
		setContentView(getLayoutResID());
		if(isShow){
			floatingView = new FloatingView(this);
			floatingView.showFloat();
			floatingView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					startActivity(new Intent(context,WebActivity.class).putExtra("_Key_3",false)
							.putExtra("_Key_1","智能客服")
							.putExtra("_Key_2","https://aitongliang.sobot.com/chat/h5/v2/index.html?sysnum=c4397d34aa42432caba6abb1ffd43d2b"));
				}
			});
			Glide.with(getApplicationContext()).load(resId==0?R.drawable.ic_logo_robort:resId)
					.into(floatingView.CircleImageView());
		}
		initBar();
		initUI(savedInstanceState);
		addEvent();
	}

	@Override
	protected void onResume() {
		if(isShow){
			if(floatingView!=null){
				floatingView.update();
			}
		}
		super.onResume();
	}

	protected void setShowFloating(boolean isShow,int resId){
		this.isShow = isShow;
		this.resId = resId;
	}

	protected abstract boolean isStatusBarColorWhite();

	protected abstract int getLayoutResID();
	
	protected abstract void initBar();

	protected abstract void initUI(@Nullable Bundle savedInstanceState);

	protected abstract void addEvent();

	protected void setToolbarTitle(Toolbar bar,CharSequence title){
		titleat = findViewById(R.id.title);
		if(StringUtils.notEmpty(title.toString())){
			bar.setTitle("");
			titleat.setText(title);
		}
	}

	/**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
	protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
	
	@Override
	protected void onDestroy() {
		AllenManager.getInstance().closeActivity(this);
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
			dialog = null;
		}
		if (floatingView != null) {
			floatingView.dismissFloatView();
			floatingView = null;
		}
		super.onDestroy();
	}
	public void showProgressDialog(String msg){
		if(dialog==null?true:!dialog.isShowing()){
			dialog = new ProgressDialog(this, R.style.Allen_Dialog_Theme);
			dialog.setMessage(StringUtils.notEmpty(msg)?msg:"正在处理,请稍等...");
			dialog.setCancelable(false);
			dialog.show();
			dialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.dialog_bar));
		}else{
			dialog.setMessage(StringUtils.notEmpty(msg)?msg:"正在处理,请稍等...");
		}
	}
	@SuppressWarnings("deprecation")
	public void showOldProgressDialog(String msg){
		if(dialog==null||!dialog.isShowing()){
			dialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
			dialog.setMessage(StringUtils.notEmpty(msg)?msg:"正在处理,请稍等...");
			dialog.setCancelable(false);
			dialog.show();
			dialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.dialog_bar));
		}else{
			dialog.setMessage(StringUtils.notEmpty(msg)?msg:"正在处理,请稍等...");
		}
	}
	public void dismissProgressDialog(){
		if(dialog!=null&&dialog.isShowing()){
			dialog.dismiss();
		}
	}
	
	public void setCanLoadMore(MaterialRefreshLayout mater,int pageSize,int csize){
		if(csize>0&&csize%pageSize==0){
			mater.setLoadMore(true);
		}else{
			mater.setLoadMore(false);
		}
	}
}
