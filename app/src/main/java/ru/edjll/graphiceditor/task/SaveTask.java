package ru.edjll.graphiceditor.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

public class SaveTask extends AsyncTask<Bitmap, Void, String> {
    @Override
    protected String doInBackground(Bitmap... bitmaps) {
        URL url = null;
        HttpURLConnection connection = null;
        String id = null;
        try {
            url = new URL("http://192.168.1.9:8085/save");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setChunkedStreamingMode(0);
            connection.connect();

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmaps[0].compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            outputStream.write(("{\"imageBase64\": " + "\"" + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()) + "\"}").getBytes());

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
                InputStream inputStream = connection.getInputStream();
                id = new BufferedReader(new InputStreamReader(inputStream)).readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return id;
    }
}
