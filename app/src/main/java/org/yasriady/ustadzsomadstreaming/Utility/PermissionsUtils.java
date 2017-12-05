package org.yasriady.ustadzsomadstreaming.Utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import org.yasriady.ustadzsomadstreaming.Category.OnFragmentInteractionListener;
import org.yasriady.ustadzsomadstreaming.Cfg;

/**
 * Created by dedy on 12/4/17.
 */

// https://stackoverflow.com/a/42143563/3626789
public class PermissionsUtils {

    private Context m_context;

    //public static final int REQUEST_PERMISSION_MULTIPLE = 9;
    public static final int REQUEST_PERMISSION_CAMERA = 1;
    public static final int REQUEST_PERMISSION_LOCATION = 2;
    public static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 6;
    //public static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 7;
    public static final int REQUEST_PERMISSION_INTERNET = 4;                    // Tak masuk dalam requestPermission
    public static final int REQUEST_PERMISSION_ACCESS_NETWORK_STATE = 5;        // Tak masuk dalam requestPermission

    private boolean m_permCamera;
    private boolean m_permAccessLocation;
    //private boolean m_permReadExternalStorage;
    private boolean m_permWriteExternalStorage;
    private boolean m_permInternet;
    private boolean m_permAccessNetworkState;

    private boolean m_permissionResult;
    private OnPermissionListener mListener;

    public PermissionsUtils(Context context) {
        m_context = context;

        if (context instanceof OnPermissionListener) {
            mListener = (OnPermissionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPermissionListener");
        }

    }

    public boolean requestPermissions1() {

        Activity activity = (Activity) m_context;
        boolean result1 = requestPermission(activity, Manifest.permission.CAMERA, REQUEST_PERMISSION_CAMERA);
        boolean result2 = requestPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_PERMISSION_LOCATION);
        boolean result3 = requestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        //boolean result4 = requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
        boolean result5 = requestPermission(activity, Manifest.permission.INTERNET, REQUEST_PERMISSION_INTERNET);
        boolean result6 = requestPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE, REQUEST_PERMISSION_ACCESS_NETWORK_STATE);
        boolean result = result1 && result2 && result3 && /*result4 &&*/ result5 && result6;
        return result;
    }

    private boolean requestPermission(Activity activity, final String permission, final int requestCode) {
        boolean result;
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            result = false;
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
            // Permission is granted
        } else {
            result = true;
            System.out.println("requestPermission() PERMISSION ALREADY GRANTED");
        }
        return result;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case REQUEST_PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                    //Toast.makeText(context, "Permission granted!", Toast.LENGTH_LONG).show();
                    m_permCamera = true;
                    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_CAMERA");
                } else {
                    //Toast.makeText(context, "Permission denied!", Toast.LENGTH_LONG).show();
                    m_permCamera = false;
                }
                break;
            case REQUEST_PERMISSION_LOCATION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    m_permAccessLocation = true;
                    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_LOCATION");
                } else {
                    m_permAccessLocation = false;
                }
                break;
            case REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE");
                    m_permWriteExternalStorage = true;
                } else {
                    m_permWriteExternalStorage = false;
                }
                break;
            //case REQUEST_PERMISSION_READ_EXTERNAL_STORAGE:
            //    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_READ_EXTERNAL_STORAGE");
            //    } else {
            //    }
            //    break;
            case REQUEST_PERMISSION_INTERNET:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_INTERNET");
                    m_permInternet = true;
                } else {
                    m_permInternet = false;
                }
                break;
            case REQUEST_PERMISSION_ACCESS_NETWORK_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(Cfg.MYTAG, "Permission granted for REQUEST_PERMISSION_ACCESS_NETWORK_STATE");
                    m_permAccessNetworkState = true;
                } else {
                    m_permAccessNetworkState = false;
                }
                break;
        }

//        m_permCamera = checkPermission(Manifest.permission.CAMERA);
//        m_permAccessLocation = checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
//        m_permWriteExternalStorage = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        //m_permReadExternalStorage = checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
//        m_permInternet = checkPermission(Manifest.permission.INTERNET);
//        m_permAccessNetworkState = checkPermission(Manifest.permission.ACCESS_NETWORK_STATE);
//        m_permissionResult = m_permCamera && m_permAccessLocation && /*m_permReadExternalStorage &&*/
//                m_permWriteExternalStorage && m_permInternet && m_permAccessNetworkState;

        // Hanya critical permission
        m_permissionResult = m_permCamera && m_permAccessLocation && m_permWriteExternalStorage;


        if (m_permissionResult) {
            //Toast.makeText(m_context, "Permission GRANTED!", Toast.LENGTH_LONG).show();
            mListener.onPermissionListener(PackageManager.PERMISSION_GRANTED);
        } else {
            //Toast.makeText(m_context, "Permission DENIED!", Toast.LENGTH_LONG).show();
            mListener.onPermissionListener(PackageManager.PERMISSION_DENIED);
        }

    }

    public boolean requestPermissions2() {
        boolean permCamera = checkPermission(Manifest.permission.CAMERA);
        boolean permAccessLocation = checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        boolean permReadExternalStorage = checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean permWriteExternalStorage = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean permInternet = checkPermission(Manifest.permission.INTERNET);
        boolean permAccessNetworkState = checkPermission(Manifest.permission.ACCESS_NETWORK_STATE);

        boolean resutl = permCamera && permAccessLocation && permReadExternalStorage &&
                permWriteExternalStorage && permInternet && permAccessNetworkState;
        return resutl;
    }

    private boolean checkPermission(final String permission) {
        int res = m_context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }


//    public static boolean checkAndRequestPermissions(Activity activity) {
//
//        System.out.println("PermissionsUtils checkAndRequestPermissions()");
//
//        int permissionCamera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
//        int permissionLocation = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionWriteExternal = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int permissionInternet = ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);
//        int permissionAccessNetworkState = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE);
//
//        // Permission List
//        List<String> listPermissionsNeeded = new ArrayList<>();
//
//        // Camera Permission
//        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
//                Toast.makeText(activity, "Camera Permission is required for this app to run", Toast.LENGTH_SHORT).show();
//            }
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
//
//        // Read/Write Permission
//        if (permissionWriteExternal != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//
//        // Location Permission
//        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//
//        // Internet Permission
//        if (permissionInternet != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.INTERNET);
//        }
//
//        // Access Network State Permission
//        if (permissionAccessNetworkState != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
//        }
//
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_PERMISSION_MULTIPLE);
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Requests the Camera permission. If the permission has been denied
//     * previously, a SnackBar will prompt the user to grant the permission,
//     * otherwise it is requested directly.
//     */
//    public static void requestCameraPermission(Activity activity) {
//        // Here, thisActivity is the current activity
//        // System.out.println("requestCameraPermission() INITIAL");
//        // Toast.makeText(this, "requestCameraPermission() INITIAL",
//        // Toast.LENGTH_LONG).show();
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
//                // Toast.makeText(activity, "Camera permission is
//                // needed for this app to run ",
//                // Toast.LENGTH_SHORT).show();
//                // System.out.println("requestCameraPermission() SHOW INFO");
//
//                // Show an explanation to the user *asynchronously* -- don't
//                // block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
//                        REQUEST_PERMISSION_CAMERA);
//
//            } else {
//                // No explanation needed, we can request the permission.
//                // System.out.println("requestCameraPermission() ASK
//                // PERMISSION");
//
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
//                        REQUEST_PERMISSION_CAMERA);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//            // Permission is granted
//        } else {
//            System.out.println("requestCameraPermission() PERMISSION ALREADY GRANTED");
//
//        }
//
//    }
//
//    public static void requestLocationPermission(Activity activity) {
//
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // Toast.makeText(activity, "Camera permission is
//                // needed for this app to run ",
//                // Toast.LENGTH_SHORT).show();
//                // System.out.println("requestCameraPermission() SHOW INFO");
//
//                // Show an explanation to the user *asynchronously* -- don't
//                // block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        REQUEST_PERMISSION_LOCATION);
//
//            } else {
//                // No explanation needed, we can request the permission.
//                // System.out.println("requestCameraPermission() ASK
//                // PERMISSION");
//
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        REQUEST_PERMISSION_LOCATION);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//            // Permission is granted
//        } else {
//            System.out.println("requestLocationPermission() PERMISSION ALREADY GRANTED");
//
//        }
//
//    }
//
//    public static void requestWriteExternalPermission(Activity activity) {
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Toast.makeText(activity, "Write permission is needed to create Excel file ", Toast.LENGTH_SHORT).show();
//                // Show an explanation to the user *asynchronously* -- don't
//                // block this thread waiting for the user's response! After the
//                // user sees the explanation, try again to request the
//                // permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        REQUEST_WRITE_EXTERNAL);
//
//                Toast.makeText(activity, "REQUEST LOCATION PERMISSION", Toast.LENGTH_LONG).show();
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        REQUEST_WRITE_EXTERNAL);
//
//            }
//        }
//    }
//
//    public static void requestInternetPermission(Activity activity) {
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.INTERNET)) {
//                Toast.makeText(activity, "Internet permission is needed... ", Toast.LENGTH_SHORT).show();
//                // Show an explanation to the user *asynchronously* -- don't
//                // block this thread waiting for the user's response! After the
//                // user sees the explanation, try again to request the
//                // permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET},
//                        REQUEST_INTERNET);
//
//                Toast.makeText(activity, "INTERNET PERMISSION", Toast.LENGTH_LONG).show();
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET},
//                        REQUEST_INTERNET);
//
//            }
//        }
//    }
//
//    public static void requestAccessNetworkStatePermission(Activity activity) {
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.ACCESS_NETWORK_STATE)) {
//                Toast.makeText(activity, "Access Network State is needed ...", Toast.LENGTH_SHORT).show();
//                // Show an explanation to the user *asynchronously* -- don't
//                // block this thread waiting for the user's response! After the
//                // user sees the explanation, try again to request the
//                // permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
//                        REQUEST_ACCESS_NETWORK_STATE);
//
//                Toast.makeText(activity, "ACCESS NETWORK STATE PERMISSION", Toast.LENGTH_LONG).show();
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
//                        REQUEST_ACCESS_NETWORK_STATE);
//
//            }
//        }
//    }
//
//
//    public static boolean hasPermissions(Context context, String... permissions) {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public interface OnPermissionListener {
        public void onPermissionListener(int permission);
    }

}