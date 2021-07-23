package alen.mj.base.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import alen.mj.base.R;
import alen.mj.base.entry.Tree;
import allen.frame.tools.StringUtils;
import allen.frame.widget.XListView;

public class TreeRunAdapter extends BaseAdapter {

	private List<Tree> list;
	private Context context;
	private int padding = 30; // 默认为30
	private int len;
	private Map<String, Boolean> isSelect;
	private String type,code;
	private Resources resources;
	private int[] bgColors = new int[]{
			R.color.menu_bg_color_1,
			R.color.menu_bg_color_2,
			R.color.menu_bg_color_3,
			R.color.menu_bg_color_4,
			R.color.white,
			R.color.menu_bg_color_6
	};
	
	public TreeRunAdapter(Context context, String prid, String type) {
		resources = context.getResources();
		isSelect = new HashMap<String, Boolean>();
		this.context = context;
		this.type = type;
		this.len = StringUtils.empty(prid)?-1:prid.length();
	}
	public void setPading(int padding){
		this.padding = padding;
	}
	public void setData(List<Tree> list){
		this.list = list;
		int len = list==null?0:list.size();
		for(int i=0;i<len;i++){
			String mcode = list.get(i).getCode();
			if(StringUtils.empty(code)){
				isSelect.put(mcode, false);
			}else if(code.equals(mcode)){
				isSelect.put(mcode, true);
			}else{
				isSelect.put(mcode, false);
			}
		}
		notifyDataSetChanged();
	}
	public void setChoice(String code){
		this.code = code;
		int len = list==null?0:list.size();
		for(int i=0;i<len;i++){
			String mcode = list.get(i).getCode();
			if(mcode.equals(code)){
				isSelect.put(mcode, true);
			}else{
				isSelect.put(mcode, false);
			}
		}
		notifyDataSetChanged();
	} 
	public void setOpen(final int index){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				boolean ischeck = list.get(index).isCheck();
				int level = list.get(index).getLevel();
				String id = list.get(index).getId();
				list.get(index).setCheck(!ischeck);
				for(int i=0;i<list.size();i++){
					int ml = list.get(i).getLevel();
					String mid = list.get(i).getId();
					if(ml>=level){
						if(id.equals(mid.substring(0, 4*level+len))){
							if(ischeck){
								if(ml>level){
									list.get(i).setOpen(false);
									list.get(i).setCheck(false);
								}
							}else{
								if(ml-1==level){
									list.get(i).setOpen(true);
									list.get(i).setCheck(false);
								}
							}
						}
					}
				}
				hander.sendEmptyMessage(0);
			}
		}).start();
//		notifyDataSetChanged();
	}
	
	private Handler hander = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	public int getCount() {
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View v, ViewGroup parent) {
		HoldView hv;
		if(v==null){
			hv = new HoldView();
			v = LayoutInflater.from(context).inflate(R.layout.tree_item_layout, parent, false);
			hv.icon = (ImageView) v.findViewById(R.id.tree_icon);
			hv.name = (TextView) v.findViewById(R.id.tree_name);
			hv.lay = (LinearLayout) v.findViewById(R.id.tree_lay);
			hv.mlay = (LinearLayout) v.findViewById(R.id.lay);
			v.setTag(hv);
		}else{
			hv = (HoldView) v.getTag();
		}
		final String mcode = list.get(position).getCode();
		hv.name.setText(list.get(position).getName());
		if(isSelect.get(mcode)){
			hv.name.setTextColor(resources.getColor(R.color.text_red_color));
		}else{
			hv.name.setTextColor(resources.getColor(R.color.text_gray_color));
		}
		int level = list.get(position).getLevel();
		
		hv.mlay.setPadding(padding * level, 0, 0, 0);
		v.setBackgroundColor(resources.getColor(bgColors[level %6]));
		if("23".equals(type)){
			if(list.get(position).isOpen()){
				if(list.get(position).getItemNum()==0){
					hv.icon.setImageResource(0);
				}else{
					if(list.get(position).isCheck()){
						hv.icon.setImageResource(R.drawable.base_ic_file_gray);
					}else{
						hv.icon.setImageResource(R.drawable.base_ic_file);
					}
				}
				hv.lay.setVisibility(View.VISIBLE);
				v.setVisibility(View.VISIBLE);
			}else{
				if(list.get(position).getItemNum()==0){
					hv.icon.setImageResource(0);
				}else{
					hv.icon.setImageResource(R.drawable.base_ic_file);
				}
				hv.lay.setVisibility(View.GONE);
				v.setVisibility(View.GONE);
			}
		}else{
			hv.icon.setImageResource(0);
		}
		hv.icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(list.get(position).getItemNum()>0){
					if(list.get(position).isAdd()){
						setOpen(position);
					}else{
						list.get(position).setAdd(true);
						listener.addItem(v, list.get(position).getId(), position+1);
					}
				}else{
					if(listener!=null){
						listener.onclick(v, position);
						setChoice(mcode);
					}
				}
			}
		});
		hv.name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!list.get(position).isCheck()&&list.get(position).getItemNum()>0){
					if(list.get(position).isAdd()){
						setOpen(position);
					}
				}
				if(listener!=null){
					listener.onclick(v, position);
					if(!list.get(position).isAdd()){
						list.get(position).setAdd(true);
						listener.addItem(v, list.get(position).getId(), position+1);
					}
					setChoice(mcode);
				}
			}
		});
		return v;
	}
	class HoldView{
		public XListView xl;
		public TextView name;
		public ImageView icon;
		public LinearLayout lay,mlay;
	}
	private OnItemClickListener listener;
	public void setItemClickListener(OnItemClickListener listener){
		this.listener = listener;
	}
	public interface OnItemClickListener{
		void onclick(View v, int position);
		void addItem(View v, String code, int position);
	}
}
