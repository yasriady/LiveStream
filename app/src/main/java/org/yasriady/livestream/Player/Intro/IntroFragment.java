package org.yasriady.livestream.Player.Intro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.BaseAnimationInterface;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.yasriady.livestream.Category.OnFragmentInteractionListener;
import org.yasriady.livestream.Player.Base;
import org.yasriady.livestream.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroFragment extends Base {

    private WebView m_webView;
    private MyImageSlider m_myImageSlider;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntroFragment newInstance(String param1, String param2) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(/*R.layout.fragment_intro*/ R.layout.fragment_intro_slider_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        begin(view);
    }

    private void begin(View view) {
        //loadIntroUrl(view);
        m_myImageSlider = new MyImageSlider(getContext(), view);
        m_myImageSlider.begin();
    }

    @Override
    public void onStop() {
        m_myImageSlider.stop();
        super.onStop();
    }

    private void loadIntroUrl(View view) {

        m_webView = view.findViewById(R.id.webView);
        m_webView.clearCache(true);
        m_webView.getSettings().setJavaScriptEnabled(true);
        m_webView.setWebViewClient(new WebViewClient());

        String introUrl = m_remoteConfig.getServer() + "/livestream/intro/introUrl.php";
        m_webView.loadUrl(introUrl);

        ImageView imageView = view.findViewById(R.id.imageView);
        String imgUrl = m_remoteConfig.getServer() + "/livestream/intro/mqdefault.jpg";
        Picasso.with(getContext()).load(imgUrl).into(imageView);

        // https://stackoverflow.com/q/33046093/3626789
        //bookingView = (WebView) findViewById(R.id.fullscreen_content);
        //bookingView.getSettings().setJavaScriptEnabled(true);
        //bookingView.setWebViewClient(new WebViewClient());
        //bookingView.loadUrl("http://www.google.com");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class MyImageSlider {

        private SliderLayout m_sliderLayout;
        private Context m_context;
        private View m_view;

        MyApi m_api;

        public MyImageSlider(Context context, View view) {
            this.m_context = context;
            this.m_view = view;
        }

        public void begin() {
            //loadImageSlider(m_view);
            startDownloadImageList();
        }

        // Referensi berasal dari, https://stackoverflow.com/a/41343060/3626789
        private void startDownloadImageList() {

//            if (!NetWorkUtils.getInstance(context).isNetworkAvailable()) {
//                Toast.makeText(context, "No data connection available", Toast.LENGTH_SHORT).show();
//                return;
//            }

//            showProgressDialog();
            final String FILE_BASE_URL = m_remoteConfig.getServer() + "/livestream/intro/"; // "http://192.168.43.117/livestream/intro/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FILE_BASE_URL)
                    .build();

            FileHandlerService handlerService = retrofit.create(FileHandlerService.class);

            Call<ResponseBody> call = handlerService.downloadFile();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //dismissProgressDialog();
                    if (response.isSuccessful()) {
//                        if (writeResponseBodyToDisk(response.body())) {
//                            listener.onFileLoaded(file);
//                        }

                        HashMap<String, String> urlMaps = makeUrlMap(response.body());
                        startImageSlider(urlMaps);

                    } else {
                        //listener.onDownloadFailed("Resource not Found");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //dismissProgressDialog();
                    //listener.onDownloadFailed("Download Failed");
                    t.printStackTrace();
                }
            });

        }

        public void stop() {
            // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
            if (m_sliderLayout != null) {
                m_sliderLayout.stopAutoCycle();
            }
        }

        private void startImageSlider(HashMap<String, String> urlMaps) {

            m_sliderLayout = m_view.findViewById(R.id.sliderLayout);

//            HashMap<String, String> urlMaps = null;
//            urlMaps = new HashMap<>();
//            urlMaps.put("Silhouette", "http://192.168.43.117/livestream/intro/sliderImages/5195550-muslim-wallpaper.jpg");
//            urlMaps.put("Reciting Quran", "http://192.168.43.117/livestream/intro/sliderImages/5448557-muslim-wallpaper.jpg");
//            urlMaps.put("Masjid", "http://192.168.43.117/livestream/intro/sliderImages/5630052-muslim-wallpaper.jpg");
//            urlMaps.put("Holy Quran", "http://192.168.43.117/livestream/intro/sliderImages/5816090-muslim-wallpaper.jpg");

            //urlMaps = makeUrlMap();

            for (String name : urlMaps.keySet()) {

                TextSliderView textSliderView = new TextSliderView(m_context);
                // Initialize slider
                textSliderView
                        .description(name)
                        .image(urlMaps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                    /*.setOnSliderClickListener( new MySliderClickListener()  )*/
                        .setOnImageLoadListener(new MyImageLoadListener());    // TAK JALAN

                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", name);

                m_sliderLayout.addSlider(textSliderView);
            }

            SliderLayout.Transformer transformer = makeTransformer();
            m_sliderLayout.setPresetTransformer(transformer);
            m_sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

            m_sliderLayout.setCustomAnimation(new MyDescriptionAnimation());

            final int duration = m_remoteConfig.imageSliderDuration();
            m_sliderLayout.setDuration(duration);
            m_sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);

            // x_
            //m_sliderLayout.getPagerIndicator().setVisibility(View.INVISIBLE);

// x_
//            m_sliderLayout.getCurrentSlider().setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//                @Override
//                public void onSliderClick(BaseSliderView baseSliderView) {
//                    Toast.makeText(getContext(), "onSliderClick(xxxxxxxxxx)", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            m_sliderLayout.setOnClickListener(new SliderLayout.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getContext(), "ImageSliderClicked()", Toast.LENGTH_SHORT).show();
//                    m_sliderLayout.setPresetTransformer(makeTransformer());
//                }
//            });

        }

        // Create random transformer
        private SliderLayout.Transformer makeTransformer() {
            // Total 15, no O hanyalah default
            final int min = 1/*inclusive*/, max = 16/*exclusive*/;
            Random r = new Random();
            int idx = r.nextInt(max - min) + min;

            SliderLayout.Transformer transformer = SliderLayout.Transformer.values()[idx];
            //SliderLayout.Transformer transformer = SliderLayout.Transformer.Background2Foreground;

            return transformer;
        }

        private HashMap<String, String> makeUrlMap(ResponseBody body) {

            HashMap<String, String> result = new HashMap<>();

            try {

                InputStream inputStream = null;
                ByteArrayOutputStream outputStream = null;

                try {

                    byte[] byteReader = new byte[4096];

                    long strSize = body.contentLength();
                    long byteSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new ByteArrayOutputStream();

                    while (true) {
                        int read = inputStream.read(byteReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(byteReader, 0, read);
                        byteSizeDownloaded += read;

                        Log.d("MYTAG", "file download: " + byteSizeDownloaded + " of " + strSize);
                    }

                    String str;
                    //str = new String( outputStream.toByteArray(), "utf-8" );
                    str = outputStream.toString("utf-8");
                    result = jsonStrToHashMap(str);
                    outputStream.flush();
                    return result; // true

                } catch (IOException e) {
                    e.printStackTrace();
                    return null; //false

                } finally {

                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null; // false
            }

        }

        private HashMap<String, String> jsonStrToHashMap(String str) {

            HashMap<String, String> result = new HashMap<>();

            try {

                JSONArray arr = new JSONArray(str);
                for (int i = 0; i < arr.length(); i++) {
                    String key = makeKey(arr.getString(i));
                    String value = makeValue(arr.getString(i));
                    result.put(key, value);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        private String makeKey(String str) {
            String key = str;
            String filename = str;
            // Remove extension
            String ext = filename.substring(filename.lastIndexOf('.'), filename.length());//.toLowerCase();
            key = str.replace(ext, "").replace("-", " ").replace("_", " ").replace("  ", " ");
            return key;
        }

        private String makeValue(String str) {
            String value = m_remoteConfig.getServer() + "/livestream/intro/sliderImages/" + str;
            return value;
        }

        class MySliderClickListener implements BaseSliderView.OnSliderClickListener {
            @Override
            public void onSliderClick(BaseSliderView baseSliderView) {
                //Toast.makeText(m_context, "onSliderClick()", Toast.LENGTH_SHORT).show();
                m_sliderLayout.setPresetTransformer(makeTransformer());
            }
        }

        class MyImageLoadListener implements BaseSliderView.ImageLoadListener {

            @Override
            public void onStart(BaseSliderView baseSliderView) {
                //Toast.makeText(m_context, "onStart()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEnd(boolean b, BaseSliderView baseSliderView) {
                //Toast.makeText(m_context, "onEnd()", Toast.LENGTH_SHORT).show();
                m_sliderLayout.setPresetTransformer(makeTransformer());
            }

        }

        public class MyDescriptionAnimation implements BaseAnimationInterface {
            /**
             * When current item is going to leave, let's make the description layout disappear.
             */
            @Override
            public void onPrepareCurrentItemLeaveScreen(View current) {
                View descriptionLayout = current.findViewById(R.id.description_layout);
                if (descriptionLayout != null) {
                    current.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
                }
            }

            /**
             * When next item is coming to show, let's hide the description layout.
             */
            @Override
            public void onPrepareNextItemShowInScreen(View next) {
                View descriptionLayout = next.findViewById(R.id.description_layout);
                if (descriptionLayout != null) {
                    next.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCurrentItemDisappear(View current) {

            }

            /**
             * When next item is shown, let's make an animation to show the
             * description layout.
             */
            @Override
            public void onNextItemAppear(View next) {

                final boolean imageSliderShowDescription = m_remoteConfig.imageShowDescription();
                final int VISIBILITY = imageSliderShowDescription ? View.VISIBLE : View.INVISIBLE;

                View descriptionLayout = /*view*/next.findViewById(R.id.description_layout);
                if (descriptionLayout != null) {
                    float layoutY = ViewHelper.getY(descriptionLayout);
                    next.findViewById(R.id.description_layout).setVisibility(/*View.VISIBLE*/VISIBILITY);
                    ValueAnimator animator = ObjectAnimator.ofFloat(
                            descriptionLayout, "y", layoutY + descriptionLayout.getHeight(),
                            layoutY).setDuration(500);
                    animator.start();
                }
            }
        }


    }

    interface FileHandlerService_1 {
        @GET("uploads/documents/{file_name}")
        Call<ResponseBody> downloadFile(
                @Path("file_name") String imageName);
    }

    interface FileHandlerService {
        @GET("imageList.php")
        Call<ResponseBody> downloadFile();

    }

}


//    if (!NetWorkUtils.getInstance(context).isNetworkAvailable()) {
//            Toast.makeText(context, "No data connection available", Toast.LENGTH_SHORT).show();
//            return;
//            }


//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("url")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            m_api = retrofit.create(MyApi.class);
//            Call<ResponseBody> call = m_api.getData();
//
//
//            Call<ResponseBody> response = m_api.getData();
//
//
//
//            try     {
//                //you can now get your file in the InputStream
//                InputStream is = response.getBody().in();
//            } catch(    IOException e)    {
//                e.printStackTrace();
//            }
//
//
