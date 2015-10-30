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
public class ArticleListFragment extends Fragment {

    private static final String DEBUG_TAG = "ArticleListFragment";

    private ArticleListAdapter articleListAdapter;
    Spinner categories;
    ListView articleListView;
    ArrayList<Article> articles;

    private ArrayList<String> categoriesList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(DEBUG_TAG, "Master created");

        View view = inflater.inflate(R.layout.fragment_articles_list,container,false);
        articleListView = (ListView)view.findViewById(R.id.article_list);
        final EditText searchEditText = (EditText)view.findViewById(R.id.article_search);
        categories = (Spinner)view.findViewById(R.id.spinner_categories);
        categoriesList = new ArrayList<String>();

        DHService.fetchArticles(new ArticlesResponseHandler() {
            @Override
            public void onArticlesFetchedResult(Exception e, ArrayList<Article> fetchedArticles) {
                super.onArticlesFetchedResult(e, fetchedArticles);
                articles = fetchedArticles;
                if (e == null) {
                    for (Article article : articles) {
                        if (!categoriesList.contains(article.getCategory())) {
                            categoriesList.add(article.getCategory());
                        }
                    }

                    ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoriesList);
                    categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categories.setAdapter(categoriesAdapter);

//                    categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            ArrayList<Article> categorizedArticles = new ArrayList<Article>();
//                            for (Article article : articles) {
//                                if (article.getCategory().equals(categoriesList.get(position))) {
//                                    categorizedArticles.add(article);
//                                    articleListAdapter = new ArticleListAdapter(getActivity(), categorizedArticles);
//                                    articleListView.setAdapter(articleListAdapter);
//                                }
//                            }
//                        }
//                    });

                    articleListAdapter = new ArticleListAdapter(getActivity(), articles);
                    articleListView.setAdapter(articleListAdapter);
                }
            }
        });

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (articles!=null && articles.size() > 0) {
                    PlaceholderFragment fragment = new PlaceholderFragment();
                    fragment.setArticle(articles.get(position));
                    ((MaterialNavigationDrawer) getActivity()).setFragmentChild(fragment, articles.get(position).getTitle());
                }
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                if (articleListAdapter != null || articleListAdapter.getCount() == 0) {
                    articleListAdapter.getFilter().filter(cs);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        //fetchArticles(null);

        return view;
    }

    // Fetch articles from dh endpoint
    public void fetchArticles(final String category) {
        DHService.fetchArticles(new ArticlesResponseHandler() {
            @Override
            public void onArticlesFetchedResult(Exception e, ArrayList<Article> fetchedArticles) {
                super.onArticlesFetchedResult(e, fetchedArticles);
                articles = fetchedArticles;
                if (e == null) {
                    articleListAdapter = new ArticleListAdapter(getActivity(), new ArrayList<Article>());
                        for (Article article : articles) {
                            if (!category.equals(article.getCategory())) {
                                articleListAdapter.add(article);
                            }
                        }

                    articleListView.setAdapter(articleListAdapter);
                }
            }
        });
    }
}
