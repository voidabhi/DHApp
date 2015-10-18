package dailyhunt.info.dhapp.services.handlers;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 18/10/15.
 */
public class ArticlesResponseHandler extends JsonHttpResponseHandler {

    private static final String DEBUG_TAG = "ArticlesResponseHandler";

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Log.d(DEBUG_TAG, "onSuccess : " + response.toString());
        if (response!=null && response.has("articles")) {
            try {
                onArticlesFetchedResult(null, Article.fromJson(response.getJSONArray("articles")));
                Log.d(DEBUG_TAG, "Response : " + response.toString());
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "Error : " + e.getMessage());
                onArticlesFetchedResult(e, null);
            }
        } else {
            Log.d(DEBUG_TAG, "Response : null");
            onArticlesFetchedResult(null, null);
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        Log.d(DEBUG_TAG, "onFailure : " + responseString);
        onArticlesFetchedResult(new Exception(throwable), null);
    }

    public void onArticlesFetchedResult(Exception e, ArrayList<Article> articles) {

    }
}
