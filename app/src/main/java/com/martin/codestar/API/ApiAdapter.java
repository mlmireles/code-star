package com.martin.codestar.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static final String BASE_URL = "https://api.github.com";
    private static ApiInterface API_SERVICE;

    /**
     * Initialize the API_SERVICE instance to
     * make the request to the API REST server
     * @return ApiInterface instance
     */
    public static ApiInterface getApiService() {
        if (API_SERVICE == null) {
            OkHttpClient client;
            HttpLoggingInterceptor interceptor;
            Retrofit retrofit;

            client = new OkHttpClient();
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            API_SERVICE = retrofit.create(ApiInterface.class);
        }
        return API_SERVICE;
    }
}
