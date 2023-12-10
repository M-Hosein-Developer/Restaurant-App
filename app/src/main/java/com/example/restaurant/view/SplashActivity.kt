package com.example.restaurant.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.restaurant.R
import com.example.restaurant.databinding.ActivitySplash2Binding
import com.example.restaurant.utils.NetworkChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplash2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        splashScreenFun()

    }

    private fun splashScreenFun() {

        if (NetworkChecker(this).internetConnection){

            binding.imageReload.visibility = View.INVISIBLE
            binding.txtRepeat.visibility = View.INVISIBLE
            binding.txtCheck.visibility = View.VISIBLE
            binding.txtCheck.text = "خوش آمدید"

            lifecycleScope.launch{

                delay(4000)
                val intent = Intent(applicationContext , MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }else{

            binding.imageReload.visibility = View.VISIBLE
            binding.txtRepeat.visibility = View.VISIBLE
            binding.txtCheck.visibility = View.VISIBLE
        }

        binding.imageReload.setOnClickListener {
            splashScreenFun()
            binding.imageReload.playAnimation()

        }

    }
}