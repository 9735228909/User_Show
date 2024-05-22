package com.example.usershow.UI

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usershow.ModelClass.OnClickfetch
import com.example.usershow.R
import com.example.usershow.UserAdapter
import com.example.usershow.databinding.ActivityMainBinding
import com.example.usershow.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), OnClickfetch {
    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(ArrayList(), this)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        userViewModel.filteredUsers.observe(this, Observer { users ->
            userAdapter.updateUsers(users)
        })

        userViewModel.fetchUsers()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                userViewModel.filterUsers(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userViewModel.filterUsers(newText ?: "")
                return true
            }
        })
    }

    override fun fetchdata(position: Int) {
        val currentItem = userViewModel.users.value?.get(position)
        if (currentItem != null) {
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("username", currentItem.username)
                putExtra("email", currentItem.email)
                putExtra("phone", currentItem.phone)
                putExtra("address_city", currentItem.address.city)
                putExtra("address_street", currentItem.address.street)
                putExtra("address_suite", currentItem.address.suite)
                putExtra("address_zipcode", currentItem.address.zipcode)
            }
            startActivity(intent)
        }
    }
}
