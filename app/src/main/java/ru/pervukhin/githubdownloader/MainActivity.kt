package ru.pervukhin.githubdownloader

import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervukhin.githubdownloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private var itemSelected = R.id.item_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
        val navigationBar = findViewById<BottomNavigationView>(R.id.navigation_bar)
        navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
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