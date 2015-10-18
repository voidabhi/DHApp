package dailyhunt.info.dhapp.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by voidabhi on 18/10/15.
 */
public class Article {


    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_SOURCE = "source";
    private static final String JSON_KEY_CATEGORY = "category";
    private static final String JSON_KEY_IMAGE_URL = "image";
    private static final String JSON_KEY_CONTENT = "content";
    private static final String JSON_KEY_URL = "url";

    private String title;
    private String source;
    private String category;
    private String imageUrl;
    private String content;
    private String url;

    public Article() {

    }

    public Article(String title, String source, String category, String imageUrl, String content, String url) {
        this.title = title;
        this.source = source;
        this.category = category;
        this.imageUrl = imageUrl;
        this.content = content;
        this.url =  url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public static Article fromJson(JSONObject jsonObject) {
        Article a = new Article();
        // Deserialize json into object fields
        try {
            a.setContent(jsonObject.has(JSON_KEY_CONTENT) ? jsonObject.getString(JSON_KEY_CONTENT) : "");
            a.setCategory(jsonObject.has(JSON_KEY_CATEGORY) ? jsonObject.getString(JSON_KEY_CATEGORY) : "");
            a.setTitle(jsonObject.has(JSON_KEY_TITLE) ? jsonObject.getString(JSON_KEY_TITLE) : "");
            a.setSource(jsonObject.has(JSON_KEY_SOURCE) ? jsonObject.getString(JSON_KEY_SOURCE) : "");
            a.setImageUrl(jsonObject.has(JSON_KEY_IMAGE_URL) ? jsonObject.getString(JSON_KEY_IMAGE_URL) : "");
            a.setUrl(jsonObject.has(JSON_KEY_URL) ? jsonObject.getString(JSON_KEY_URL) : "");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return a;
    }

    public static ArrayList<Article> fromJson(JSONArray jsonArray) {
        ArrayList<Article> articles = new ArrayList<Article>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject articleJson = null;
            try {
                articleJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Article article = Article.fromJson(articleJson);
            if (article != null) {
                articles.add(article);
            }
        }

        return articles;
    }

    @Override
    public String toString() {
        return title + ',' + source;
    }
}







