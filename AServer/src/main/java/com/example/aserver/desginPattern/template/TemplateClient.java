package com.example.aserver.desginPattern.template;

public class TemplateClient {
    public static void main(String[] args) {
        AbstractClass abstractClass1 = new ConcreteClass1();
        AbstractClass abstractClass2 = new ConcreteClass2();
        applyTemplate(abstractClass1);
        applyTemplate(abstractClass2);
    }

    public static void applyTemplate(AbstractClass abstractClass){
        abstractClass.templateMethod();
    }
}
