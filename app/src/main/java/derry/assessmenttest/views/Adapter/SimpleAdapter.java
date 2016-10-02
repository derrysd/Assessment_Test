package derry.assessmenttest.views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import derry.assessmenttest.R;
import derry.assessmenttest.entities.User;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    List<User> users;
    Context context;

    public SimpleAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_parent, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.username.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email, username;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            name = (TextView) itemLayoutView.findViewById(R.id.contact_name);
            email = (TextView) itemLayoutView.findViewById(R.id.contact_email);
            username = (TextView) itemLayoutView.findViewById(R.id.contact_username);

        }
    }
}
