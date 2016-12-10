package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        loginButton = (Button) findViewById(R.id.buttonLogin);
        signupButton = (Button) findViewById(R.id.buttonSignup);
        listeners();
    }

    private void listeners(){
        // Listener to login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start intent
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(loginIntent, 0);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start intent
                Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(signupIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(0): {
            }
            break;
            case(1):{
            }
            break;
        }
    }
}
