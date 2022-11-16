package ru.pervukhin.githubdownloader

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervukhin.githubdownloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationBar = findViewById<BottomNavigationView>(R.id.navigation_bar)
        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        var itemSelected = R.id.item_home
        navigationBar.setOnItemSelectedListener {
            val itemWillSelected = it.itemId
            if (itemSelected != itemWillSelected) {
                when (itemWillSelected) {
                    R.id.item_home -> {
                        navigationController.navigate(R.id.nav_homeFragment)
                        itemSelected = R.id.item_home
                    }
                    R.id.item_download -> {
                        navigationController.navigate(R.id.nav_downloadFragment)
                        itemSelected = R.id.item_download
                    }
                }
            }
            true
            }

        }
    }