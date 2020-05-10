package com.ssj.housewares.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssj.housewares.R;
import com.ssj.housewares.service.RegisterService;
import com.ssj.housewares.utils.ValidateImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText mUserNameEditText;
    EditText mPasswordEditText;
    EditText mCodeEditText;
    ValidateImageView mCodeImageView;
    String code = "****";
    Button mRegisterButton;
    public static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        setToolBar();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        mUserNameEditText = findViewById(R.id.editText_register_username);
        mPasswordEditText = findViewById(R.id.editText_register_password);
        mCodeEditText = findViewById(R.id.editText_register_code);
        mCodeImageView = findViewById(R.id.validateImageView);
        mCodeImageView.setValidateListener(new ValidateImageView.ValidateListener() {
            @Override
            public void onValidate(String validate) {
                code = validate;
            }
        });
        mRegisterButton = findViewById(R.id.button_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameRegister = mUserNameEditText.getText().toString();
                String passwordRegister = mPasswordEditText.getText().toString();
                String codeRegister = mCodeEditText.getText().toString();
                RegisterService registerService = new RegisterService(RegisterActivity.this);
                Toast toast = Toast.makeText(RegisterActivity.this, null, Toast.LENGTH_SHORT);

                if (codeRegister.isEmpty()) {
                    toast.setText("验证码不可为空");
                    toast.show();
                } else if (!codeRegister.equals(code)) {
                    toast.setText("验证码输入错误");
                    toast.show();
                } else {
                    if (registerService.isRegister(userNameRegister, passwordRegister)) {
                        toast.setText("注册成功");
                        toast.show();
                        finish();
                    } else {
                        toast.setText("用户名不可重复");
                        toast.show();
                    }
                }

            }
        });

    }
}
