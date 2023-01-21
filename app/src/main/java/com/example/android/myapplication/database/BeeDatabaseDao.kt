package com.example.android.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BeeDatabaseDao {

    @Insert
    suspend fun insertGroup(group: BeeGroup)

    @Update
    suspend fun updateGroup(group: BeeGroup)

    @Insert
    suspend fun insertHive(hive: Beehive)

    @Update
    suspend fun updateHive(hive: Beehive)

    @Query("DELETE FROM bee_group_table")
    suspend fun deleteAllGroups()

    @Query("DELETE FROM bee_group_table WHERE groupId= :key")
    suspend fun deleteGroupWithId(key: Long)

    @Query("DELETE FROM beehive_table WHERE beehiveId= :key")
    suspend fun deleteBeehiveWithId(key: Long)

    @Query("SELECT * FROM bee_group_table WHERE groupId = :key")
    suspend fun getgroup(key: Long): BeeGroup

    @Query("SELECT * FROM beehive_table WHERE beehiveId = :key")
    suspend fun getBeeHive(key: Long): Beehive

    @Query("SELECT * FROM bee_group_table ORDER BY groupId DESC LIMIT 1")
    suspend fun getLastGroup(): BeeGroup?

    @Query("SELECT * FROM beehive_table ORDER BY beehiveId DESC LIMIT 1")
    suspend fun getLastBeehive(): Beehive?

    @Query("SELECT * FROM bee_group_table WHERE groupId = :key")
    fun getGroupWithId(key: Long): LiveData<BeeGroup>

    @Query("SELECT * FROM beehive_table WHERE beehiveId = :key")
    fun getBeehiveWithId(key: Long): LiveData<Beehive>

    @Query("SELECT * FROM beehive_table WHERE group_id = :key ORDER BY beehive_name ASC")
    fun getAllBeehiveWithId(key: Long): LiveData<List<Beehive>>

    @Query("SELECT * FROM beehive_table WHERE group_id = :key AND NOT brood_frame=2")
    fun getBadBroodFrameBees(key: Long): LiveData<List<Beehive>>

    @Query("SELECT * FROM beehive_table WHERE group_id = :key AND queen_bee_year < :year OR queen_bee_state<3")
    fun getBadQueenBees(key: Long, year: Long): LiveData<List<Beehive>>

    @Query("SELECT * FROM bee_group_table WHERE NOT group_nev = '' ORDER BY groupId DESC")
    fun getAllGroups(): LiveData<List<BeeGroup>>
}