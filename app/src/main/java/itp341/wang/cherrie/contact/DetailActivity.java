package itp341.wang.cherrie.contact;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import itp341.wang.cherrie.contact.async.GetSenResponse;
import itp341.wang.cherrie.contact.model.Senator;
import itp341.wang.cherrie.contact.model.User;
import itp341.wang.cherrie.contact.tab_fragments.OneFragment;
import itp341.wang.cherrie.contact.tab_fragments.ThreeFragment;
import itp341.wang.cherrie.contact.tab_fragments.TwoFragment;
import itp341.wang.cherrie.contact.utils.ContactApplication;
import itp341.wang.cherrie.contact.utils.Debug;

public class DetailActivity extends AppCompatActivity {

    private Senator mySenator;
    private User myUser;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button saveButton;
    private Boolean hideFavoritesButton;
    private TextView senatorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        hideFavoritesButton = i.getBooleanExtra(SavedActivity.EXTRA_HIDE, false);
        if(hideFavoritesButton){
            mySenator = (Senator) i.getExtras().getParcelable(SavedActivity.EXTRA_SEN);
        } else {
            mySenator = (Senator) i.getExtras().getParcelable(ResultsActivity.EXTRA_SENATOR);
        }

        myUser = ((ContactApplication) this.getApplication()).getMyUser();
        this.setTitle(mySenator.getName());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = (Button) findViewById(R.id.buttonSave);
        senatorName = (TextView) findViewById(R.id.senName);
        senatorName.bringToFront();
        senatorName.setText(mySenator.getName().toString());

        if(hideFavoritesButton){
            saveButton.setVisibility(View.INVISIBLE);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        listeners();
    }

    private void listeners(){
        // Listener for when you want to search
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mySenator.getName();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                //myRef.child("users").child(myUser.getmNormalizedEmail()).child("mSenators").child(name).setValue(mySenator);
                myUser.appendSenator(mySenator);
                myRef.child("users").child(myUser.getmNormalizedEmail()).setValue(myUser);
                ((ContactApplication) getApplication()).setMyUser(myUser);
                Debug.printToast("You have just saved "+name+" to your Favorites!", getApplicationContext());

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();

        Bundle args = new Bundle();
        args.putParcelable("sen", mySenator);
        oneFragment.setArguments(args);
        twoFragment.setArguments(args);
        threeFragment.setArguments(args);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(oneFragment, "ONE");
        adapter.addFragment(twoFragment, "TWO");
        adapter.addFragment(threeFragment, "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
