package org.yasriady.livestream.Category;

/**
 * Created by dedy on 11/20/17.
 */

import android.net.Uri;

import org.yasriady.livestream.Player.*;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
    //void onFragmentInteraction(org.yasriady.livestream.Player.Base fragment);
}
