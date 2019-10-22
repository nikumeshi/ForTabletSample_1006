package com.nikumeshi_azddi9.fortabletsample_1006

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.ClassCastException

class ListFragment : Fragment() {

    companion object{
        private const val PREF_NAME = "pref"
        private const val PREF_KEY = "key"
    }

    interface OnItemSelectListener {
        fun onItemSelected(alData: AlData)
    }

    private lateinit var onItemSelectListener: OnItemSelectListener

    private fun getAlgo(): List<AlData>{
        //TODO sharedPrefに保存したやつからアルゴリズムのデータを取ってくる
        //TODO 永続化したリソース群をList<AlData>として拾ってくる？
        return  listOf<AlData>()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectListener) {
           onItemSelectListener = context
        } else {
            throw ClassCastException("$context must implement OnItemSelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        algoList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val alList: List<AlData> = getAlgo()
        show(alList)
    }

    fun show(alList: List<AlData>){
        algoList.adapter = AlgoAdapter(alList){item ->
            onItemSelectListener.onItemSelected(item)
        }
    }

}
