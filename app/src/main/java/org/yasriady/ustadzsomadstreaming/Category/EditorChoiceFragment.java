package org.yasriady.ustadzsomadstreaming.Category;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yasriady.ustadzsomadstreaming.Cfg;
import org.yasriady.ustadzsomadstreaming.R;

import static org.yasriady.ustadzsomadstreaming.Cfg.Category.EDITOR_CHOICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditorChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditorChoiceFragment extends Base  {

    /* Configure these consts: */
    private final String TABLENAME = "video_editor_choice";
    //private final String TITLE = getResources().getString(R.string.live_now);
    private final Cfg.Category CATEGORY = EDITOR_CHOICE;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "title";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String m_title;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditorChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditorChoiceFragment newInstance(String title, String param2) {
        EditorChoiceFragment fragment = new EditorChoiceFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            m_title= getArguments().getString(TITLE);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.begin(view, TABLENAME);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(this);
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


}
