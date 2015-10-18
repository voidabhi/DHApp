package dailyhunt.info.dhapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dailyhunt.info.dhapp.R;
import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 18/10/15.
 */
public class ArticleListAdapter extends ArrayAdapter<Article> {

//    private ArrayList<Article> articles;

    public ArticleListAdapter(Context context, ArrayList<Article> items) {
        super(context, 0, items);
//        this.articles = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Article article = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item,null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.article_title);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.article_image);

        title.setText(article.getTitle());
        Picasso.with(getContext()).load(article.getImageUrl()).fit().centerCrop().into(imageView);


        return convertView;

    }

    @Override
    public Article getItem(int position) {
//        if (articles!=null) {
//            return this.articles.get(position);
//        }
        return super.getItem(position);
    }

    @Override
    public int getCount() {
//        if (articles!=null) {
//            return this.articles.size();
//        }
        return super.getCount();
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if (constraint.toString().length() != 0 && articles!=null) {
//                    ArrayList<Article> matchedArticles = new ArrayList<Article>();
//
//                    for (Article article : articles) {
//                        if (article.getTitle().indexOf(constraint.toString())!= -1) {
//                            matchedArticles.add(article);
//                        }
//                    }
//
//                    filterResults.values = matchedArticles;
//                    filterResults.count = matchedArticles.size();
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
//                if (constraint.toString().length() != 0) {
//                    articles = (ArrayList<Article>) results.values;
//                    if (results.count > 0 && articles!=null) {
//                        notifyDataSetChanged();
//                    } else {
//                        notifyDataSetInvalidated();
//                    }
//                } else {
//                    articles = null;
//                    notifyDataSetInvalidated();
//                }
//            }
//
//        };
//    }

}
