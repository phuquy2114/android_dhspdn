package com.bignerdranch.android.photogallery

import android.content.Context
import android.content.SharedPreferences

private const val PREF_SEARCH_QUERY = "searchQuery"
private const val PREF_APP = "PHOTO_PQ"

object QueryPreferences {

    fun initPreference(context: Context, name: String): SharedPreferences
    = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.searchKey
        get() = getString(PREF_SEARCH_QUERY, "")
        set(value) {
            editMe {
                it.putString(PREF_SEARCH_QUERY, value)
            }
        }

}

