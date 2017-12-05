package org.yasriady.ustadzsomadstreaming.Utility;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dedy on 11/10/17.
 */

public class DownloadFileFromUrl extends AsyncTask<String, String, String> {

    private DownloadFileFromURLListener m_listener;

    public DownloadFileFromUrl(DownloadFileFromURLListener listener) {
        if (listener instanceof DownloadFileFromURLListener) {
            m_listener = (DownloadFileFromURLListener) listener;
        } else {
            throw new RuntimeException(m_listener.toString()
                    + " must implement DownloadFileFromURLListener");
        }

    }

    /**
     * Before starting background thread
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Starting download");

        //pDialog = new ProgressDialog(MainActivity.this);
        //pDialog.setMessage("Loading... Please wait...");
        //pDialog.setIndeterminate(false);
        //pDialog.setCancelable(false);
        //pDialog.show();
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {

        int count;
        String str ="";

        try {
            String root = Environment.getExternalStorageDirectory().toString();

            System.out.println("Downloading");
            URL url = new URL(f_url[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file

            OutputStream output = new FileOutputStream(root+"/downloadedfile.txt");
            byte data[] = new byte[1024];
            ByteArrayOutputStream output2 = new ByteArrayOutputStream();

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;

                // writing data to file
                output.write(data, 0, count);

                // writing data to ByteArrayOutputString
                output2.write(data,0, count);

            }

            str = output2.toString();

            // flushing output
            output.flush();
            output2.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return str;
    }

    /**
     * After completing background task
     * **/
    @Override
    protected void onPostExecute(String jsonStr) {
        System.out.println("Downloaded");

        m_listener.onFileDownloaded(jsonStr);

        //pDialog.dismiss();
    }


    public interface DownloadFileFromURLListener {
        void onFileDownloaded(final String jsonString);
    }
}
