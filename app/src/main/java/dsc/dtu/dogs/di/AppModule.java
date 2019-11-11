package dsc.dtu.dogs.di;

import dagger.Module;
import dagger.Provides;
import dsc.dtu.dogs.api.DogsService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class AppModule {

    @Provides
    public static HttpLoggingInterceptor provideLogger() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.level(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }

    @Provides
    public static OkHttpClient provideOkHttp(HttpLoggingInterceptor logger) {
        return new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient okHttp) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttp)
                .baseUrl("https://dog.ceo/api/")
                .build();
    }

    @Provides
    public static DogsService provideDogService(Retrofit retrofit) {
        return retrofit.create(DogsService.class);
    }
}
