package com.martin.codestar.main;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.codestar.R;

import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.label_user_one)
    TextInputLayout mLabelUserOne;
    @BindView(R.id.input_user_one)
    TextInputEditText mEditUserOne;
    @BindView(R.id.label_user_two)
    TextInputLayout mLabelUserTwo;
    @BindView(R.id.input_user_two)
    TextInputEditText mEditUserTwo;

    @BindString(R.string.error_required_field)
    String errorRequiredField;
    @BindString(R.string.user_one)
    String userOneField;
    @BindString(R.string.user_two)
    String userTwoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String getUserOne() {
        this.mLabelUserOne.setError(null);
        this.mLabelUserOne.setErrorEnabled(false);
        return this.mEditUserOne.getText().toString().trim();
    }

    @Override
    public void showUserOneNullError() {
        String error = String.format(Locale.ENGLISH, errorRequiredField, userOneField);
        this.mLabelUserOne.setError(error);
        this.mLabelUserOne.setErrorEnabled(true);
        this.mLabelUserOne.requestFocus();
    }

    @Override
    public String getUserTwo() {
        this.mLabelUserTwo.setError(null);
        this.mLabelUserTwo.setErrorEnabled(false);
        return this.mEditUserTwo.getText().toString().trim();
    }

    @Override
    public void showUserTwoNullError() {
        String error = String.format(Locale.ENGLISH, errorRequiredField, userTwoField);
        this.mLabelUserTwo.setError(error);
        this.mLabelUserTwo.setErrorEnabled(true);
        this.mLabelUserTwo.requestFocus();
    }
}
