package com.example.client;

import com.example.client.model.DataModel;

public interface DataModelAwareController {
    void setup(DataModel dataModel, ViewChanger viewChanger);
}
