package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.volio.model.entity.DataEntered;
import com.volio.model.entity2.Datum;
import com.volio.model.entity3.Datum2;
import com.volio.presenter.MainPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginView {
    private MainPresenter mainPresenter;
    private EditText edtCode, edtUser, edtPassword;
    private Button btnLogin;
    private TextView txtNoti;
    private ProgressBar progressBar;

    private String code, user, password;
    DataEntered dataEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        mainPresenter = new MainPresenter(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = edtCode.getText().toString();
                user = edtUser.getText().toString();
                password = edtPassword.getText().toString();
                txtNoti.setVisibility(View.VISIBLE);
                if (code.isEmpty()) {
                    txtNoti.setText("*Mã khách hàng không được bỏ trống");
                } else if (user.isEmpty()) {
                    txtNoti.setText("*Tài khoản không được bỏ trống");
                } else if (password.isEmpty()) {
                    txtNoti.setText("*Mật khẩu không được bỏ trống");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    dataEntered = new DataEntered(user, password, code, "");
                    mainPresenter.loadData(dataEntered);
                }
                btnLogin.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setEnabled(true);
                    }
                }, 1000);
            }
        });
    }

    private void addControls() {
        edtCode = findViewById(R.id.edtCode);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        txtNoti = findViewById(R.id.txtNoti);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.loadLogin);
    }

    @Override
    public void displayLoginSuccess(List<Datum> datas) {
        Intent intent = new Intent(MainActivity.this, LoginSuccess.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) datas);
        bundle.putString("user", user);
        bundle.putString("pass", password);
        bundle.putString("code", code);
        intent.putExtras(bundle);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    @Override
    public void displayLoginFailure(String message) {
        progressBar.setVisibility(View.GONE);
        txtNoti.setText(message);
    }
}
