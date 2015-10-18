package dailyhunt.info.dhapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by voidabhi on 18/10/15.
 */
public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(this.getActivity());
        text.setText("Section");
        text.setGravity(Gravity.CENTER);
        return text;
    }

}