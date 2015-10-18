package dailyhunt.info.dhapp;

import android.app.PendingIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import javax.xml.transform.Source;

import dailyhunt.info.dhapp.domain.Article;
import dailyhunt.info.dhapp.fragments.ArticleListFragment;
import dailyhunt.info.dhapp.fragments.BookmarkedArticleListFragment;
import dailyhunt.info.dhapp.services.DHService;
import dailyhunt.info.dhapp.services.handlers.ArticlesResponseHandler;
import dailyhunt.info.dhapp.services.handlers.SourceCountHandler;
import dailyhunt.info.dhapp.utils.NetUtils;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class MainActivity extends MaterialNavigationDrawer {

    private static final String DEBUG_TAG = "MainActivity";


    @Override
    public void init(Bundle savedInstanceState) {

        this.setDrawerHeaderImage(R.drawable.web_hi_res_512);
        this.addSection(this.newSection("Articles", new ArticleListFragment()));
        this.addSection(this.newSection("Bookmarks", new BookmarkedArticleListFragment()));

    }

    @Override
    protected void onStart() {
        super.onStart();
        //this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final TextView t = (TextView)findViewById(R.id.text);
//        t.setText("Loading...");
//        if (NetUtils.isOnline(getApplicationContext())) {
//            Log.d(DEBUG_TAG, "User is online");
////            DHService.fetchArticles(new ArticlesResponseHandler() {
////                @Override
////                public void onArticlesFetchedResult(Exception e, ArrayList<Article> articles) {
////                    super.onArticlesFetchedResult(e, articles);
////
////                    Log.d(DEBUG_TAG, "Articles Fetched " + articles.size());
////
////                    if (e != null) {
////                        t.setText("Some Exception occurred");
////                    } else {
////                        t.setText(articles.get(0).getCategory());
////                    }
////                }
////            });
//
//            DHService.fetchSourceCount(new SourceCountHandler() {
//                @Override
//                public void onSourceCountResult(Exception e, int count) {
//                    super.onSourceCountResult(e, count);
//
//                    Log.d(DEBUG_TAG, "Source count Fetched " + count);
//
//                    if (e != null) {
//                        t.setText("Some Exception occurred");
//                    } else {
//                        t.setText("Count : " + count);
//                    }
//                }
//            });
//        } else {
//            Log.d(DEBUG_TAG, "User is not online");
//            t.setText("Not Online");
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
