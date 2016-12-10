package itp341.wang.cherrie.contact;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Cherrie on 11/29/16.
 */

public class Debug {

    public Debug(){}

    public static void printToast(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
