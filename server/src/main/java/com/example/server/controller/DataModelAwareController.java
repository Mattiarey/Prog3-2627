package com.example.server.controller;

import com.example.server.view.ViewChanger;
import com.example.server.model.DataModel;

public interface DataModelAwareController {
    void setup(DataModel dataModel, ViewChanger viewChanger);
}
