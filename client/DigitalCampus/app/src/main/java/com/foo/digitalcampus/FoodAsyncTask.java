package com.foo.digitalcampus;

import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shgl1hz1 on 2017/5/2.
 */

public class FoodAsyncTask extends AsyncTask<String,Void,Food> {
    private ListView lvFood;
    private FoodCallback callback;

    public FoodAsyncTask(ListView lvFood, FoodCallback callback) {
        this.lvFood = lvFood;
        this.callback = callback;
    }

    @Override
    protected Food doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(params[0]).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                String jsonStr = response.body().string();
                Gson gson = new Gson();
                Food food = gson.fromJson(jsonStr,Food.class);
                return food;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Food food) {

        super.onPostExecute(food);
        callback.callback(food);
    }
}
