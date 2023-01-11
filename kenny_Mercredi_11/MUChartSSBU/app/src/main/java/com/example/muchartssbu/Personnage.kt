package com.example.myappli

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Définit une entité qui représente la table "personnages"
@Entity(tableName = "personnage")
data class Personnage (

    // Définit l'ID comme clé primaire et autorise Room à l'auto-incrémenter

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name ="nom") var nom : String,

    )