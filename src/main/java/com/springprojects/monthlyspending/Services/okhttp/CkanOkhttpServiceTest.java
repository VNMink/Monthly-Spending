package com.springprojects.monthlyspending.Services.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springprojects.monthlyspending.Constants.ApiResponse;
import com.springprojects.monthlyspending.Dtos.test.TestRequest;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CkanOkhttpServiceTest {
    private final OkHttpClient client;

    private final ObjectMapper mapper;
    private static final String BASE_URL = "http://localhost:5000/api/3/action/organization_create";

    public ApiResponse createOrganization(TestRequest test, String token) throws IOException {
        String json = mapper.writeValueAsString(test);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Call call = client.newCall(request);

        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                return new ApiResponse(400, "CKAN returned an error", response.body() != null ? response.body().string() : null);
            }

            return new ApiResponse(200, "Organization created", response.body() != null ? response.body().string() : null);
        } catch (SocketTimeoutException e) {
            return new ApiResponse(504, "Request timed out", null);

        } catch (IOException e) {
            return new ApiResponse(502, "Network error: " + e.getMessage(), null);

        } catch (Exception e) {
            return new ApiResponse(500, "Unexpected error: " + e.getMessage(), null);
        }
    }
}
