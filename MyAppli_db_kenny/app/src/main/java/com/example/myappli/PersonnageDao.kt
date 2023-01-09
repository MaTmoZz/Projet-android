package com.example.myappli

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// je crée un  DAO qui définit les méthodes d'accès à la table "personnages".

@Dao
interface PersonnageDao {

    // Récupère tous les personnages
    @Query("SELECT * FROM personnage")
    fun getAll(): List<Personnage>

    // Insère un nouveau personnage dans la table
    @Insert
    fun insert(personnage: Personnage)


    @Query("SELECT count(*) FROM personnage")
    fun count() : Int;
    abstract fun getPersonnagesById(idPersonnagesAvantageux: Any): Any

}

