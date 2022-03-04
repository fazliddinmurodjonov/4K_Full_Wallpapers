package com.model

import android.content.Context
import android.content.SharedPreferences


object MySharedPreference {

    private const val NAME = "Images"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreference: SharedPreferences

    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var allImages: String?
        get() = sharedPreference.getString("allImages", null)
        set(value) = sharedPreference.edit {
            it.putString("allImages", value)
        }
    var animals: String?
        get() = sharedPreference.getString("animals", null)
        set(value) = sharedPreference.edit {
            it.putString("animals", value)
        }
    var new: String?
        get() = sharedPreference.getString("new", null)
        set(value) = sharedPreference.edit {
            it.putString("new", value)
        }

    var technology: String?
        get() = sharedPreference.getString("technology", null)
        set(value) = sharedPreference.edit {
            it.putString("technology", value)
        }
    var nature: String?
        get() = sharedPreference.getString("nature", null)
        set(value) = sharedPreference.edit {
            it.putString("nature", value)
        }
}

