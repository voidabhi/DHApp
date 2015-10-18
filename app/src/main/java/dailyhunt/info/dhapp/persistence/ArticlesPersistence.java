package dailyhunt.info.dhapp.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 19/10/15.
 */
public class ArticlesPersistence {

    private static final String TAG = "bookmarked_articles";

    public static void saveBookmarkedArticle(Context context, Article article) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(article);

        editor.putString(TAG, json);
        editor.commit();
    }

    public static ArrayList<Article> fetchBookmarkedArticles(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, null);
        Type type = new TypeToken<ArrayList<Article>>() {}.getType();
        ArrayList<Article> articles = gson.fromJson(json, type);
        return articles;
    }
}
