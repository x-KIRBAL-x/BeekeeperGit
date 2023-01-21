package com.example.android.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bee_group_table")
data class BeeGroup(

    @PrimaryKey(autoGenerate = true)
    var groupId: Long = 0L,

    @ColumnInfo(name = "group_nev")
    var groupNev: String = "",

    @ColumnInfo(name = "group_hely")
    var groupHely: String = "-"
)