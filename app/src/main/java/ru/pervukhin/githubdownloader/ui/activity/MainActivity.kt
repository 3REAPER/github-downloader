package ru.pervukhin.githubdownloader.ui.activity

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.pervukhin.githubdownloader.R

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
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
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

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context
            .getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
            return false
        }
        val info = connectivity.allNetworkInfo
        if (info != null) {
            for (i in info.indices) {
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    override fun onBackPressed() {

    }
}