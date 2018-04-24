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

    @BindString(R.string.user_one)
    String userOneField;
    @BindString(R.string.user_two)
    String userTwoField;
    @BindString(R.string.error_required_field)
    String errorRequiredField;
    @BindString(R.string.same_user)
    String errorSameUser;

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
}
