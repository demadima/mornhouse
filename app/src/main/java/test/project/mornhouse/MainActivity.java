package test.project.mornhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Objects;

import test.project.mornhouse.ui.interesting_fact.contract.ContractInterestingFact;
import test.project.mornhouse.ui.main.navigation.IFragmentsNavigationListener;

public class MainActivity extends AppCompatActivity implements IFragmentsNavigationListener {
    private NavController navController;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

    }

    @Override
    public void onFragmentsNavigation(String label, Bundle bundle) {
        if (label.equals(getString(R.string.fragment_interesting_fact_label))){
            navController.navigate(R.id.fragment_interesting_fact,bundle);
        } else if (label.equals(getString(R.string.fragment_details_fact_label))){
            navController.navigate(R.id.fragment_details_fact,bundle);
        }
    }

    @Override
    public void onFragmentsNavigation(String label) {
        if (label.equals(getString(R.string.fragment_interesting_fact_label))){
            navController.navigate(R.id.fragment_interesting_fact);
        } else if (label.equals(getString(R.string.fragment_details_fact_label))){
            navController.navigate(R.id.fragment_details_fact);
        }
    }
}