package itp341.wang.cherrie.contact.model;

import java.util.HashMap;

/**
 * Created by Cherrie on 11/28/16.
 */

public class User {
    private String username;
    private String mNormalizedEmail;
    private String password;
    private HashMap<String, Senator> mSavedSenators;


    public String getUsername() {
        return username;
    }

    public String getmNormalizedEmail() {
        return mNormalizedEmail;
    }


    public HashMap<String, Senator> getmSavedSenators() {
        return mSavedSenators;
    }

    public void setmSavedSenators(HashMap<String, Senator> mSavedSenators) {
        this.mSavedSenators = mSavedSenators;
    }

    public void appendSenator(Senator s) {
        if (mSavedSenators == null) {
            mSavedSenators = new HashMap<>();
        }

        mSavedSenators.put(s.getName(), s);
    }

    public void setUsername(String username) {
        this.username = username;
        this.mNormalizedEmail = username.replace(".", "%2E");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
