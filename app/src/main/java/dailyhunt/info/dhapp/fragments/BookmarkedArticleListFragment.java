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

import dailyhunt.info.dhapp.R;
import dailyhunt.info.dhapp.adapters.ArticleListAdapter;
import dailyhunt.info.dhapp.domain.Article;
import dailyhunt.info.dhapp.persistence.ArticlesPersistence;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by voidabhi on 18/10/15.
 */
public class BookmarkedArticleListFragment extends Fragment {

    private static final String DEBUG_TAG = "BookmarkedArticleListFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bookmarked_article_list,container,false);
        ListView bookmarkedArticlesListView = (ListView)view.findViewById(R.id.bookmarked_article_list);
        final ArrayList<Article> articles = ArticlesPersistence.fetchBookmarkedArticles(getActivity().getApplicationContext());
        if (articles!=null) {
            ArticleListAdapter articleListAdapter = new ArticleListAdapter(getActivity(), articles);
            bookmarkedArticlesListView.setAdapter(articleListAdapter);

            bookmarkedArticlesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ArticlesPersistence.removeBookmarkedArticle(getActivity(), articles.get(position));
                    return false;
                }
            });

            bookmarkedArticlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (articles != null && articles.size() > 0) {
                        PlaceholderFragment fragment = new PlaceholderFragment();
                        fragment.setArticle(articles.get(position));
                        ((MaterialNavigationDrawer) getActivity()).setFragmentChild(fragment, articles.get(position).getTitle());
                    }
                }
            });
        }
        return view;
    }
}