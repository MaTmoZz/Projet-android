package com.example.myappli

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "personnage_des_aventageux")
data class PersonnageDesAventageux(
    @ColumnInfo(name = "id_personnage")
    var idPersonnage: Int,
    @ColumnInfo(name = "id_contre")
    var idContre: Int

)
