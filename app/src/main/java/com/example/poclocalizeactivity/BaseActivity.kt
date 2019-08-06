package com.example.poclocalizeactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.poclocalizeactivity.dialog.LocalizeDialog
import com.ice.restring.Restring



open class BaseActivity : AppCompatActivity(),BaseView {

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