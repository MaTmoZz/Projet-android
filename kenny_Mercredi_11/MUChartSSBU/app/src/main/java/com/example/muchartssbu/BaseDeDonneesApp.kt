package com.example.muchartssbu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myappli.Personnage
import com.example.myappli.PersonnageDao

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS personnagedesavantageux (id INTEGER PRIMARY KEY AUTOINCREMENT, personnageId INTEGER NOT NULL, desavantageuxId INTEGER NOT NULL)")
    }
}

// Définit la base de données qui contient la table "personnages"
@Database(entities = [Personnage::class , PersonnageDesavantageux::class], version = 2, exportSchema = false)

abstract class BaseDeDonneesApp : RoomDatabase() {
    // Définit l'objet DAO pour la table "personnages"
    abstract fun personnageDao(): PersonnageDao
    abstract fun personnagedesavantageuxDao() : PersonnageDesavantageuxDAO

    private val LISTEPERSO: Array<String> = arrayOf("Fox", "DresseurDePokemon", "ken")

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */






    companion object {
        private var sInstance: BaseDeDonneesApp? = null
        fun getInstance(context: Context): BaseDeDonneesApp {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(context.applicationContext, BaseDeDonneesApp::class.java, "DataBase_Smash")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
                //sInstance.populateInitialData()
            }
            return sInstance!!
        }
    }


    val mapDesAvantagesInferieurs: Map<String, List<String>> = mapOf(
        "Fox" to listOf("Bowser", "Wario"),
        "DresseurDePokemon" to listOf("Sheik", "Marth"),
        "ken" to listOf("Fox", "Falco")
    )

    fun populateInitialData() {
        // Vérifie si la table Personnage est vide
        if (personnageDao().count() == 0) {
            // Initialise les listes pour stocker les personnages et les associations
            val personnages = ArrayList<Personnage>()
            val personnageDesavantageux = ArrayList<PersonnageDesavantageux>()

            // Boucle pour ajouter chaque personnage et son association éventuelle
            for (i in 0 until LISTEPERSO.size) {
                val currentPersonnage = LISTEPERSO[i]
                val p = Personnage(i, currentPersonnage)
                // Ajoute le personnage à la liste des personnages
                personnages.add(p)
                // Récupère les associations pour ce personnage
                val disadvantages = mapDesAvantagesInferieurs[currentPersonnage]
                if (disadvantages != null) {
                    for (disadvantage in disadvantages) {
                        val disadvantageId = LISTEPERSO.indexOf(disadvantage)
                        // Ajoute l'association à la liste des associations
                        personnageDesavantageux.add(PersonnageDesavantageux(i, p.id, disadvantageId))
                    }
                }
            }
            // Insère tous les personnages et les associations en utilisant les DAO
            personnageDao().insert(personnages)
            personnagedesavantageuxDao().insertPersonnageDesavantageux(personnageDesavantageux)
        }
    }

}