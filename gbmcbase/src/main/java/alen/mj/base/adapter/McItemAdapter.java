package alen.mj.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import alen.mj.base.R;
import alen.mj.base.entry.gwyinfo;

public class McItemAdapter extends BaseAdapter {

	private List<gwyinfo> list;
	private Context context;
	
	public McItemAdapter(Context context) {
		this.context = context;
	}
	public void setData(List<gwyinfo> list){
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
		HoldView hv;
		if(v==null){
			hv = new HoldView();
			v = LayoutInflater.from(context).inflate(R.layout.mc_child_item_layout, parent, false);
			hv.name = (TextView) v.findViewById(R.id.tree_name);
			v.setTag(hv);
		}else{
			hv = (HoldView) v.getTag();
		}
		hv.name.setText(list.get(position).getNodeMap().get(gwyinfo.B0101));
		
		hv.name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener!=null){
					listener.onclick(v, position);
				}
			}
		});
		return v;
	}
	class HoldView{
		public TextView name;
	}
	private OnItemClickListener listener;
	public void setItemClickListener(OnItemClickListener listener){
		this.listener = listener;
	}
	public interface OnItemClickListener{
		void onclick(View v, int position);
	}
}
