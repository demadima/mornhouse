package test.project.mornhouse.ui.details.view;

import static test.project.mornhouse.ui.utils.Constants.KeysBundle.INTERESTING_FACT_OBJECT;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import test.project.mornhouse.R;
import test.project.mornhouse.ui.interesting_fact.presenter.PresenterInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;
import test.project.mornhouse.ui.interesting_fact.view.adapter.InterestingFactAdapter;
import test.project.mornhouse.ui.main.navigation.IFragmentsNavigationListener;

public class FragmentDetailsFact extends Fragment implements View.OnClickListener {

    private TextView tvNumber,tvText;
    private Toolbar toolbar;
    private ImageView ivBack;

    private IFragmentsNavigationListener fragmentsNavigationListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_fact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNumber = view.findViewById(R.id.tvNumber);
        tvText = view.findViewById(R.id.tvText);
        toolbar = view.findViewById(R.id.toolbar);
        ivBack = toolbar.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);

        if (getArguments() != null && getArguments().getString(INTERESTING_FACT_OBJECT) != null && !getArguments().getString(INTERESTING_FACT_OBJECT).isEmpty()  ){
            Fact fact = new Gson().fromJson(getArguments().getString(INTERESTING_FACT_OBJECT),new TypeToken<Fact>() {}.getType());
            tvNumber.setText(String.valueOf(fact.getNumber()));
            tvText.setText(String.valueOf(fact.getText()));
        }
    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentsNavigationListener) {
            fragmentsNavigationListener = (IFragmentsNavigationListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivBack:
                fragmentsNavigationListener.onFragmentsNavigation(getString(R.string.fragment_interesting_fact_label));
                break;
        }
    }

}