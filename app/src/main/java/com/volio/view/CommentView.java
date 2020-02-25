package com.volio.view;

import com.volio.model.entity3.Datum2;
import com.volio.model.entityPeopleLiked.Datum4;

import java.util.ArrayList;
import java.util.List;

public interface CommentView {

    void displayCmtSuccess(List<Datum2> data2s);

    void displayPeopleLiked(List<Datum4> data);
}
