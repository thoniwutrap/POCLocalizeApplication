package com.example.poclocalizeactivity

interface BaseView {

    fun showLanguageDialog()

    fun reActivity(cls: Class<MainActivity>)
}