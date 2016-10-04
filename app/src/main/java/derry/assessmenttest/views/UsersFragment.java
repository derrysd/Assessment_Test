package derry.assessmenttest.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import derry.assessmenttest.R;
import derry.assessmenttest.entities.User;
import derry.assessmenttest.presenters.MainPresenter;
import derry.assessmenttest.presenters.MainPresenterImpl;
import derry.assessmenttest.utils.SharedPreferencesManager;
import derry.assessmenttest.views.Adapter.CustomAdapter;


public class UsersFragment extends Fragment implements MainView{
    private RecyclerView recyclerView;
    private ImageButton buttonClear;
    private EditText editTextSearch;
    private MainPresenter presenter;
    private CustomAdapter customAdapter;
    private Context context;

    public UsersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_users, container, false);
        context = view.getContext().getApplicationContext();

        SharedPreferences prefs = SharedPreferencesManager.createInstance(view.getContext().getApplicationContext()).getPreferences();
        presenter = new MainPresenterImpl(this, prefs);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        customAdapter = new CustomAdapter(presenter.getData(), view.getContext());
        recyclerView.setAdapter(customAdapter);

        buttonClear = (ImageButton) view.findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextSearch.setText("");
                hideSoftKeyboard();
            }
        });

        editTextSearch = (EditText) view.findViewById(R.id.edittext_search);
        editTextSearch.addTextChangedListener(searchButtonTextWatcher);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private TextWatcher searchButtonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            buttonClear.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
            charSequence = charSequence.toString().toLowerCase();

            final List<User> filteredList = new ArrayList<>();

            for (int i = 0; i < presenter.getData().size(); i++) {

                final String textName = presenter.getData().get(i).getName().toLowerCase();
                final String textEmail = presenter.getData().get(i).getEmail().toLowerCase();
                final String textUsername = presenter.getData().get(i).getUsername().toLowerCase();
                if (textName.contains(charSequence) || textEmail.contains(charSequence) || textUsername.contains(charSequence)) {
                    filteredList.add(presenter.getData().get(i));
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            customAdapter = new CustomAdapter(filteredList, context);
            recyclerView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
    }
}
