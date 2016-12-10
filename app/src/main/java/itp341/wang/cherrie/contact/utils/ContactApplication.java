package itp341.wang.cherrie.contact.utils;

/**
 * Created by Cherrie on 12/10/16.
 */

import android.app.Application;

import itp341.wang.cherrie.contact.model.User;

public class ContactApplication extends Application {

    public User myUser;

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User mainAct) {
        myUser = mainAct;
    }
}