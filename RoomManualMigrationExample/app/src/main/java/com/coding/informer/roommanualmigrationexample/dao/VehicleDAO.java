package com.coding.informer.roommanualmigrationexample.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coding.informer.roommanualmigrationexample.models.Vehicle;

import java.util.List;

@Dao
public interface VehicleDAO{

    @Query("SELECT * FROM vehicle")
    List<Vehicle> getAll();

    @Query("SELECT * FROM vehicle WHERE id in (:findVehicleId)")
    Vehicle findById(int findVehicleId);

    @Insert
    void insertAll(Vehicle... vehicles);

    @Delete
    void delete(Vehicle vehicle);
}
