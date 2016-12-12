package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import itp341.wang.cherrie.contact.model.User;
import itp341.wang.cherrie.contact.utils.ContactApplication;
import itp341.wang.cherrie.contact.utils.Debug;

public class SignupActivity extends AppCompatActivity {
    private Button enterButton;
    private EditText usernameEditText;
    private EditText passEditText;
    private EditText passEditTextAgain;
    private User myUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;
    private CircularProgressView progressView;

    public final static int SIGN_UP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initialize();
        listeners();
    }

    private void initialize(){
        enterButton = (Button) findViewById(R.id.buttonEnter);
        usernameEditText = (EditText) findViewById(R.id.editTextEmail);
        passEditText = (EditText) findViewById(R.id.editTextPass);
        passEditTextAgain = (EditText)findViewById(R.id.editTextPassAgain);
        progressView = (CircularProgressView) findViewById(R.id.progress_view);
        FirebaseAuth.getInstance().signOut();

        ((ContactApplication) this.getApplication()).setMyUser(new User());
        myUser = ((ContactApplication) this.getApplication()).getMyUser();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && myUser != null && myUser.getmNormalizedEmail() != null) {
                    // User is signed in
                    Log.d("Auth Listener", "onAuthStateChanged:signed_in:" + user.getUid());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    myRef.child("users").child(myUser.getmNormalizedEmail()).setValue(myUser);

                    progressView.stopAnimation();
                    progressView.setVisibility(View.GONE);

                    Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivityForResult(searchIntent, 0);


                } else {
                    // User is signed out
                    Log.d("Auth Listener", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    private void listeners(){
        // Listener to signup
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasEmptyFields() && passwordMatches()) {
                    // Add signup to database
                    myUser.setUsername(usernameEditText.getText().toString());
                    myUser.setPassword(passEditText.getText().toString());
                    progressView.startAnimation();
                    progressView.setVisibility(View.VISIBLE);
                    signUp();


                }
            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signUp() {
        Log.e("Signup function", "Before mAuth");
        mAuth.createUserWithEmailAndPassword(myUser.getUsername(), passEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignupActivity.this, "Signed up: " + task.isSuccessful(),
                                Toast.LENGTH_SHORT).show();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Sign up unsuccessful. Make sure your password is of adequate length and complexity.", Toast.LENGTH_SHORT).show();
                            progressView.stopAnimation();
                            progressView.setVisibility(View.GONE);
                        }

                    }
                });
    }


    private boolean hasEmptyFields(){
        if(usernameEditText.getText().toString().isEmpty() || passEditText.getText().toString().isEmpty()){
            progressView.stopAnimation();
            progressView.setVisibility(View.GONE);
            Debug.printToast("Please fill out all input fields!", getApplicationContext());
            return true;
        } else {
            return false;
        }
    }

    private boolean passwordMatches(){
        if(passEditText.getText().toString().equals(passEditTextAgain.getText().toString())){
            // they match
            return true;
        } else {
            progressView.stopAnimation();
            progressView.setVisibility(View.GONE);
            Debug.printToast("Passwords do not match", getApplicationContext());
            passEditText.setText("");
            passEditTextAgain.setText("");
            return false;
        }
    }

}
