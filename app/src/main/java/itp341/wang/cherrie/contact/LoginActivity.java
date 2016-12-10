package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        enterButton = (Button) findViewById(R.id.buttonEnter);
        listeners();
    }

    private void listeners(){
        // Listener to login
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verify login information

                // Start intent
                Intent savedIntent = new Intent(getApplicationContext(), SavedActivity.class);
                startActivityForResult(savedIntent, 0);
            }
        });
    }
}
