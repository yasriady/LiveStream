package org.yasriady.livestream.RecyclerView;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dedy on 11/26/17.
 */

public class ServiceGenerator {

    private static final String URL = "http://192.168.43.117/livestream/record/"; // Jangan LUPA tanda / diakhir url
    // http://www.sab99r.com/demos/api/

    public static <S> S createService(Class<S> serviceClass) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();

        return retrofit.create(serviceClass);
    }

}
