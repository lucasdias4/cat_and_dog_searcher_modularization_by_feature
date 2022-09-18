package com.lucasdias.android_core.extension

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.lucasdias.android_core.R

fun Spinner.setUp(context: Context, options: List<String>, onItemSelectedCallback: (String) -> Unit) {
    val adapter = ArrayAdapter(
        context,
        R.layout.collapsed_spinner_item,
        options
    )
    adapter.setDropDownViewResource(R.layout.expanded_spinner_item)
    this.adapter = adapter
    this.onItemSelectedListener = createListener(onItemSelectedCallback)
}

private fun createListener(onItemSelectedCallback: (String) -> Unit): AdapterView.OnItemSelectedListener {
    return object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {}

        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View?,
            position: Int,
            id: Long
        ) {
            onItemSelectedCallback(parent.getItemAtPosition(position) as String)
        }
    }
}
