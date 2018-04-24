package com.martin.codestar.main;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martin.codestar.R;

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
}
