package com.example.poclocalizeactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.poclocalizeactivity.dialog.LocalizeDialog
import com.ice.restring.Restring



open class BaseActivity : AppCompatActivity(),BaseView {


    override fun reActivity(cls: Class<MainActivity>) {
        Intent(this,cls).apply {
            startActivity(this)
            finish()
            overridePendingTransition(0, 0)
        }
    }

    private var localizeDialog: LocalizeDialog? = null

    init {
        localizeDialog = LocalizeDialog.shared()
    }


    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(Restring.wrapContext(context))
    }

    override fun showLanguageDialog() {
        localizeDialog?.show(supportFragmentManager,"LocalizeDialog")
    }




}