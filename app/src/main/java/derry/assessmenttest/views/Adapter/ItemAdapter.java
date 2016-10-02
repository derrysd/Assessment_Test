package derry.assessmenttest.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;

import derry.assessmenttest.R;
import derry.assessmenttest.entities.User;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<User> users;
    private Context context;

    public ItemAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(context, users);
        holder.expandableListView.setAdapter(expandableListAdapter);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ExpandableListView expandableListView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            expandableListView = (ExpandableListView) itemView.findViewById(R.id.expandableListView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
