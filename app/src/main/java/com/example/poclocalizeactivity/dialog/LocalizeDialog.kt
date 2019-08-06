package com.example.poclocalizeactivity.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.poclocalizeactivity.R
import com.example.poclocalizeactivity.adapter.LocalizeAdapter
import com.example.poclocalizeactivity.extension.initVertical
import com.example.poclocalizeactivity.model.LocalizeModel
import kotlinx.android.synthetic.main.dialog_language.view.*

class LocalizeDialog : DialogFragment(),LocalizeAdapter.Listener {

    var adapter : LocalizeAdapter? = null
    var listener: DialogOnClickListener? = null


    interface DialogOnClickListener {
        fun onSelectLanguage(code : String)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_language, container)
        initInstance(view)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,0);
        return view
    }

    companion object {
        fun shared(): LocalizeDialog {
            val dialog = LocalizeDialog()
            val bundle: Bundle = Bundle()
            dialog.arguments = bundle
            dialog.isCancelable = false
            return dialog
        }
    }


    private fun initInstance(view: View) {

        listener = if (parentFragment != null) {
            parentFragment as DialogOnClickListener
        } else {
            activity as DialogOnClickListener
        }
        view.rvLanguage.initVertical()
        adapter = LocalizeAdapter(mockData(),this)
        view.rvLanguage.adapter = adapter
    }


    override fun onItemClick(code: String) {
        listener?.onSelectLanguage(code)
        dialog.dismiss()
    }

    //Mock Data
    fun mockData() : MutableList<LocalizeModel>{
        val localizeList : MutableList<LocalizeModel> = mutableListOf()
        return localizeList.apply {
            add(LocalizeModel(code = "th",name = "Thailand",img = R.drawable.ic_th))
            add(LocalizeModel(code = "en",name = "English",img = R.drawable.ic_en))
            add(LocalizeModel(code = "gm",name = "German",img = R.drawable.ic_gm))
            add(LocalizeModel(code = "fr",name = "France",img = R.drawable.ic_fr))
        }
    }


}