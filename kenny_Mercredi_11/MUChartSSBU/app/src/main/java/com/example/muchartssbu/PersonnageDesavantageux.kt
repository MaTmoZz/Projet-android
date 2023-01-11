package com.example.muchartssbu

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "personnagedesavantageux")
data class PersonnageDesavantageux (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val personnageId: Int,
    val desavantageuxId: Int
)