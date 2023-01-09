package com.example.myappli

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myappli.PersonnageListAdapter

// Définit la base de données qui contient la table "personnages"
@Database(entities = [Personnage::class, PersonnageDesAventageux::class], version = 1, exportSchema = false)

abstract class BaseDeDonneesApp : RoomDatabase() {
    // Définit l'objet DAO pour la table "personnages"
    abstract fun personnageDao(): PersonnageDao
    abstract  fun personnageDesAventageuxDao(): PersonnageDesAventageuxDao

    abstract val personnageList: Any
    private val LISTEPERSO: Array<String> = arrayOf ( "Fox", "DresseurDePokemon","ken")
    private val LISTE_PERSONNAGE_DES_AVENTAGEUX = listOf(
        Pair(1, 2),
        Pair(1, 3),
        Pair(2, 3),
        Pair(3, 1)
    )

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
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDonneesApp::class.java,
                    "DataBase_Smash"
                ).allowMainThreadQueries()
                    .build()
                //sInstance.populateInitialData()
            }
            return sInstance!!
        }
    }


    fun populateInitialData() {
        runInTransaction(Runnable {
            if (personnageDao().count() == 0) {
                val person = Personnage(0, "")
                for (i in LISTEPERSO.indices) {
                    person.nom = LISTEPERSO[i]
                    personnageDao().insert(person)
                }
            }

            if (personnageDesAventageuxDao().count() == 0) {
                // Vérifie si la table "personnage_des_aventageux" est vide

                val personnageDesAventageux = PersonnageDesAventageux(0, 0)
                // Crée un nouvel objet "PersonnageDesAventageux" avec des ID de personnage initialisés à 0

                for (i in LISTE_PERSONNAGE_DES_AVENTAGEUX.indices) {
                    // Pour chaque élément dans la liste "LISTE_PERSONNAGE_DES_AVENTAGEUX"
                    val (idPersonnage, idContre) = LISTE_PERSONNAGE_DES_AVENTAGEUX[i]
                    // Décompose l'élément en une paire d'ID de personnage (idPersonnage, idContre)

                    personnageDesAventageux.idPersonnage = idPersonnage
                    personnageDesAventageux.idContre = idContre
                    // Affecte les ID de personnage à l'objet "PersonnageDesAventageux"

                    personnageDesAventageuxDao().insert(personnageDesAventageux)
                    // Ajoute l'objet "PersonnageDesAventageux" à la table "personnage_des_aventageux" en utilisant la fonction "insert" de l'objet "PersonnageDesAventageuxDao"
                }
            }

        })
    }



    fun afficherPersonnagesAvantageux(idPersonnage: Int) {
        // Récupère l'objet "PersonnageDao"
        val personnageDao = getInstance(this).personnageDao()

        // Récupère l'objet "PersonnageDesAventageuxDao"
        val personnageDesAventageuxDao = getInstance(this).personnageDesAventageuxDao()

        // Récupère la liste des ID de personnage avantageux pour le personnage donné
        val idPersonnagesAvantageux = personnageDesAventageuxDao.getPersonnagesAvantageux(idPersonnage)

        // Récupère la liste des personnages avantageux à partir de leurs ID
        val personnagesAvantageux = personnageDao.getPersonnagesById(idPersonnagesAvantageux)

        // Affiche la liste des personnages avantageux dans l'élément de liste (par exemple, un RecyclerView)
        personnageList.adapter = PersonnageListAdapter(context, personnagesAvantageux)

    }

}