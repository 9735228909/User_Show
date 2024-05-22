package com.example.usershow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usershow.ModelClass.UserData
import com.example.usershow.ModelClass.UserDataItem
import com.example.usershow.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableLiveData<List<UserDataItem>>()
    val users: LiveData<List<UserDataItem>> get() = _users

    private val _filteredUsers = MutableLiveData<List<UserDataItem>>()
    val filteredUsers: LiveData<List<UserDataItem>> get() = _filteredUsers

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchUsers() {
        _loading.value = true
        repository.getUsers().enqueue(object : Callback<UserData> {
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                _loading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()
                    _filteredUsers.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                _loading.value = false
            }
        })
    }

    fun filterUsers(query: String) {
        _filteredUsers.value = if (query.isEmpty()) {
            _users.value
        } else {
            _users.value?.filter { it.username.contains(query, true) }
        }
    }
}
