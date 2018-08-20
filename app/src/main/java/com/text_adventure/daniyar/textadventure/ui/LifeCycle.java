package com.text_adventure.daniyar.textadventure.ui;

public interface LifeCycle<V> {

    void bind(V view);

    void unbind();
}
