package com.mobillium.vitrinova.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mobillium.vitrinova.R


class MainActivity : AppCompatActivity() {


    companion object{
        lateinit var navController: NavController

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)

    }


}