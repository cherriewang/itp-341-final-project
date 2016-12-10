package itp341.wang.cherrie.contact;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.model.Senator;

public class ResultsActivity extends AppCompatActivity {

    private static final String TAG = Activity.class.getName();
    ListView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        HashMap<String, ArrayList<Senator>> repMap =
                (HashMap<String, ArrayList<Senator>> ) intent.getSerializableExtra("map");

        resultView = (ListView) findViewById(R.id.result_lv);

        ArrayAdapter<Senator> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                repMap.get("results"));

        resultView.setAdapter(arrayAdapter);
    }
}
