package alexis.tvrs.rickandmortyuniverse.wiki.ui.activities

import alexis.tvrs.rickandmortyuniverse.R
import alexis.tvrs.rickandmortyuniverse.wiki.data.webservices.firebase.AuthManager
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val drawerNavView: NavigationView = findViewById(R.id.drawer_nav_view)
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfigurationDrawer = AppBarConfiguration(setOf(
                R.id.drawer_account, R.id.drawer_offers), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfigurationDrawer)
        drawerNavView.setupWithNavController(navController)

        val appBarConfigurationBottomNav = AppBarConfiguration.Builder(
            R.id.navigation_marketplace,
            R.id.navigation_characters,
            R.id.navigation_collection,
            R.id.navigation_favorites,
            R.id.drawer_account,
            R.id.drawer_offers)
            .build()

        setupActionBarWithNavController(navController, appBarConfigurationBottomNav)
        bottomNavView.setupWithNavController(navController)

//        drawerNavView.setNavigationItemSelectedListener{
//            when(it.itemId){
//                R.id.drawer_logout -> {
//                    AuthManager.signOut()
//                    startActivity(Intent(this, SplashScreenActivity::class.java))
//                }
//            }
//            true
//        }
    }
}