package com.example.myappli

import androidx.room.*


@Dao
interface PersonnageDesAventageuxDao {
    @Insert
    fun insert(personnageDesAventageux: PersonnageDesAventageux)

    @Update
    fun update(personnageDesAventageux: PersonnageDesAventageux)

    @Delete
    fun delete(personnageDesAventageux: PersonnageDesAventageux)

    @Query("SELECT * FROM personnage_des_aventageux")
    fun getAll(): List<PersonnageDesAventageux>

    @Query("SELECT count(*) FROM personnage")
    fun count() : Int;
    abstract fun getPersonnagesAvantageux(idPersonnage: Int): Any
}

