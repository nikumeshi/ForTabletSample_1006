package com.nikumeshi_azddi9.fortabletsample_1006

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectListener{

    override fun onItemSelected(alData: AlData){
        (supportFragmentManager.findFragmentById(R.id.AnimeAndCodeFragment) as AnimeAndCodeFragment).show(alData)
        drawerLayout?.closeDrawer(GravityCompat.START)
    }

    private var actBarDrawerToggle: ActionBarDrawerToggle? = null

    private fun setupDrawer(drawerLayout: DrawerLayout){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name).apply {
            isDrawerIndicatorEnabled = true
        }
        drawerLayout.addDrawerListener(toggle)
        actBarDrawerToggle = toggle

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setViews(){
        setContentView(R.layout.activity_main)
        val drawerLayout = drawerLayout
        drawerLayout?.let { setupDrawer(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actBarDrawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actBarDrawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        if (actBarDrawerToggle?.onOptionsItemSelected(item) == true) true else super.onOptionsItemSelected(item)
}
