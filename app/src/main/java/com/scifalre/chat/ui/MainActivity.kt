package com.scifalre.chat.ui

import android.graphics.Rect
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.scifalre.chat.R
import com.scifalre.chat.data.viewmodel.AppViewModel
import com.scifalre.chat.databinding.ActivityMainBinding
import com.scifalre.chat.utils.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private var currentSelection: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        initUI()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentDestinationId = findNavController()?.currentDestination?.id
                findNavController()?.navigateUp()
            }
        })

    }

    private fun initUI() {
        binding.bottomNav.setOnItemSelectedListener {

            if (currentSelection != it.itemId) when (it.itemId) {
                R.id.sender -> findNavController()?.navigate(R.id.fragment_sender)
                R.id.receiver -> findNavController()?.navigate(R.id.fragment_receiver)
            }
            currentSelection = it.itemId
            return@setOnItemSelectedListener true
        }

    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHostFragment.navController
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            window.decorView.getWindowVisibleDisplayFrame(r)

            val height = window.decorView.height
            binding.bottomNav.isVisible = height - r.bottom <= height * 0.1399
        }

    }
}