package com.example.android.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "beehive_table")
data class Beehive(
    @PrimaryKey(autoGenerate = true)
    var beehiveId: Long = 0L,

    @ColumnInfo(name = "group_name")
    var groupName: String = "-",

    @ColumnInfo(name = "beehive_name")
    var beehiveName: String = "-",

    @ColumnInfo(name = "queen_bee_age")
    var queenBeeAge: Int = 0,

    @ColumnInfo(name = "queen_bee_year")
    var queenBeeYear: Int = 2020,

    @ColumnInfo(name = "queen_bee_state")
    var queenBeeState: Int = 3,

    @ColumnInfo(name = "beehive_population")
    var BeehivePopulation: Int = 3,

    @ColumnInfo(name = "last_management")
    var lastManagement: String = "--",

    @ColumnInfo(name = "brood_frame_number")
    var broodFrameNumber: Int = -1,

    @ColumnInfo(name = "brood_frame")
    var broodFrame: Int = 2,

    @ColumnInfo(name = "honey_frame_number")
    var honeyFrameNumber: Int = -1,

    @ColumnInfo(name = "honey_frame")
    var honeyFrame: Int = 2,

    @ColumnInfo(name = "noszema")
    var noszema: Int = 0,

    @ColumnInfo(name = "meszesedes")
    var meszesedes: Int = 0,

    @ColumnInfo(name = "group_id")
    var groupId: Long = 0L
)
