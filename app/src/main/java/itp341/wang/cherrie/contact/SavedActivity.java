package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SavedActivity extends AppCompatActivity {
    Button goToSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        getSupportActionBar().hide();
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
}
