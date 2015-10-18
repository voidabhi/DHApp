package dailyhunt.info.dhapp.services.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 18/10/15.
 */
public class SourceCountHandler extends JsonHttpResponseHandler {

    private static final String DEBUG_TAG = "SourceCountHandler";

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Log.d(DEBUG_TAG, "onSuccess : " + response.toString());
        if (response!=null && response.has("articles")) {
            try {
                List<Article> articles = Article.fromJson(response.getJSONArray("articles"));
                List<String> sources = new ArrayList<String>();
                for (Article article : articles) {
                    if (!sources.contains(article.getSource())) {
                        sources.add(article.getSource());
                    }
                }

                onSourceCountResult(null, sources.size());
                Log.d(DEBUG_TAG, "Response : " + response.toString());
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "Error : " + e.getMessage());
                onSourceCountResult(e, -1);
            }
        } else {
            Log.d(DEBUG_TAG, "Response : null");
            onSourceCountResult(null, -1);
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        Log.d(DEBUG_TAG, "onFailure : " + responseString);
        onSourceCountResult(new Exception(throwable), -1);
    }

    public void onSourceCountResult(Exception e, int count) {

    }
}