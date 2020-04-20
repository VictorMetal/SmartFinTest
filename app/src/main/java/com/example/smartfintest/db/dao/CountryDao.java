package com.example.smartfintest.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.smartfintest.model.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    LiveData<List<Country>> getCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCountries(List<Country> countries);
}
