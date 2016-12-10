package itp341.wang.cherrie.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    private EditText searchQuery;
    private String stateToSearch;
    private Button searchButton;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        initialize();
        listeners();
    }

    void initialize(){
        searchQuery = (EditText) findViewById(R.id.editTextSearch);
        searchButton = (Button) findViewById(R.id.buttonSearch);
    }

    void listeners(){
        // Listener for when you want to search
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save string from EditText
                stateToSearch = searchQuery.getText().toString();
                makeHTTPrequest();
            }
        });

    }

    void makeHTTPrequest(){
        Log.e(TAG, "making a request");
        new GetMethodSearch().execute("http://www.whoismyrepresentative.com/getall_sens_bystate.php?state=ME&output=json");
    }

}
