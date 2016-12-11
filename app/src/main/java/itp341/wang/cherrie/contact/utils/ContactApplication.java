package itp341.wang.cherrie.contact.utils;

/**
 * Created by Cherrie on 12/10/16.
 */

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import itp341.wang.cherrie.contact.model.User;

public class ContactApplication extends Application {

    public User myUser;

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User mainAct) {
        myUser = mainAct;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        myUser = null;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FirebaseAuth.getInstance().signOut();
    }
}