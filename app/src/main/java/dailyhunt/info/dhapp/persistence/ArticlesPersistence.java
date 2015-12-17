package dailyhunt.info.dhapp.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 19/10/15.
 */
public class ArticlesPersistence {

    private static final String APP_PREFERENES_KEY = "dhapp";
    private static final String BOOKMARKED_ARTICLES_KEY = "bookmarked_articles";

    // save bookmarked articles
    public static void saveArticles(Context context, ArrayList<Article> articles) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(APP_PREFERENES_KEY,Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(articles);
        editor.putString(BOOKMARKED_ARTICLES_KEY, jsonFavorites);
        editor.commit();
    }

    // fetch bookmarked articles
    public static ArrayList fetchBookmarkedArticles(Context context) {
        SharedPreferences settings;
        List<Article> articles;
        settings = context.getSharedPreferences(APP_PREFERENES_KEY,Context.MODE_PRIVATE);
        if (settings.contains(BOOKMARKED_ARTICLES_KEY)) {
            String jsonFavorites = settings.getString(BOOKMARKED_ARTICLES_KEY, null);
            Gson gson = new Gson();
            Article[] articleItems = gson.fromJson(jsonFavorites,Article[].class);
            articles = Arrays.asList(articleItems);
            articles = new ArrayList(articles);
        } else
            return null;
        return (ArrayList<Article>) articles;
    }
    
    // saving bookmarked article
    public static void saveBookmarkedArticle(Context context, Article article) {
        ArrayList<Article> articles = fetchBookmarkedArticles(context);
        if (articles == null)
            articles = new ArrayList();
        articles.add(article);
        saveArticles(context, articles);
    }

    // removing bookmarked article
    public static void removeBookmarkedArticle(Context context, Article article) {
        ArrayList<Article> articles = fetchBookmarkedArticles(context);
        if (articles != null) {
            articles.remove(article);
            saveArticles(context, articles);
        }
    }
}
