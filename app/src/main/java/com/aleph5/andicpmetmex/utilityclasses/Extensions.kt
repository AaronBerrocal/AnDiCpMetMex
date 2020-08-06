package com.aleph5.andicpmetmex.utilityclasses

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.aleph5.andicpmetmex.adapters.AreaArrayAdapter
import com.aleph5.andicpmetmex.adapters.PlantArrayAdapter
import com.aleph5.andicpmetmex.entities.AreaEntity
import com.aleph5.andicpmetmex.entities.PlantEntity

fun View.expandCollapse(){
    this.visibility = if(this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ArrayAdapter<String>.setData(newDataList: List<String>){
    this.clear()
    this.addAll(newDataList)
    this.notifyDataSetChanged()
}

//fun PlantArrayAdapter.setPlantData(newPlants: List<PlantEntity>){
//    this.clear()
//    this.addAll(newPlants)
//    this.notifyDataSetChanged()
//}
//
//fun AreaArrayAdapter.setAreaData(newAreas: List<AreaEntity>){
//    this.clear()
//    this.addAll(newAreas)
//    this.notifyDataSetChanged()
//}

fun AutoCompleteTextView.setOnClickDropDown(){
    this.setOnClickListener {
        this.showDropDown()
    }
}