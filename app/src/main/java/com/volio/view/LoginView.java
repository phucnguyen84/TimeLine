package com.volio.view;

import com.volio.model.entity2.Datum;
import com.volio.model.entity3.Datum2;

import java.util.ArrayList;

public interface LoginView {
    void displayLoginSuccess(ArrayList<Datum> datas);

    void displayLoginFailure(String message);
}