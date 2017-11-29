package org.yasriady.livestream.RecyclerView;

import org.yasriady.livestream.Model.Model2.VideoModel;
import org.yasriady.livestream.Model.Model4.VideoModel4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dedy on 11/26/17.
 */

//public interface VideosApi {
//    @GET("videos.php")
//    Call<List<VideoModel>> getVideos(@Query("index") int index);
//}

// How to set query parameter?
// https://futurestud.io/tutorials/retrofit-2-add-multiple-query-parameter-with-querymap
public interface VideosApi {

    @GET("videos.php")
    Call<List<VideoModel4>> getVideos(
            @Query("index") int index,
            @Query("table_name") String tableName
    );

}

