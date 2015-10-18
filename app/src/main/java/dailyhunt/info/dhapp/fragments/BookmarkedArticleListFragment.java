package dailyhunt.info.dhapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dailyhunt.info.dhapp.MainActivity;
import dailyhunt.info.dhapp.R;
import dailyhunt.info.dhapp.adapters.ArticleListAdapter;
import dailyhunt.info.dhapp.domain.Article;
import dailyhunt.info.dhapp.services.DHService;
import dailyhunt.info.dhapp.services.handlers.ArticlesResponseHandler;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by voidabhi on 18/10/15.
 */
public class BookmarkedArticleListFragment extends Fragment {

    private static final String DEBUG_TAG = "BookmarkedArticleListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles_list,container,false);
        ListView bookmarkedArticlesListView = (ListView)view.findViewById(R.id.bookmarked_article_list);

        return view;
    }
}