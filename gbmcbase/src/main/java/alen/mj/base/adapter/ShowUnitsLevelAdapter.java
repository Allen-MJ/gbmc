package alen.mj.base.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import alen.mj.base.R;
import alen.mj.base.entry.UnitslevelEntry;

public class ShowUnitsLevelAdapter extends BaseAdapter {

	private List<UnitslevelEntry> list = null;
	private Context context;
	private int padding = 30; // 默认为30
	private String type;
//	private Resources resources;
//	private int[] bgColors = new int[]{
//			R.color.menu_bg_color_1,
//			R.color.menu_bg_color_2,
//			R.color.menu_bg_color_3,
//			R.color.menu_bg_color_4,
//			R.color.white,
//			R.color.menu_bg_color_6
//	};
	
	public ShowUnitsLevelAdapter(Context context, String type) {
//		resources = context.getResources();
		this.context = context;
		this.type = type;
	}
	
	public void setPading(int padding){
		this.padding = padding;
	}
	
	public void setData(List<UnitslevelEntry> list){
		this.list = list;
		notifyDataSetChanged();
	}
	
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
		if(v==null){
			hv = new HoldView();
			v = LayoutInflater.from(context).inflate(R.layout.units_level_item, parent, false);
			hv.icon = (ImageView) v.findViewById(R.id.units_item_icon);
			hv.name = (TextView) v.findViewById(R.id.units_item_name);
			hv.defaultView = v.findViewById(R.id.defaultView);
			v.setTag(hv);
		}else{
			hv = (HoldView) v.getTag();
		}
		int level = list.get(position).getLevel();
//		hv.defaultView.setPadding(padding * level, 0, 0, 0);
//		v.setBackgroundColor(resources.getColor(bgColors[level %6]));
		if("23".equals(type)){ // 单位名册
			hv.name.setText(list.get(position).getName());
			
			hv.defaultView.setPadding(
					padding * level, 
					hv.defaultView.getPaddingTop(), 
					hv.defaultView.getPaddingRight(), 
					hv.defaultView.getPaddingBottom());
			if(list.get(position).isOpen()){
				hv.icon.setImageResource(R.drawable.base_ic_file_gray);
			}else{
				hv.icon.setImageResource(R.drawable.base_ic_file);
			}
			if(list.get(position).isLeaf()){
				hv.icon.setVisibility(View.INVISIBLE);
			}else{
				hv.icon.setVisibility(View.VISIBLE);
			}
		}else{ // 自定义 名册
			hv.name.setText((position + 1)+"、 "+list.get(position).getName());
			hv.icon.setVisibility(View.INVISIBLE);
		}
		// 有争议，需要 再次处理
		if(list.get(position).isCheck()){
			hv.name.setSelected(true);
		}else{
			hv.name.setSelected(false);
		}
		return v;
	}
	
	HoldView hv = null;
	
	class HoldView{
		public TextView name;
		public ImageView icon;
		View defaultView;
	}
}
