package com.mcterni.board_game_trading;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReturnHTTP {

    private InputStream stream;

    public InputStream getStream(String url, final ReturnHTTPCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure();
                    throw new IOException("Unexpected code " + response);
                } else {
                    stream = response.body().byteStream();
                    callback.onSuccess();
                }
            }
        });
        return stream;
    }


    public interface ReturnHTTPCallback{
        void onFailure();
        void onSuccess();
    }

}
