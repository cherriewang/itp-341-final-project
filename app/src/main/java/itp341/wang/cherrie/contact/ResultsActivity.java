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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;

public class ResultsActivity extends AppCompatActivity {
    public static final String EXTRA_SENATOR = "com.itp341.wang.cherrie.senator";
    private static final String TAG = Activity.class.getName();
    ListView resultView;
    SenatorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //getSupportActionBar().hide();

        Intent intent = getIntent();
        HashMap<String, ArrayList<Senator>> repMap =
                (HashMap<String, ArrayList<Senator>> ) intent.getSerializableExtra("map");

        resultView = (ListView) findViewById(R.id.result_lv);

//        ArrayAdapter<Senator> arrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                repMap.get("results"));
//
//        resultView.setAdapter(arrayAdapter);

        adapter = new SenatorAdapter(this, repMap.get("results"));
        resultView.setAdapter(adapter);


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
                    //detailIntent.putExtra(EXTRA_SENATOR, position);
                    detailIntent.putExtra(EXTRA_SENATOR, selectedSenator);
                    startActivityForResult(detailIntent,2);
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
