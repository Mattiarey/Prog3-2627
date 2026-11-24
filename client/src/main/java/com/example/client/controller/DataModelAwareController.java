    package com.example.client.controller;

    import com.example.client.view.ViewChanger;
    import com.example.client.model.DataModel;

        public interface DataModelAwareController {
        void setup(DataModel dataModel, ViewChanger viewChanger);
    }
