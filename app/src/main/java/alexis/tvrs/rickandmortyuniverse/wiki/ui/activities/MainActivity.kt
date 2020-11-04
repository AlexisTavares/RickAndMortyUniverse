package alexis.tvrs.rickandmortyuniverse.wiki.ui.activities

import alexis.tvrs.rickandmortyuniverse.R
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_episodes,
                R.id.navigation_characters,
                R.id.navigation_locations,
                R.id.navigation_characters)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setOnTouchListener(View.OnTouchListener(::onDrag))
    }

    private fun onDrag(v : View,dEvent : MotionEvent) : Boolean{
        when(dEvent.action){
            MotionEvent.ACTION_DOWN -> {
                Toast.makeText(this@MainActivity,"SwipeDown",Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }
}