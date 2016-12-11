package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import itp341.wang.cherrie.contact.model.Senator;

public class DetailActivity extends AppCompatActivity {

    private Senator mySenator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        Intent i = getIntent();
        mySenator = (Senator) i.getExtras().getParcelable(ResultsActivity.EXTRA_SENATOR);

    }
}
