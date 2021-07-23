package alen.mj.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.List;

import alen.mj.base.R;
import alen.mj.base.entry.gwyinfo;
import allen.frame.tools.OnAdapterItemClickListener;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class McAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<gwyinfo> list;
	private int[] colors;
	public McAdapter(int[] colors) {
		this.colors = colors;
	}
	public void setData(List<gwyinfo> list){
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
				.inflate(R.layout.gv_item_layout, arg0, false);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
		return new ObjHolder(view);
	}
	
	class ObjHolder extends RecyclerView.ViewHolder {

		public AppCompatTextView name,bb;
		private CardView view;
		public ObjHolder(View v) {
			super(v);
			name = v.findViewById(R.id.mc_name);
			bb = v.findViewById(R.id.mc_update);
			view = v.findViewById(R.id.mc_bg);
		}

		public void bind(final gwyinfo info, final int position){
			if(info!=null){
				view.setCardBackgroundColor(colors[position%colors.length]);
				name.setText(info.getNodeMap().get(gwyinfo.B0101));
				bb.setText("V"+info.getNodeMap().get(gwyinfo.DATAVERSION));
				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(listener!=null){
							listener.itemClick(v, position, info);
						}
					}
				});
			}
		}

	}
	
	private OnAdapterItemClickListener<gwyinfo> listener;
	public void setItemClickListener(OnAdapterItemClickListener<gwyinfo> listener){
		this.listener = listener;
	}
}
