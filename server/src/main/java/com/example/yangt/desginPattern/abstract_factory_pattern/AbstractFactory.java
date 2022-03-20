package com.example.yangt.desginPattern.abstract_factory_pattern;

public abstract class AbstractFactory {
    public abstract Animal getAnimal(String type);

    public abstract Color getColor(String type);
}
