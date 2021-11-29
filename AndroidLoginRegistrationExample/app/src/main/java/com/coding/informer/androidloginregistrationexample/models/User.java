package com.coding.informer.androidloginregistrationexample.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="user_id")
    @NonNull
    private int userId;

    @ColumnInfo(name="username")
    @NonNull
    private String username;

    @ColumnInfo(name="email")
    @NonNull
    private String email;

    @ColumnInfo(name="first_name")
    @NonNull
    private String firstName;

    @ColumnInfo(name="last_name")
    @NonNull
    private String lastName;

    @ColumnInfo(name="password")
    @NonNull
    private String password;

    @ColumnInfo(name="password_salt")
    @NonNull
    private String passwordSalt;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(@NonNull String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}