package com.ssj.housewares.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ssj.housewares.R;
import com.ssj.housewares.service.ForgetService;
import com.ssj.housewares.utils.ValidateImageView;

public class ForgetActivity extends AppCompatActivity {

    public static final String TAG = "ForgetActivity";

    EditText mUserNameEditText;
    EditText mPasswordEditText;
    EditText mConfirmEditText;
    EditText mCodeEditText;
    Button mSubmitButton;
    String code = "****";
    ValidateImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        init();
    }

    private void init() {
        mUserNameEditText = findViewById(R.id.editText_forget_username);
        mPasswordEditText = findViewById(R.id.editText_forget_password);
        mConfirmEditText = findViewById(R.id.editText_forget_confirm_password);
        mCodeEditText = findViewById(R.id.editText_forget_code);
        mSubmitButton = findViewById(R.id.button_forget);
        mImageView = findViewById(R.id.validateForgetImageView);
        mImageView.setValidateListener(new ValidateImageView.ValidateListener() {
            @Override
            public void onValidate(String validate) {
                code = validate;
            }
        });


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeForget = mCodeEditText.getText().toString();
                String userName = mUserNameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String confirm = mConfirmEditText.getText().toString();
                Toast toast = Toast.makeText(ForgetActivity.this, null, Toast.LENGTH_SHORT);
                if (codeForget.isEmpty()) {
                    toast.setText("验证码不可为空");
                    toast.show();
                } else if (!codeForget.equals(code)) {
                    toast.setText("验证码输入错误");
                    toast.show();
                } else if (!confirm.equals(password)) {
                    Log.d(TAG, "onClick: " + confirm);
                    Log.d(TAG, "onClick: " + password);
                    toast.setText("两次密码输入不一样");
                    toast.show();
                } else {
                    ForgetService forgetService = new ForgetService(ForgetActivity.this);
                    if (forgetService.isForget(userName, password)) {
                        toast.setText("密码修改成功");
                        toast.show();
                        finish();
                    } else {
                        toast.setText("用户名不存在");
                        toast.show();
                    }
                }
            }
        });

    }

}
