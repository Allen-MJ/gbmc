package alen.mj.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import alen.mj.base.R;
import alen.mj.base.entry.UnitslevelEntry;
import alen.mj.base.entry.gwyinfo;
import allen.frame.tools.MaterialUtil;
import allen.frame.tools.OnAdapterItemClickListener;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UnitsLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<UnitslevelEntry> list;
	private String type;
	public UnitsLevelAdapter(String type) {
		this.type = type;
	}
	public void setData(List<UnitslevelEntry> list){
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public int getItemCount() {
		return list==null?0:list.size();
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder arg0, int position) {
		ObjHolder holder = (ObjHolder) arg0;
		holder.bind(list.get(position),position);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext())
				.inflate(R.layout.gwy_units_level_item, arg0, false);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
		return new ObjHolder(view);
	}
	
	class ObjHolder extends RecyclerView.ViewHolder {

		private AppCompatTextView name;
		private AppCompatImageView icon;
		private View defaultView;
		private int padding;
		public ObjHolder(View v) {
			super(v);
			icon = v.findViewById(R.id.units_item_icon);
			name = v.findViewById(R.id.units_item_name);
			defaultView = v.findViewById(R.id.defaultView);
			padding = MaterialUtil.dip2px(v.getContext(),20);
		}

		public void bind(final UnitslevelEntry entry, final int position){
			if(entry!=null){
				int level = entry.getLevel();
				if("23".equals(type)){ // 单位名册
					name.setText(list.get(position).getName());
					defaultView.setPadding(
							padding * level,
							defaultView.getPaddingTop(),
							defaultView.getPaddingRight(),
							defaultView.getPaddingBottom());
					if(list.get(position).isOpen()){
						icon.setImageResource(R.drawable.base_ic_down);
					}else{
						icon.setImageResource(R.drawable.base_ic_right);
					}
					if(list.get(position).isLeaf()){
						icon.setVisibility(View.INVISIBLE);
					}else{
						icon.setVisibility(View.VISIBLE);
					}
				}else{ // 自定义 名册
					name.setText((position + 1)+"、 "+entry.getName());
					icon.setVisibility(View.INVISIBLE);
				}
				icon.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(listener!=null){
							listener.leafClick(entry,position);
						}
					}
				});
				name.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(listener!=null){
							listener.itemClick(entry,position);
						}
					}
				});
			}
		}

	}
	
	private OnitemClickListener listener;
	public void setItemClickListener(OnitemClickListener listener){
		this.listener = listener;
	}
	public interface OnitemClickListener{
		void itemClick(UnitslevelEntry entry,int position);
		void leafClick(UnitslevelEntry entry,int position);
	}
}
