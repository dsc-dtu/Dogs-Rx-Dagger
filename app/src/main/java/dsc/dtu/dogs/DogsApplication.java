package dsc.dtu.dogs;

import android.app.Application;

import dsc.dtu.dogs.di.AppComponent;
import dsc.dtu.dogs.di.DaggerAppComponent;

/**
 * A custom application class to create and initialize the Dagger object graph.
 */
public class DogsApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
}
