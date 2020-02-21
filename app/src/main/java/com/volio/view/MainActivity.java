package com.volio.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private String code, user, password;
    DataEntered dataEntered;
    List<Datum> DisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        initPresenter();
        code = edtCode.getText().toString();
        user = edtUser.getText().toString();
        password = edtPassword.getText().toString();
        dataEntered = new DataEntered(user, password, code, "");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.loadData(dataEntered);
            }
        });
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    private void addControls() {
        edtCode = findViewById(R.id.edtCode);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void displayLoginSuccess(List<Datum> datas) {
        Intent intent = new Intent(MainActivity.this, LoginSuccess.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) datas);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void displayLoginFailure(String message) {

    }
}
