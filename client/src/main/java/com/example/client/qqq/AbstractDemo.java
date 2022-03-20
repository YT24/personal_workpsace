package com.example.client.qqq;

public interface AbstractDemo<T extends BaseEntity> {

    <T extends BaseEntity> T query(String id);
}
