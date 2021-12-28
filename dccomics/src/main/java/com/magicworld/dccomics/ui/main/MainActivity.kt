package com.magicworld.dccomics.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.magicworld.dccomics.R
import com.magicworld.dccomics.ui.login.LoginFragment
import com.magicworld.dccomics.ui.preference.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_HelloWorld)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val fm:FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        return when (item.itemId){
            R.id.menu_preferencias ->{
                val fragmentSettings = SettingsFragment()
                ft.replace(R.id.fragmentContainerView,fragmentSettings).commit()
                ft.addToBackStack(null)
                true
            }
            android.R.id.home ->{
                onBackPressed()
                true
            }
            R.id.menu_sign_out ->{
                val auth:FirebaseAuth = Firebase.auth
                auth.signOut()
                val fragmentLogin = LoginFragment()
                ft.replace(R.id.fragmentContainerView,fragmentLogin).commit()
                true
            }
            else -> {return true}
        }
    }

    fun showIcon(){
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun hideIcon(){
        val actionBar : androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }
}