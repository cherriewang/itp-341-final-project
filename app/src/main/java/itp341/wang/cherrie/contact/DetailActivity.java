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

public class DetailActivity extends AppCompatActivity {

    private Senator mySenator;
    private User myUser;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //getSupportActionBar().hide();

        Intent i = getIntent();
        mySenator = (Senator) i.getExtras().getParcelable(ResultsActivity.EXTRA_SENATOR);
        myUser = ((ContactApplication) this.getApplication()).getMyUser();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveButton = (Button) findViewById(R.id.buttonSave);

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

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
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
