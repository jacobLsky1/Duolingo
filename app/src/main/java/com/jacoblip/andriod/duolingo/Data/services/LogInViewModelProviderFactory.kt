package com.jacoblip.andriod.duolingo.Data.services

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LogInViewModelProviderFactory(
        val repository: Repository,
        val context: Context
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogInViewModel(repository,context) as T
    }
}