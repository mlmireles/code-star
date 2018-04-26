package com.martin.codestar.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.codestar.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfile extends AppCompatActivity {

    public static final String PARAM_AVATAR = "avatar";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_BIO = "bio";
    public static final String PARAM_COMPANY = "COMPANY";
    public static final String PARAM_LOCATION = "location";
    public static final String PARAM_BLOG = "blog";
    public static final String PARAM_REPOS = "repos";
    public static final String PARAM_FOLLOWERS = "followers";
    public static final String PARAM_FOLLOWING = "following";

    @BindView(R.id.profile_avatar)
    ImageView mAvatar;
    @BindView(R.id.profile_name)
    TextView mName;
    @BindView(R.id.profile_email)
    TextView mEmail;
    @BindView(R.id.profile_bio)
    TextView mBio;
    @BindView(R.id.profile_company)
    TextView mCompany;
    @BindView(R.id.profile_location)
    TextView mLocation;
    @BindView(R.id.profile_blog)
    TextView mBlog;
    @BindView(R.id.profile_repos)
    TextView mRepos;
    @BindView(R.id.profile_followers)
    TextView mFollowers;
    @BindView(R.id.profile_following)
    TextView mFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Picasso.with(this)
                .load(intent.getStringExtra(PARAM_AVATAR))
                .error(R.drawable.ic_person_black)
                .into(this.mAvatar);
        this.mName.setText(intent.getStringExtra(PARAM_NAME));
        this.mEmail.setText(intent.getStringExtra(PARAM_EMAIL));
        this.mBio.setText(intent.getStringExtra(PARAM_BIO));
        this.mCompany.setText(intent.getStringExtra(PARAM_COMPANY));
        this.mLocation.setText(intent.getStringExtra(PARAM_LOCATION));
        this.mBlog.setText(intent.getStringExtra(PARAM_BLOG));
        this.mRepos.setText(intent.getStringExtra(PARAM_REPOS));
        this.mFollowers.setText(intent.getStringExtra(PARAM_FOLLOWERS));
        this.mFollowing.setText(intent.getStringExtra(PARAM_FOLLOWING));
    }

}
