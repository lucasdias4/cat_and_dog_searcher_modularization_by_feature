package com.lucasdias.search.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.android_core.extension.animateGoneToVisible
import com.lucasdias.android_core.extension.animateVisibleToGone
import com.lucasdias.android_core.extension.hideKeyBoard
import com.lucasdias.android_core.extension.onImeActionDone
import com.lucasdias.android_core.extension.setDefaultState
import com.lucasdias.android_core.extension.setErrorState
import com.lucasdias.android_core.extension.setUp
import com.lucasdias.android_core.navigator.Navigator
import com.lucasdias.common_ui_model.UIAnimalType
import com.lucasdias.common_ui_model.UIAnimalType.*
import com.lucasdias.search.view.databinding.ActivitySearchBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModel<com.lucasdias.search.presentation.view_model.SearchViewModel>()
    private val navigator by inject<Navigator>()
    private lateinit var binding: ActivitySearchBinding
    private lateinit var animalType: UIAnimalType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSpinner()
        setUpInputText()
        setUpSearchButton()
    }

    private fun setUpSpinner() {
        val options = listOf(CAT.description, DOG.description, CAT_AND_DOG.description)
        binding.animalTypeSpinner.setUp(this, options) { selectedOptionText ->
            animalType = viewModel.getAnimalTypeByDescription(description = selectedOptionText)
        }
    }

    private fun setUpInputText() = with(binding) {
        searchTextInputEditText.onImeActionDone { searchButton.performClick() }
    }

    private fun setUpSearchButton() = with(binding) {
        searchButton.setOnClickListener {
            val searchText = searchTextInputEditText.text.toString()
            if (searchText.isNotEmpty()) {
                initiateSearch(searchText, animalType)
            } else {
                showSearchErrorState()
            }
        }
    }

    private fun initiateSearch(searchText: String, animalType: UIAnimalType) = with(binding) {
        hideKeyBoard()
        searchTextInputLayout.setDefaultState()
        emptySearchMessage.animateVisibleToGone()
        navigator.navigateToBreedList(searchText, animalType.name, this@SearchActivity)
    }

    private fun showSearchErrorState() = with(binding) {
        searchTextInputLayout.setErrorState()
        emptySearchMessage.animateGoneToVisible()
    }
}
