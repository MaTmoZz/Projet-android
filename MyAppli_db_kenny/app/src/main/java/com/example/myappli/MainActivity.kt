package com.example.myappli

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var personnageList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        // Initialisation de l'objet "personnageList"
        personnageList = findViewById(R.id.personnage_list)

        // Configuration de l'objet "personnageList" avec un "layout manager"
        personnageList.layoutManager = LinearLayoutManager(this)

        // Configuration de l'objet "personnageList" avec une "animation"
        personnageList.itemAnimator = DefaultItemAnimator()

        dbInit(this.applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun dbInit (context: Context) {

        // Initialise la base de données et récupère un objet de type BaseDeDonneesApp
        val db = BaseDeDonneesApp.getInstance(context)

        // Remplit la base de données avec des données initiales si elle est vide
        db.populateInitialData()

        // Récupère un objet de type PersonnageDao qui permet d'accéder aux données de la table "personnage"
        val personnageDao = db.personnageDao()

        // Récupère tous les personnages de la table "personnage"
        val personnages = personnageDao.getAll()

        // Affiche la liste de personnages dans les logs de l'application
        Log.d("Nom_personage",personnages.toString())

        // Si la liste n'est pas nulle ou vide, parcourt chaque personnage et affiche son nom dans les logs de l'application
        if (personnages != null) {
            personnages.forEach { personnage ->
                Log.d("Nom_personage",personnage.toString())
            }
        }
    }
}
