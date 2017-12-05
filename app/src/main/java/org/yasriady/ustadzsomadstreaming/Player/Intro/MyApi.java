package org.yasriady.ustadzsomadstreaming.Player.Intro;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by dedy on 11/28/17.
 */

public interface MyApi {

    @GET("path/to/your/resource")
    @Streaming
    Call<ResponseBody> getData();

}


