package itp341.wang.cherrie.contact.tab_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;


public class TwoFragment extends Fragment {

    private Senator fragSenator2;
    private TextView website;
    private TextView email;

    public TwoFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);

        Bundle bundle = getArguments();
        fragSenator2 = bundle.getParcelable("sen");
        website = (TextView) rootView.findViewById(R.id.website_tv);
        email = (TextView) rootView.findViewById(R.id.email_tv);

        website.setText(fragSenator2.getLink().toString());
        // Inflate the layout for this fragment
        return rootView;
    }
}
