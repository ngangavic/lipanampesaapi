package com.ngangavictor.lipanampesa.okhttp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ngangavictor.lipanampesa.error.CheckError;
import com.ngangavictor.lipanampesa.result.Result;
import com.ngangavictor.lipanampesa.settings.SandBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getAccessToken() throws JSONException,IOException{
        CheckError checkError = new CheckError();
        checkError.getError();
            String key = SandBox.consumerKey + ":" + SandBox.consumerSecret;
            byte[] bytes = key.getBytes("ISO-8859-1");
            String encoded = Base64.getEncoder().encodeToString(bytes);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(SandBox.access_token_url)
                    .get()
                    .addHeader("authorization", "Basic "+encoded)
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject jsonObject=new JSONObject(response.body().string());
            System.out.println(jsonObject.getString("access_token"));
            return jsonObject.getString("access_token");
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getRequest(String requestJson,String url) throws JSONException,IOException{
        CheckError checkError = new CheckError();
        checkError.getError();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+getAccessToken())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        String s=response.body().string();
        System.out.println(s);
        JSONObject jsonObject = new JSONObject(s);
        if (jsonObject.getString("ResponseCode").equals("0")) {
            Result.ResponseCode=jsonObject.getString("ResponseCode");
            Result.MerchantRequestID=jsonObject.getString("MerchantRequestID");
            Result.CheckoutRequestID=jsonObject.getString("CheckoutRequestID");
            Result.ResponseDescription=jsonObject.getString("ResponseDescription");
            Result.CustomerMessage=jsonObject.getString("CustomerMessage");
        }else{
            Result.ResponseCode="1";
        }
        return response.body().toString();
    }




}
