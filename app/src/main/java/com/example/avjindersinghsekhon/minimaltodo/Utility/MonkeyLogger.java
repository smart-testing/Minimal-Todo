package com.example.avjindersinghsekhon.minimaltodo.Utility;

import android.util.Log;

import java.io.IOException;

import okhttp3.*;

public class MonkeyLogger {

    public static void write(String message) {
        Log.d("MonkeyLogger", message);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("text/plain"), message);
        Request request = new Request.Builder().url("http://10.0.2.2:8080/log")
                    .post(body)
                    .build();
        Thread thread = new Thread(() -> {
            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) { }
    }
}
