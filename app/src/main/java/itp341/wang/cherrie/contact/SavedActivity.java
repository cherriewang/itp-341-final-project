package itp341.wang.cherrie.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

import itp341.wang.cherrie.contact.model.Senator;
import itp341.wang.cherrie.contact.model.User;
import itp341.wang.cherrie.contact.utils.ContactApplication;

public class SavedActivity extends AppCompatActivity {
    public static final String EXTRA_SEN = "com.itp341.wang.cherrie.sen";
    public static final String EXTRA_HIDE = "com.itp341.wang.cherrie.hide";
    private static final String TAG = Activity.class.getName();
    private ListView resultView;
    private SenatorAdapter adapter;
    private Button goToSearchButton;
    private ArrayList<Senator> senList;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        myUser = ((ContactApplication) this.getApplication()).getMyUser();
        Log.e("Is my User being stored", "Hello: "+myUser.getUsername());
        // Create HashMap<String, ArrayList<Senator>>
        senList = new ArrayList<Senator>();

        //getSupportActionBar().hide();


        //HashMap<String, ArrayList<Senator>> repMap = myUser.getmSavedFormatted();

        resultView = (ListView) findViewById(R.id.result_lv);
        //adapter = new SenatorAdapter(this, repMap.get("results"));


        if (myUser.getmSavedSenators() != null) {
            convertSenList(myUser.getmSavedSenators());
            adapter = new SenatorAdapter(this, senList);
            resultView.setAdapter(adapter);
        }


        goToSearchButton = (Button) findViewById(R.id.buttonSearch);
        listeners();
    }

    private void listeners(){
        // Listener to login
        goToSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start intent
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(searchIntent, 0);
            }
        });
    }


    private class SenatorAdapter extends ArrayAdapter<Senator> {
        public SenatorAdapter(Context context, ArrayList<Senator> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Senator sen = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_row, parent, false);
            }
            // Lookup view for data population
            TextView senName = (TextView) convertView.findViewById(R.id.textView2);
            TextView senState = (TextView) convertView.findViewById(R.id.textView);
            Button moreButton = (Button) convertView.findViewById(R.id.buttonMore);

            // Populate the data into the template view using the data object
            senName.setText(sen.getName());
            senState.setText("State: "+sen.getState());

            moreButton.setTag(position);

            // Listener to view more
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Senator selectedSenator = getItem(position);

                    Log.e("ResultsActivity", "do we crash here");
                    // Start intent
                    Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                    detailIntent.putExtra(EXTRA_HIDE, true);
                    //detailIntent.putExtra(EXTRA_SENATOR, position);
                    detailIntent.putExtra(EXTRA_SEN, selectedSenator);
                    startActivityForResult(detailIntent,2);
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(0): {
                // should reset the views
            }
            break;
        }
    }

    public void convertSenList(HashMap<String, Senator> saved){
        for (String key : saved.keySet()) {
            senList.add(saved.get(key));
        }
    }


}
