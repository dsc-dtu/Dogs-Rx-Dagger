package dsc.dtu.dogs.di;

import dagger.Component;
import dsc.dtu.dogs.MainActivity;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

}
