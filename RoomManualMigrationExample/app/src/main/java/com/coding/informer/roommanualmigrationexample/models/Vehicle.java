package com.coding.informer.roommanualmigrationexample.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="vehicle")
public class Vehicle {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    @NonNull
    private int id;


    @ColumnInfo(name="name")
    @NonNull
    private String name;

//    @ColumnInfo(name="model")
//    @NonNull
//    private String model;

//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }

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
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
