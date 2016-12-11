package itp341.wang.cherrie.contact.model;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cherrie on 11/28/16.
 */

public class User {
    private String username;
    private String mNormalizedEmail;
    private String password;
    private HashMap<String, Senator> mSavedSenators;
    private HashMap<String, ArrayList<Senator>> mSavedFormatted;
    private ArrayList<Senator> list;

    // CONSTRUCTOR
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getmNormalizedEmail() {
        return mNormalizedEmail;
    }

    public HashMap<String, ArrayList<Senator>> getmSavedFormatted() {
        if (mSavedFormatted == null) {
            mSavedFormatted = new HashMap<>();
        }

        mSavedFormatted.put("results", list);

        return mSavedFormatted;
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
        if(list == null){
            list = new ArrayList<Senator>();
        }
        mSavedSenators.put(s.getName(), s);
        Log.e("USER","adding senator to list: "+s.getName());
        list.add(s);
    }

    public void setUsername(String username) {
        this.username = username;
        this.mNormalizedEmail = username.replace(".", "%2E");
    }

    public ArrayList<Senator> getList() {
        return list;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
