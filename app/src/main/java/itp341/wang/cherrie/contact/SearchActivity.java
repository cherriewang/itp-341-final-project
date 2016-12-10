package itp341.wang.cherrie.contact;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import itp341.wang.cherrie.contact.async.GetSenResponse;

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
                GetSenResponse response;

                if (isConnected()) {
                    // save string from EditText
                    String repState = searchQuery.getText().toString();
                    Log.i(TAG, getString(R.string.REPSTATE_LOG) + repState);
                    response = new GetSenResponse(SearchActivity.this);
                    response.execute(getString(R.string.SENSTATE_URL) + repState);
                    emptyTextField();
                }

            }
        });

    }

    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

    private void emptyTextField(){
        searchQuery.setText("");
    }

}
