package com.example.muchartssbu

import androidx.room.*

@Dao
interface PersonnageDesavantageuxDAO {
    @Insert
    fun insertPersonnageDesavantageux(personnageDesavantageux: ArrayList<PersonnageDesavantageux>)

    @Query("SELECT * FROM PersonnageDesavantageux WHERE personnageId = :personnageId")
    fun getAllPersonnageDesavantageux(personnageId: Int): List<PersonnageDesavantageux>

    @Query("SELECT * FROM PersonnageDesavantageux WHERE id = :id")
    fun getPersonnageDesavantageuxById(id: Int): PersonnageDesavantageux

    @Update
    fun updatePersonnageDesavantageux(personnageDesavantageux: PersonnageDesavantageux)

    @Delete
    fun deletePersonnageDesavantageux(personnageDesavantageux: PersonnageDesavantageux)
}