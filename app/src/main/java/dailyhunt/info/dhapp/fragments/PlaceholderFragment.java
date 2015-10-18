package dailyhunt.info.dhapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import dailyhunt.info.dhapp.R;
import dailyhunt.info.dhapp.domain.Article;

/**
 * Created by voidabhi on 18/10/15.
 */
public class PlaceholderFragment extends Fragment {

    private Article article;

    public PlaceholderFragment() {

    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        TextView title = (TextView)view.findViewById(R.id.article_title);
        ImageView image = (ImageView)view.findViewById(R.id.article_image);
        TextView content = (TextView)view.findViewById(R.id.article_content);

        Button btn_bookmark = (Button)view.findViewById(R.id.btn_bookmark);
        Button btn_view = (Button)view.findViewById(R.id.btn_view);
        Button btn_share = (Button)view.findViewById(R.id.btn_share);

        if(article!=null) {
            title.setText(article.getTitle());
            Picasso.with(getActivity()).load(article.getImageUrl()).into(image);
            content.setText(article.getContent());

            btn_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Article Bookmarked", Toast.LENGTH_SHORT);
                }
            });

            btn_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(article.getUrl()));
                    startActivity(i);
                }
            });

            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, article.getSource() + " : " + article.getTitle());
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, article.getUrl());
                }
            });
        }
        return view;
    }

}