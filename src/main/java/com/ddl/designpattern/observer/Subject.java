package com.ddl.designpattern.observer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    protected List<Observer> list = Lists.newArrayList();

    public void registerObserver(Observer obs) {
        list.add(obs);
    }

    public void removeObserver(Observer obs) {
        list.add(obs);
    }

    //通知所有的观察者更新状态
    public void notifyAllObservers() {
        for (Observer obs : list) {
            obs.update(this);
        }
    }

}
