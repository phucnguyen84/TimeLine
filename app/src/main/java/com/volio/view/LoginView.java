package com.volio.view;

import com.volio.model.entity2.Datum;
import com.volio.model.entity3.Datum2;

import java.util.ArrayList;
import java.util.List;

public interface LoginView {
    void displayLoginSuccess(List<Datum> data);

    void displayLoginFailure(String message);
}
