package com.moselo.HomingPigeon.Data.RecentSearch;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface HpRecentSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HpRecentSearchEntity... entities);

    @Delete
    void delete(HpRecentSearchEntity entity);

    @Delete
    void delete(List<HpRecentSearchEntity> entities);

    @Query("delete from recent_search")
    void deleteAllRecentSearch();

    @Update
    void update(HpRecentSearchEntity entity);

    @Query("select * from Recent_Search order by Created desc")
    LiveData<List<HpRecentSearchEntity>> getAllRecentSearchLive();
}
