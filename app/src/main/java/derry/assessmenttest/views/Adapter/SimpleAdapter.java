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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.username.setText(users.get(position).getUsername());

        holder.street.setText(users.get(position).getAddress().getStreet());
        holder.suite.setText(users.get(position).getAddress().getSuite());
        holder.city.setText(users.get(position).getAddress().getCity());
        holder.zipcode.setText(users.get(position).getAddress().getZipcode());
        holder.lat.setText(users.get(position).getAddress().getGeo().getLat());
        holder.lng.setText(users.get(position).getAddress().getGeo().getLng());

        holder.phone.setText(users.get(position).getPhone());
        holder.website.setText(users.get(position).getWebsite());

        holder.companyName.setText(users.get(position).getCompany().getName());
        holder.catchphrase.setText(users.get(position).getCompany().getCatchPhrase());
        holder.bs.setText(users.get(position).getCompany().getBs());


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

        private TextView name, email, username;
        private TextView street, suite, city, zipcode, lat, lng;
        private TextView phone, website;
        private TextView companyName, catchphrase, bs;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            name = (TextView) itemLayoutView.findViewById(R.id.contact_name);
            email = (TextView) itemLayoutView.findViewById(R.id.contact_email);
            username = (TextView) itemLayoutView.findViewById(R.id.contact_username);

            street = (TextView) itemLayoutView.findViewById(R.id.contact_street);
            suite = (TextView) itemLayoutView.findViewById(R.id.contact_suite);
            city = (TextView) itemLayoutView.findViewById(R.id.contact_city);
            zipcode = (TextView) itemLayoutView.findViewById(R.id.contact_zipcode);
            lat = (TextView) itemLayoutView.findViewById(R.id.contact_geo_lat);
            lng = (TextView) itemLayoutView.findViewById(R.id.contact_geo_lang);

            phone = (TextView) itemLayoutView.findViewById(R.id.contact_phone);
            website = (TextView) itemLayoutView.findViewById(R.id.contact_website);

            companyName = (TextView) itemLayoutView.findViewById(R.id.contact_company_name);
            catchphrase = (TextView) itemLayoutView.findViewById(R.id.contact_company_catchphrase);
            bs = (TextView) itemLayoutView.findViewById(R.id.contact_company_bs);

        }
    }
}
