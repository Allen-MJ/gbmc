package alen.mj.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import alen.mj.base.R;
import alen.mj.base.entry.gwyinfo;
import androidx.recyclerview.widget.RecyclerView;


public class McItermAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<gwyinfo> list;
	public McItermAdapter() {
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
	public void onBindViewHolder(RecyclerView.ViewHolder arg0, int arg1) {
		McViewHolder holder = (McViewHolder) arg0;
		holder.position = arg1;
		holder.name.setText(list.get(arg1).getNodeMap().get(gwyinfo.B0101));
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(arg0.getContext())
				.inflate(R.layout.mc_child_item_layout_2, arg0, false);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.WRAP_CONTENT));
		return new McViewHolder(view);
	}
	
	class McViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

		public int position;
		public TextView name;
		public McViewHolder(View v) {
			super(v);
			name = (TextView) v.findViewById(R.id.tree_name);
			v.setOnClickListener(this);
		}
		
		@Override
		public void onClick(View v) {
			if(listener!=null){
				listener.itemClick(v, position);
			}
		}
		
	}
	
	private OnItemClickListener listener;
	public void setItemClickListener(OnItemClickListener listener){
		this.listener = listener;
	}
	public interface OnItemClickListener{
		void itemClick(View v, int position);
	}
	
}
