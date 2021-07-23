/*
package alen.mj.base.utils;

import com.frame.AppManager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import alen.mj.base.R;

public class MenuPopu {
	private Context context;
	private View v;
    private PopupWindow pop;
    private LinearLayout lay;
    private TextView list,pic;
    private int mode;
	public MenuPopu(Context context){
		this.context = context;
		v = LayoutInflater.from(context).inflate(R.layout.login_popu_layout, null, false);
		lay = (LinearLayout) v.findViewById(R.id.lay);
		v.findViewById(R.id.cz_bt).setOnClickListener(l);
		v.findViewById(R.id.xg_bt).setOnClickListener(l);
		v.findViewById(R.id.gy_bt).setOnClickListener(l);
		list = (TextView) v.findViewById(R.id.list_mode_bt);
		list.setOnClickListener(l);
		pic = (TextView) v.findViewById(R.id.pic_mode_bt);
		pic.setOnClickListener(l);
		v.findViewById(R.id.mode_bt).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(lay.getVisibility()==View.GONE){
					lay.setVisibility(View.VISIBLE);
				}else{
					lay.setVisibility(View.GONE);
				}
			}
		});
		pop = new PopupWindow(v);
		pop.setFocusable(true);
        pop.setOutsideTouchable(true);
	}
	public void show(View v){
		mode = AppManager.getInstance().getStoragePreference().getInt("mode", 0);
		if(pop!=null&&!pop.isShowing()){
			pop.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);  
			pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);  
			pop.setTouchable(true);  
			pop.setFocusable(true);
			pop.setOutsideTouchable(true);
	        ColorDrawable dw = new ColorDrawable(0000000000);  
	        pop.setBackgroundDrawable(dw);
			pop.showAtLocation(v, Gravity.RIGHT|Gravity.TOP, 0, 0);
		}
		lay.setVisibility(View.GONE);
		if(mode==0){
			list.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_015, 0, 0, 0);
			pic.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_000, 0, 0, 0);
		}else{
			list.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_000, 0, 0, 0);
			pic.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_015, 0, 0, 0);
		}
	}
	public void dismiss(){
		if(pop!=null&&pop.isShowing()){
			pop.dismiss();
		}
	}
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dismiss();
			if(v.getId()==R.id.list_mode_bt){
				list.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_015, 0, 0, 0);
				pic.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_000, 0, 0, 0);
			}else if(v.getId()==R.id.pic_mode_bt){
				list.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_000, 0, 0, 0);
				pic.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_btn_radio_to_on_mtrl_015, 0, 0, 0);
			}
			if(listener!=null){
				listener.onMenuItemClick(v);
			}
		}
	};
	private OnPopMenuListener listener;
	public void setOnPopMenuListener(OnPopMenuListener listener){
		this.listener = listener;
	}
	public interface OnPopMenuListener{
		void onMenuItemClick(View v);
	}
}
*/
