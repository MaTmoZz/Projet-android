package com.example.myappli

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Définit la base de données qui contient la table "personnages"
@Database(entities = [Personnage::class], version = 1)
abstract class BaseDeDonneesApp : RoomDatabase() {
    // Définit l'objet DAO pour la table "personnages"
    abstract fun personnageDao(): PersonnageDao

    private val LISTEPERSO: Array<String> = arrayOf ( "Fox", "DresseurDePokemon","ken")

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */


companion object {

        private lateinit var sInstance : BaseDeDonneesApp ;
        fun getInstance(context: Context): BaseDeDonneesApp? {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDonneesApp::class.java,
                    "DataBase_Smash"
                )
                    .build()
                sInstance.populateInitialData()
            }
            return sInstance
        }
    }

    private fun populateInitialData(){
        if (personnageDao().count() == 0 ){
            runInTransaction(Runnable {
                val person = Personnage(0,"")
                for ( i in LISTEPERSO.indices){
                    person.nom = LISTEPERSO[i]
                    personnageDao().insert(person)

                }
            })
        }
    }
}