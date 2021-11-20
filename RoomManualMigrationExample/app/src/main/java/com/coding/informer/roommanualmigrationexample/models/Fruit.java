package com.coding.informer.roommanualmigrationexample.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="fruit")
public class Fruit {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    @NonNull
    private int id;


    @ColumnInfo(name="name")
    @NonNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
