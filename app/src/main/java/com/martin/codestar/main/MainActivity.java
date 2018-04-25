package com.martin.codestar.main;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martin.codestar.API.models.User;
import com.martin.codestar.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.label_info)
    TextView mInfo;
    @BindView(R.id.label_user_one)
    TextInputLayout mLabelUserOne;
    @BindView(R.id.input_user_one)
    TextInputEditText mEditUserOne;
    @BindView(R.id.label_user_two)
    TextInputLayout mLabelUserTwo;
    @BindView(R.id.input_user_two)
    TextInputEditText mEditUserTwo;
    @BindView(R.id.button_start)
    Button mButtonStart;
    @BindView(R.id.progress_main)
    ProgressBar mProgressBar;

    @BindView(R.id.user_one_info)
    RelativeLayout mUserOneInfo;
    @BindView(R.id.avatar_user_one)
    ImageView mUserOneAvatar;
    @BindView(R.id.label_main_user_one_name)
    TextView mUserOneName;
    @BindView(R.id.label_main_user_one_company)
    TextView mUserOneCompany;
    @BindView(R.id.user_two_info)
    RelativeLayout mUserTwoInfo;
    @BindView(R.id.avatar_user_two)
    ImageView mUserTwoAvatar;
    @BindView(R.id.label_main_user_two_name)
    TextView mUserTwoName;
    @BindView(R.id.label_main_user_two_company)
    TextView mUserTwoCompany;

    @BindString(R.string.user_one)
    String userOneField;
    @BindString(R.string.user_two)
    String userTwoField;
    @BindString(R.string.error_required_field)
    String errorRequiredField;
    @BindString(R.string.same_user)
    String errorSameUser;
    @BindString(R.string.user_not_found)
    String errorUserNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String getEditInput(TextInputLayout input, TextInputEditText edit) {
        input.setError(null);
        input.setErrorEnabled(false);
        return edit.getText().toString().trim();
    }

    private void setTextInputLayoutError(String error, TextInputLayout input) {
        input.setError(error);
        input.setErrorEnabled(true);
        input.requestFocus();
    }

    @Override
    public String getUserOne() {
        return this.getEditInput(this.mLabelUserOne, this.mEditUserOne);
    }

    @Override
    public void showUserOneNullError() {
        String error = String.format(Locale.ENGLISH, errorRequiredField, userOneField);
        this.setTextInputLayoutError(error, this.mLabelUserOne);
    }

    @Override
    public String getUserTwo() {
        return this.getEditInput(this.mLabelUserTwo, this.mEditUserTwo);
    }

    @Override
    public void showUserTwoNullError() {
        String error = String.format(Locale.ENGLISH, errorRequiredField, userTwoField);
        this.setTextInputLayoutError(error, this.mLabelUserTwo);
    }

    @Override
    public void showSameUserError() {
        String error = String.format(Locale.ENGLISH, errorSameUser, userTwoField);
        this.setTextInputLayoutError(error, this.mLabelUserTwo);
    }

    @Override
    public void showProgressBar() {
        this.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        this.mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showServerError() {
        this.mInfo.setVisibility(View.VISIBLE);
        this.mInfo.setText(R.string.error_server);
        this.mInfo.setTextColor(Color.RED);
    }

    @Override
    public void showUserNotFoundError(String username) {
        if (username.equals(this.getUserOne())) {
            String error = String.format(Locale.ENGLISH, errorUserNotFound, userOneField);
            this.setTextInputLayoutError(error, this.mLabelUserOne);
        } else {
            String error = String.format(Locale.ENGLISH, errorUserNotFound, userTwoField);
            this.setTextInputLayoutError(error, this.mLabelUserTwo);
        }
    }

    @Override
    public void onUsersSuccess() {
        this.mInfo.setVisibility(View.GONE);
        this.mButtonStart.setVisibility(View.GONE);
        this.removeTextInputLayoutError(this.mLabelUserOne);
        this.removeTextInputLayoutError(this.mLabelUserTwo);
    }

    private void removeTextInputLayoutError(TextInputLayout input) {
        input.setError(null);
        input.setErrorEnabled(false);
    }

    @Override
    public void showUsersInfo(User userOne, User userTwo) {
        this.showUser(userOne, this.mEditUserOne, this.mUserOneInfo, this.mUserOneAvatar,
                this.mUserOneName, this.mUserOneCompany);
        this.showUser(userTwo, this.mEditUserTwo, this.mUserTwoInfo, this.mUserTwoAvatar,
                this.mUserTwoName, this.mUserTwoCompany);
    }

    private void showUser(User user, TextInputEditText input, RelativeLayout layout,
                          ImageView avatar, TextView name, TextView company) {
        input.setEnabled(false);
        name.setText(user.getName());
        company.setText(user.getCompany());
        Picasso.with(this)
                .load(user.getAvatar_url())
                .error(R.drawable.ic_person_black)
                .into(avatar);

        layout.setVisibility(View.VISIBLE);
    }
}
