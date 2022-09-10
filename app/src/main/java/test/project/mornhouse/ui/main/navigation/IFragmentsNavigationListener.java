package test.project.mornhouse.ui.main.navigation;

import android.os.Bundle;

public interface IFragmentsNavigationListener {
    void onFragmentsNavigation (String label, Bundle bundle);
    void onFragmentsNavigation (String label);
}
