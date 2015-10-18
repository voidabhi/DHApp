package dailyhunt.info.dhapp.services;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import dailyhunt.info.dhapp.domain.Article;
import dailyhunt.info.dhapp.services.handlers.ArticlesResponseHandler;
import dailyhunt.info.dhapp.services.handlers.SourceCountHandler;

/**
 * Created by voidabhi on 18/10/15.
 */
public class DHService {

    private static final String ACTIVITY_DEBUG_TAG = "DHService";

    private static final String BASE_URL = "http://dailyhunt.0x10.info/api";
    private static final String ARTICLES_ENDPOINT = "/dailyhunt?type=json&query=list_news";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(ACTIVITY_DEBUG_TAG, "URL : " + getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void fetchArticles(ArticlesResponseHandler handler) {
        get(ARTICLES_ENDPOINT, null, handler);
    }

    public static void fetchSourceCount(SourceCountHandler handler) {
        get(ARTICLES_ENDPOINT, null, handler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


}
