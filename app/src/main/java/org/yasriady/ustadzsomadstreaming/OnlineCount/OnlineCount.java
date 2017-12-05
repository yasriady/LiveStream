package org.yasriady.ustadzsomadstreaming.OnlineCount;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.content.ContentValues.TAG;

/**
 * Created by dedy on 11/30/17.
 */

// Reference: https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/

    // 1
public class OnlineCount {

    public OnlineCount() {
    }

    public void count(final String direction) { // up or down

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<OnlineCountModel> call = apiInterface.getOnlineCount(direction);
        call.enqueue(new Callback<OnlineCountModel>() {
            @Override
            public void onResponse(Call<OnlineCountModel> call, Response<OnlineCountModel> response) {

                String value = response.body().getValue();
                Log.d("MYTAG", "Currently user online are: " + value);
            }

            @Override
            public void onFailure(Call<OnlineCountModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    // 2
    static class ApiClient {

        public static final String BASE_URL = "http://192.168.43.15/livestream/record/";   // JANGAN LUPA SLASH diakhir
        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

    }

    // 3
    interface ApiInterface {

        @GET("update_online_count.php")
        Call<OnlineCountModel> getOnlineCount(@Query("direction") String direction);

        @GET("movie/{id}") // contoh
        Call<OnlineCountModel> x_getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    }

    // 4
    class OnlineCountModel {

        @SerializedName("value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}