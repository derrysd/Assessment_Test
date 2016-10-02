package derry.assessmenttest.views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import derry.assessmenttest.R;
import derry.assessmenttest.entities.User;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<User> users;

    public ExpandableListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getGroupCount() {
        return users.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return users.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return users.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        User user = (User) getGroup(i);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.list_parent, viewGroup);
        }

        TextView name = (TextView) view.findViewById(R.id.contact_name);
        TextView username = (TextView) view.findViewById(R.id.contact_username);
        TextView email = (TextView) view.findViewById(R.id.contact_email);

        name.setText(user.getName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        User user = (User) getGroup(i);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.list_child, viewGroup);
        }

        TextView street = (TextView) view.findViewById(R.id.contact_street);
        TextView suite = (TextView) view.findViewById(R.id.contact_suite);
        TextView city = (TextView) view.findViewById(R.id.contact_city);
        TextView zipcode = (TextView) view.findViewById(R.id.contact_zipcode);
        TextView lat = (TextView) view.findViewById(R.id.contact_geo_lat);
        TextView lng = (TextView) view.findViewById(R.id.contact_geo_lang);

        TextView phone = (TextView) view.findViewById(R.id.contact_phone);
        TextView website = (TextView) view.findViewById(R.id.contact_website);

        TextView company_name = (TextView) view.findViewById(R.id.contact_company_name);
        TextView company_catchphrase = (TextView) view.findViewById(R.id.contact_company_catchphrase);
        TextView company_bs = (TextView) view.findViewById(R.id.contact_company_bs);

        street.setText(user.getAddress().getStreet());
        suite.setText(user.getAddress().getSuite());
        city.setText(user.getAddress().getCity());
        zipcode.setText(user.getAddress().getZipcode());

        lat.setText(user.getAddress().getGeo().getLat());
        lng.setText(user.getAddress().getGeo().getLng());
        phone.setText(user.getPhone());
        website.setText(user.getWebsite());
        company_name.setText(user.getCompany().getName());
        company_catchphrase.setText(user.getCompany().getCatchPhrase());
        company_bs.setText(user.getCompany().getBs());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
