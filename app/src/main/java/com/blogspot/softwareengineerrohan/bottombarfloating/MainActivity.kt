package com.blogspot.softwareengineerrohan.bottombarfloating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blogspot.softwareengineerrohan.bottombarfloating.botton_nav_fragments.CartFragment
import com.blogspot.softwareengineerrohan.bottombarfloating.botton_nav_fragments.HomeFragment
import com.blogspot.softwareengineerrohan.bottombarfloating.botton_nav_fragments.ProfileFragment
import com.blogspot.softwareengineerrohan.bottombarfloating.botton_nav_fragments.SetingFragment
import com.blogspot.softwareengineerrohan.bottombarfloating.databinding.ActivityMainBinding
import com.blogspot.softwareengineerrohan.bottombarfloating.drawer_nav_fragments.primeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        replaceFragments(HomeFragment())


        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.floatingbutton.setOnClickListener {
            Toast.makeText(this,"floating Working",Toast.LENGTH_SHORT).show()
        }

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.botom_home -> replaceFragments(HomeFragment())
                R.id.bottom_profile -> replaceFragments(ProfileFragment())
                R.id.bottom_cart -> replaceFragments(CartFragment())
                R.id.bottom_setting -> replaceFragments(SetingFragment())

            }
            true
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun replaceFragments(fragment: Fragment) {

        fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.drawer_prime -> replaceFragments(primeFragment())
            R.id.drawer_life -> Toast.makeText(this,"life",Toast.LENGTH_SHORT).show()
            R.id.drawer_photo -> Toast.makeText(this,"photo",Toast.LENGTH_SHORT).show()
            R.id.drawer_video -> Toast.makeText(this,"video",Toast.LENGTH_SHORT).show()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
}