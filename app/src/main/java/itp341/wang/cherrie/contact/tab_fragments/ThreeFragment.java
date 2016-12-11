package itp341.wang.cherrie.contact.tab_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;


public class ThreeFragment extends Fragment {

    private Senator fragSenator3;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        Bundle bundle = getArguments();
        fragSenator3 = bundle.getParcelable("sen");
        // Inflate the layout for this fragment
        return rootView;
    }

}
