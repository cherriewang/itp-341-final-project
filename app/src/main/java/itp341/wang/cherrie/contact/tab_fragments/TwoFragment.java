package itp341.wang.cherrie.contact.tab_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;


public class TwoFragment extends Fragment {

    private Senator fragSenator2;
    private TextView website;
    private TextView partyAffiliation;

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
        partyAffiliation = (TextView) rootView.findViewById(R.id.email_tv);

        website.setText(fragSenator2.getLink().toString());
        partyAffiliation.setText(fullPartyForm(fragSenator2.getParty()));
        Linkify.addLinks(website, Linkify.ALL);
        // Inflate the layout for this fragment
        return rootView;
    }

    public String fullPartyForm(String abbrev){

        if(abbrev.equals("D")){
            return "Democratic Party";
        }
        if(abbrev.equals("R")){
            return "Republican Party";
        }
        if(abbrev.equals("I")){
            return "Independent";
        } else{
            return "No listed Party Affiliation";
        }

    }
}
