package com.ssj.housewares.login;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ssj.housewares.use.NavigationActivity;
import com.ssj.housewares.R;
import com.ssj.housewares.service.LoginService;
import com.ssj.housewares.utils.DatabaseHelper;
import com.ssj.housewares.utils.ValidateImageView;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private final int PERMS_REQUEST_CODE = 200;
    ValidateImageView mCodeImageView;
    EditText mLoginEditText;
    EditText mPasswordEditText;
    EditText mCodeEditText;
    Button mLoginButton;
    TextView mRegisterTextView;
    TextView mForgetTextView;
    String code = "****";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = new String[]{Manifest.permission.
                WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions, PERMS_REQUEST_CODE);
        }
        init();
    }

    private void init() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();
        mLoginEditText = findViewById(R.id.editText_login_username);
        mPasswordEditText = findViewById(R.id.editText_login_password);
        mCodeEditText = findViewById(R.id.editText_login_code);
        mCodeImageView = findViewById(R.id.validateImageView);
        mCodeImageView.setValidateListener(new ValidateImageView.ValidateListener() {
            @Override
            public void onValidate(String validate) {
                code = validate;
            }
        });
        mLoginButton = findViewById(R.id.button_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                finish();
//                LoginService loginService = new LoginService(MainActivity.this);
//                String loginText = mLoginEditText.getText().toString();
//                String passwordText = mPasswordEditText.getText().toString();
//                boolean flag = loginService.isLogin(loginText, passwordText);
//                Toast toast = Toast.makeText(MainActivity.this, null, Toast.LENGTH_SHORT);
//                if (code.equals(mCodeEditText.getText().toString()) && flag) {
////                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
////                    finish();
//                } else if (mCodeEditText.getText().toString().isEmpty()) {
//                    toast.setText("验证码不为空");
//                    toast.show();
//                } else if (!code.equals(mCodeEditText.getText().toString())) {
//                    toast.setText("验证码不匹配");
//                    toast.show();
//                    Toast.makeText(MainActivity.this, null, Toast.LENGTH_SHORT).show();
//                } else {
//                    toast.setText("用户名或密码错误！");
//                    toast.show();
//                }
            }
        });
        mRegisterTextView = findViewById(R.id.register_textView);
        mRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerView = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerView);
            }
        });
        mForgetTextView = findViewById(R.id.forget_password_textView);
        mForgetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetView = new Intent(MainActivity.this, ForgetActivity.class);
                startActivity(forgetView);
            }
        });
    }
}
