package com.bc.notes_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.bc.notes_application.fragments.HelpFragment
import com.bc.notes_application.fragments.HomeFragment
import com.bc.notes_application.fragments.RegisterFragment
import com.bc.notes_application.fragments.StatisticsFragment
import com.bc.notes_application.services.SchoolService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    val initFragment: HomeFragment = HomeFragment.newInstance(
        "hola",
        "hola2"
    )
    private val onNavlinkSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navlink_home -> {
                toolbar.title = "Inicio"
                openFragment(initFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navlink_registry -> {
                toolbar.title = "Registro"
                val registerFragment: RegisterFragment = RegisterFragment.newInstance(
                    "hola",
                    "hola2"
                )
                openFragment(registerFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navlink_statistics -> {
                toolbar.title = "Estadisticas"
                val statisticsFragment: StatisticsFragment = StatisticsFragment.newInstance(
                    "hola",
                    "hola2"
                )
                openFragment(statisticsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navlink_help -> {
                toolbar.title = "Ayuda"
                val helpFragment: HelpFragment = HelpFragment.newInstance(
                    "hola",
                    "hola2"
                )
                openFragment(helpFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = supportActionBar!!
        val bottomNavbar: BottomNavigationView = findViewById(R.id.navbar)
        bottomNavbar.setOnNavigationItemSelectedListener(onNavlinkSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        openFragment(initFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}