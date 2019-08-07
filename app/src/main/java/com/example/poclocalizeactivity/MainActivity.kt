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
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory


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
        extractStringXML(readXml())
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

    override fun onSelectLanguage(code: String) {
        setLocale(code)
    }


    fun setLocale(code : String) {
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
        reActivity(MainActivity::class.java)
    }


    fun readXml(): Document {
        val xmlFile = assets.open("lang-temp.xml").bufferedReader().use {
            it.readText()
        }
        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = InputSource(StringReader(xmlFile))
        val doc = dBuilder.parse(xmlInput)
        return doc
    }


    fun extractStringXML(doc: Document) {
        val xpFactory = XPathFactory.newInstance()
        val xPath = xpFactory.newXPath()
        val xpath = "resources/string"
        val elementNodeList = xPath.evaluate(xpath, doc, XPathConstants.NODESET) as NodeList
        for (i in 0 until elementNodeList.length) {
           val key = elementNodeList.item(i).attributes.item(0).nodeValue
           val value = elementNodeList.item(i).textContent
           Log.e("xml","$key = $value")
        }
    }

}
