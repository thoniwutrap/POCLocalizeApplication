package com.example.poclocalizeactivity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import javax.xml.xpath.XPathFactory
import java.nio.file.Files.exists



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        methodWithPermissions()
        btnTest.setOnClickListener {
            createFolder()
        }

    }

    fun methodWithPermissions() = runWithPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE) {
        Log.e("Path", Environment.getExternalStorageState().toString())
    }

    fun createFolder(){
        val file = File("src/values", "Records.txt")
        FileOutputStream(file).use {
            it.write("fdsf".toByteArray())
        }
    }

}
