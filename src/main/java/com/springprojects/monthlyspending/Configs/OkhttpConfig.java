package com.springprojects.monthlyspending.Configs;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkhttpConfig {

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request newReq = original.newBuilder()
//                            .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJseEs2OE5ucXBHZXlsbmg3aDlPUEZKR19rSVdYVGVyMENtd1UybFFzVHN3IiwiaWF0IjoxNzYzMzQ5NDgxfQ._KGKtl6cXesPHU8-luCeDU-JCn9yNh6HTiXyDzNyHy0")
                            .addHeader("Content-Type", "application/json")
                            .build();

                    return chain.proceed(newReq);
                })
                .build();
    }
}
