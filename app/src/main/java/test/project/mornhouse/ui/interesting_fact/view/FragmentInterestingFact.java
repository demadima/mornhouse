package test.project.mornhouse.ui.interesting_fact.view;

import static test.project.mornhouse.ui.utils.Constants.KeysBundle.INTERESTING_FACT_OBJECT;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import test.project.mornhouse.MainActivity;
import test.project.mornhouse.R;
import test.project.mornhouse.ui.interesting_fact.contract.ContractInterestingFact;
import test.project.mornhouse.ui.interesting_fact.presenter.PresenterInterestingFact;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;
import test.project.mornhouse.ui.interesting_fact.view.adapter.IInterestingFactListener;
import test.project.mornhouse.ui.interesting_fact.view.adapter.InterestingFactAdapter;
import test.project.mornhouse.ui.main.navigation.IFragmentsNavigationListener;


public class FragmentInterestingFact extends Fragment implements  View.OnClickListener, ContractInterestingFact.View, IInterestingFactListener {
    private EditText etNumber;
    private Button bFact, bRandomFact;
    private RecyclerView rvInterestingFact;
    private InterestingFactAdapter interestingFactAdapter;
    private IFragmentsNavigationListener fragmentsNavigationListener;

    private List<Fact> facts = new ArrayList<>();
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private ContractInterestingFact.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interesting_fact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etNumber = view.findViewById(R.id.etNumber);
        bFact = view.findViewById(R.id.bFact);
        bRandomFact = view.findViewById(R.id.bRandomFact);
        rvInterestingFact = view.findViewById(R.id.rvInterestingFact);

        mPresenter =  new PresenterInterestingFact(this);

        interestingFactAdapter = new InterestingFactAdapter(getContext(), this, facts);
        rvInterestingFact.setAdapter(interestingFactAdapter);

        bFact.setOnClickListener(this);
        bRandomFact.setOnClickListener(this);

        initView();

    }

    @Override
    public  void onStop() {
        super.onStop();
        mPresenter.onDestroy();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFact:
                if (etNumber.getText() != null && etNumber.getText().length() > 0) {
                    mPresenter.onBFactWasClicked(String.valueOf(etNumber.getText()));
                }
                break;
            case R.id.bRandomFact:
                mPresenter.onBRandomFactWasClicked();
                break;
        }
    }

    @Override
    public void setDataToRecyclerview(List<Fact> facts) {
        this.facts.clear();
        this.facts.addAll(facts);

        getActivity().runOnUiThread(()->{ interestingFactAdapter.notifyDataSetChanged();});
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void itemSelected(Fact fact) {
        Bundle bundle = new Bundle();
        bundle.putString(INTERESTING_FACT_OBJECT, gson.toJson(fact));

        fragmentsNavigationListener.onFragmentsNavigation(getString(R.string.fragment_details_fact_label),bundle);
    }

    private void initView(){
        mPresenter.getFactsFromDB();
    }
}