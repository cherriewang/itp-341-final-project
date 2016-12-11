package itp341.wang.cherrie.contact.tab_fragments;

/**
 * Created by Cherrie on 12/10/16.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;

public class OneFragment extends Fragment{

    private Senator fragSenator1;
    private TextView phoneNumber;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        Bundle bundle = getArguments();
        fragSenator1 = bundle.getParcelable("sen");
        phoneNumber = (TextView) rootView.findViewById(R.id.phoneNum);

        phoneNumber.setText(fragSenator1.getPhone().toString());
        // Inflate the layout for this fragment
        return rootView;

    }

}