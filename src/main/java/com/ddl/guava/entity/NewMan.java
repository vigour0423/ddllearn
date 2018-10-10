package com.ddl.guava.entity;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author dongdongliu
 * @version 1.0
 * 注意：Optional 不能被序列化
 */
public class NewMan implements Serializable {

    private Optional<Godness> godness = Optional.empty();

    private Godness god;

    public Optional<Godness> getGod() {
        return Optional.of(god);
    }

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan [godness=" + godness + "]";
    }

}
