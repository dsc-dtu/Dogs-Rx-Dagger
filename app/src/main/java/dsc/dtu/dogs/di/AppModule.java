package dsc.dtu.dogs.di;

import androidx.recyclerview.widget.DiffUtil;

import dagger.Binds;
import dagger.Module;
import dsc.dtu.dogs.adapter.DogDiffer;

/**
 * A Dagger module which binds the DogDiffer class to the interface DiffUtil.ItemCallback<String>
 *
 * Binding this dependency here tells Dagger to provide an instance of DogDiffer to any class
 * requesting DiffUtil.ItemCallback<String> as a dependency.
 *
 * An alternative way of doing this would be to use a @Provides method instead of @Binds. It would
 * look something like this:
 *
 * @Provides
 * static DiffUtil.ItemCallback<String> provideDiffCallback() {
 *     return new DogDiffer();
 * }
 *
 * The @Provides method would work too. However, in the cases when we just need to bind an
 * interface (DiffUtil.ItemCallback) to its implementation (DogDiffer), @Binds is a more efficient
 * method of doing so.
 */
@Module
public interface AppModule {

    @Binds
    DiffUtil.ItemCallback<String> bindDiffCallback(DogDiffer dogDiffer);

}
