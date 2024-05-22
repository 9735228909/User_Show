package com.example.usershow.UI

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.usershow.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username ="Name : " + intent.getStringExtra("username")
        val email ="Email : " + intent.getStringExtra("email")
        val phone ="Phone Number : " + intent.getStringExtra("phone")
        val addressCity ="City : " + intent.getStringExtra("address_city")
        val addressStreet ="Street : " + intent.getStringExtra("address_street")
        val addressSuite ="Suite : " + intent.getStringExtra("address_suite")
        val addressZipcode ="Zipcode : " + intent.getStringExtra("address_zipcode")

        binding.usernameTextView.text = username
        binding.emailTextView.text = email
        binding.phoneTextView.text = phone
        binding.addresTextView.text = "Address : "
        binding.addressTextView.text = "$addressSuite \n  $addressStreet \n  $addressCity \n $addressZipcode"

    }

}