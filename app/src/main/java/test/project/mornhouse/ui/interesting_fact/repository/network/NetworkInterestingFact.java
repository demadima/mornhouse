package test.project.mornhouse.ui.interesting_fact.repository.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import test.project.mornhouse.ui.interesting_fact.contract.ContractInterestingFact;

public class NetworkInterestingFact implements ContractInterestingFact.RepositoryNetwork {
    private OkHttpClient client = new OkHttpClient();

    @Override
    public void loadInfoFromUserNumber(OnFinishedListener onFinishedListener, String number) {
        String url = "http://numbersapi.com/"+ number+"?json";

        getOkHttpRequest(onFinishedListener,url);
    }

    @Override
    public void loadInfoFromRandomNumber(OnFinishedListener onFinishedListener) {
        String url = "http://numbersapi.com/random/math?json";

        getOkHttpRequest(onFinishedListener,url);
    }

    private  void  getOkHttpRequest (OnFinishedListener onFinishedListener, String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onFinishedListener.onFailure(e);
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    onFinishedListener.onFinished(responseBody.string());
                }
            }
        });
    }
}
