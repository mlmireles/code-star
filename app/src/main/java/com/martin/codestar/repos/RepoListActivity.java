package com.martin.codestar.repos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toolbar;

import com.martin.codestar.R;
import com.martin.codestar.main.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListActivity extends AppCompatActivity {

    private static String sTabUserOne = "User one";
    private static String sTabUserTwo = "User two";

    public static final String PARAM_USER_ONE = "user one";
    public static final String PARAM_USER_TWO = "use two";

    @BindView(R.id.tab_host)
    TabHost mTabHost;
    @BindView(R.id.list_user_one)
    ListView mListUserOne;
    @BindView(R.id.list_user_two)
    ListView mListUserTwo;
    @BindView(R.id.progress_list)
    ProgressBar mProgressBar;

    ReposAdapter mReposAdapterOne, mReposAdapterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        setActionBar(toolbar);

        ButterKnife.bind(this);

        sTabUserOne = this.getIntent().getStringExtra(PARAM_USER_ONE);
        sTabUserTwo = this.getIntent().getStringExtra(PARAM_USER_TWO);
        this.initTabOptions();
        this.initReposAdapter();
    }

    private void initTabOptions() {
        this.mTabHost.setup();
        TabHost.TabSpec spec = this.mTabHost.newTabSpec(sTabUserOne);
        spec.setContent(R.id.tab_user_one);
        spec.setIndicator(sTabUserOne);
        this.mTabHost.addTab(spec);

        spec = this.mTabHost.newTabSpec(sTabUserTwo);
        spec.setContent(R.id.tab_user_two);
        spec.setIndicator(sTabUserTwo);
        this.mTabHost.addTab(spec);

        this.mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

            }
        });
    }

    private void initReposAdapter() {
        this.mReposAdapterOne = new ReposAdapter(this, MainPresenter.mReposOne);
        this.mListUserOne.setAdapter(this.mReposAdapterOne);

        this.mReposAdapterTwo = new ReposAdapter(this, MainPresenter.mReposTwo);
        this.mListUserTwo.setAdapter(this.mReposAdapterTwo);
    }

}
