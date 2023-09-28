    package it.mirea.kursovaya;

    import android.app.AlertDialog;
    import android.content.ContentProviderOperation;
    import android.content.Context;
    import android.os.Handler;
    import android.os.Looper;


    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.IOException;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    import okhttp3.Call;
    import okhttp3.MediaType;
    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.RequestBody;
    import okhttp3.Response;
    import retrofit2.Callback;

    public class ApiClient {

        private OkHttpClient client;
        private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        private Context context;
        private SharedPrefManager prefManager;
        private String HOST = "http://172.20.10.3:5000";

        public ApiClient(Context context) {
            if (this.client == null) {
                this.client = new OkHttpClient();
            }
            if (this.context == null) {
                this.context = context;
            }
            if (this.prefManager == null) {
                this.prefManager = new SharedPrefManager(context);
            }
        }


        public void registerUser(String username, String password, String email, ResponseCallback responseCallback, ErrorCallback errorCallback) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", username);
                jsonObject.put("password", password);
                jsonObject.put("email", email);
            } catch (JSONException e) {
                errorCallback.onError(e);
                return;
            }

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(HOST + "/register")
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    errorCallback.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        responseCallback.onResponse(response);
                        authAndSaveId(username);
                    } else {
                        errorCallback.onError(new IOException("Unexpected code " + response));
                    }
                }
            });
        }

        public void loginUser(String username, String password, ResponseCallback responseCallback, ErrorCallback errorCallback) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", username);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                errorCallback.onError(e);
                return;
            }

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(HOST + "/login")
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    errorCallback.onError(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        responseCallback.onResponse(response);
                        authAndSaveId(username);
                    } else {
                        errorCallback.onError(new IOException("Unexpected code " + response));
                    }
                }
            });
        }

        private void authAndSaveId(String username) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                try {
                    // создаем JSON объект с параметром username
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username", username);

                    RequestBody body = RequestBody.create(JSON, jsonObject.toString());

                    Request request = new Request.Builder()
                            .url(HOST + "/auth")
                            .post(body)
                            .build();

                    Response response = client.newCall(request).execute();

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    // парсим ответ и извлекаем id
                    String responseStr = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseStr);
                    int id = jsonResponse.getInt("id");

                    // сохраняем id в SharedPreferences
                    handler.post(() -> prefManager.saveUserDetails(id, 1, 1));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }



        public void AddNewFest(String name, String description, String date_time,
                               String category_name, String price, String location,
                               ResponseCallback responseCallback, ErrorCallback errorCallback) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name);
                jsonObject.put("description", description);
                jsonObject.put("date_time", date_time);
                jsonObject.put("category_name", category_name);
                jsonObject.put("price", price);
                jsonObject.put("location", location);
            } catch (JSONException e) {
                errorCallback.onError(e);
                return;
            }

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(HOST + "/addfest")
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        responseCallback.onResponse(response);
                    } else {
                        errorCallback.onError(new Exception("Request failed"));
                    }
                }
                @Override
                public void onFailure(Call call, IOException e) {
                    errorCallback.onError(e);
                }
            });
        }

        public void deleteFestByName(String name, ResponseCallback responseCallback, ErrorCallback errorCallback) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name);
            } catch (JSONException e) {
                errorCallback.onError(e);
                return;
            }

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(HOST + "/deletefest")
                    .delete(body)
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        responseCallback.onResponse(response);
                    } else {
                        errorCallback.onError(new Exception("Request failed"));
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    errorCallback.onError(e);
                }
            });
        }


        public interface ResponseCallback {
            void onResponse(Response response) throws IOException;
        }

        public interface ErrorCallback {
            void onError(Exception e);
        }
    }

