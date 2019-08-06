package com.example.poclocalizeactivity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.example.poclocalizeactivity.dialog.LocalizeDialog
import com.ice.restring.Restring
import org.json.JSONObject




class MainActivity : BaseActivity(),LocalizeDialog.DialogOnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btnPage2.setOnClickListener {
            Intent(this,Page2Activity::class.java).apply {
                startActivity(this)
            }
        }
    }


    private fun setLocale(code : String) {
        val fileName = "lang-$code.json"
        val jsonAddress = assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        val inputJSON = JSONObject(jsonAddress)
        val preferencesJSON = inputJSON.getJSONObject(code)
        val keysIterator = preferencesJSON.keys()
        while (keysIterator.hasNext()) {
            val keyStr = keysIterator.next() as String
            val valueStr = preferencesJSON.getString(keyStr)
            Restring.setString(code,keyStr,valueStr)
        }
        val locale = Locale(code)
        Locale.setDefault(locale)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent) // start same activity
        finish() // destroy older activity
        overridePendingTransition(0, 0) // this is important for seamless transition
    }

    override fun onSelectLanguage(code: String) {
        setLocale(code)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.btnSelectLanguage -> {
                showLanguageDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
