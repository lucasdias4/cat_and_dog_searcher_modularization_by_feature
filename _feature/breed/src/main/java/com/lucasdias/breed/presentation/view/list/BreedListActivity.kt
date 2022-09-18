package com.lucasdias.breed.presentation.view.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdias.android_core.extension.gone
import com.lucasdias.android_core.extension.visible
import com.lucasdias.android_core.ui_state.observeState
import com.lucasdias.breed.presentation.view.databinding.ActivityBreedListBinding
import com.lucasdias.breed.presentation.view.detail.navigateToBreedDetailActivity
import com.lucasdias.breed.presentation.view_model.BreedListViewModel
import com.lucasdias.breed.presentation.view_model.model.UIBreed
import com.lucasdias.common_model.UIAnimalType
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val BREED_NAME_KEY = "BREED_NAME_KEY"
const val ANIMAL_TYPE_KEY = "ANIMAL_TYPE_KEY"

class BreedListActivity : AppCompatActivity() {

    private val viewModel by viewModel<BreedListViewModel>()
    private val adapter by inject<BreedListAdapter> {
        parametersOf({ breed: UIBreed -> navigateToBreedDetail(breed) })
    }
    private lateinit var binding: ActivityBreedListBinding
    private val animalTypeName: String by lazy { intent?.getStringExtra(ANIMAL_TYPE_KEY).orEmpty() }
    private val breedName: String by lazy { intent?.getStringExtra(BREED_NAME_KEY).orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpObservers()
        setUpErrorView()
        setUpEmptyView()
    }

    override fun onResume() {
        super.onResume()
        getBreeds()
    }

    private fun getBreeds() {
        viewModel.getBreeds(
            breedName,
            animalType = UIAnimalType.valueOf(animalTypeName),
            isScreenEmpty = adapter.currentList.isEmpty()
        )
    }

    private fun setUpRecyclerView() = with(binding.recyclerView) {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager = linearLayoutManager
        adapter = this@BreedListActivity.adapter
    }

    private fun setUpObservers() {
        viewModel.requestLiveData.observeState(
            this,
            success = { osRequestSuccess(it) },
            empty = { osRequestEmpty() },
            error = { osRequestError() },
            loading = { osRequestLoading() }
        )
    }

    private fun setUpErrorView() = with(binding.errorView) {
        button.setOnClickListener {
            progressBar.visible()
            getBreeds()
        }
    }

    private fun setUpEmptyView() = with(binding.emptyView) {
        button.setOnClickListener {
            finish()
        }
    }

    private fun osRequestSuccess(list: List<UIBreed>) = with(binding) {
        val sortedList = list.sortedBy { it.name }
        adapter.update(sortedList)
        recyclerView.visible()
        loadingView.root.gone()
        emptyView.root.gone()
        errorView.root.gone()
    }

    private fun osRequestEmpty() = with(binding) {
        recyclerView.gone()
        loadingView.root.gone()
        emptyView.root.visible()
        errorView.root.gone()
    }

    private fun osRequestLoading() = with(binding) {
        recyclerView.gone()
        loadingView.root.visible()
        if (emptyView.root.isVisible.not()) {
            loadingView.root.visible()
        } else {
            errorView.progressBar.visible()
        }
    }

    private fun osRequestError() = with(binding) {
        recyclerView.gone()
        loadingView.root.gone()
        emptyView.root.gone()
        errorView.progressBar.gone()
        errorView.root.visible()
    }

    private fun navigateToBreedDetail(breed: UIBreed) {
        navigateToBreedDetailActivity(this, breed)
    }
}
