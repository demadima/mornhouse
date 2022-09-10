package test.project.mornhouse;

import android.app.Application;
import android.content.Context;

//пока использую для получения общего контекста
public class AFApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AFApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AFApplication.context;
    }
}
