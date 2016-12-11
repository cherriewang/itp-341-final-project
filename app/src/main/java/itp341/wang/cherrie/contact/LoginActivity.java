package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import itp341.wang.cherrie.contact.model.User;
import itp341.wang.cherrie.contact.utils.ContactApplication;
import itp341.wang.cherrie.contact.utils.Debug;

public class LoginActivity extends AppCompatActivity {

    private Button enterButton;
    private EditText usernameEditText;
    private EditText passEditText;
    private FirebaseAuth mAuth;
    private User myUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CircularProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        initialize();
        listeners();
    }

    private void initialize(){
        enterButton = (Button) findViewById(R.id.buttonEnter);
        usernameEditText = (EditText) findViewById(R.id.editTextEmail);
        passEditText = (EditText) findViewById(R.id.editTextPass);
        progressView = (CircularProgressView) findViewById(R.id.progress_view);
        FirebaseAuth.getInstance().signOut();
    }
    private void listeners(){
        // Firebase checkin
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Debug.printToast("Are we here?", getApplicationContext());
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    FirebaseDatabase.getInstance().getReference().child("users").child(user.getEmail().replace(".", "%2E")).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user information
                            myUser = dataSnapshot.getValue(User.class);
                            ((ContactApplication) getApplication()).setMyUser(myUser);
                            Intent savedIntent = new Intent(getApplicationContext(), SavedActivity.class);
                            startActivityForResult(savedIntent, 0);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                    progressView.stopAnimation();
                    progressView.setVisibility(View.GONE);

                } else {
                    // User is signed out
                    // Commented this out because it seems to randomly toast in the beginning
                    // Debug.printToast("Signed out", getApplicationContext());
                }
            }
        };

        // Listener to login
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!hasEmptyFields()) {
                    // Sign in

                    progressView.startAnimation();
                    progressView.setVisibility(View.VISIBLE);
                    signIn();

                } else{
                    // print debug toast
                    Debug.printToast("Please fill out all input fields!", getApplicationContext());
                    progressView.stopAnimation();
                    progressView.setVisibility(View.GONE);
                }

            }
        });
    }

    private void signIn() {
        String email = usernameEditText.getText().toString();
        String password = passEditText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Debug.printToast("Signing in", getApplicationContext());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Debug.printToast("Sign in failed", getApplicationContext());
                    passEditText.setText("");
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
            return true;
        } else {
            return false;
        }
    }
}
