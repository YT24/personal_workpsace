package com.example.aserver.desginPattern.builder_pattern;

public class Director {
    //指挥装机人员组装电脑
    public void Construct(Builder builder) {

        builder.BuildCPU();
        builder.BuildMainboard();
        builder.BuildHD();
    }
}