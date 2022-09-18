package com.lucasdias.breed.presentation.view.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.android_core.extension.gone
import com.lucasdias.android_core.extension.loadImage
import com.lucasdias.breed.presentation.view.R
import com.lucasdias.breed.presentation.view.databinding.ActivityBreedDetailBinding
import com.lucasdias.breed.presentation.view.databinding.BreedDetailSectionBinding
import com.lucasdias.breed.presentation.view_model.model.UIBreed

private const val BREED_KEY = "BREED_KEY"

fun navigateToBreedDetailActivity(activity: Activity, breed: UIBreed) {
    Intent(activity, BreedDetailActivity::class.java).run {
        Bundle().also {
            it.putParcelable(BREED_KEY, breed)
            putExtras(it)
            activity.startActivity(this)
        }
    }
}

class BreedDetailActivity : AppCompatActivity() {

    private val breed: UIBreed? by lazy { intent?.getParcelableExtra(BREED_KEY) }
    private lateinit var binding: ActivityBreedDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() = with(binding) {
        image.loadImage(breed?.imageUrl)
        setUpSection(nameSection, R.string.name_breed_activity, breed?.name)
        setUpSection(temperamentSection, R.string.temperament_breed_activity, breed?.temperament)
        setUpSection(lifetimeSection, R.string.lifetime_breed_activity, breed?.lifetime)
        setUpSection(energyLevelSection, R.string.energy_level_breed_activity, breed?.energyLevel)
        setUpButton()
    }

    private fun setUpSection(
        section: BreedDetailSectionBinding,
        @StringRes titleRes: Int,
        text: String?
    ) {
        if (text.isNullOrEmpty()) {
            section.layout.gone()
            return
        }
        section.title.text = getText(titleRes)
        section.description.text = text
    }

    private fun setUpButton() = with(binding.wikipediaButton) {
        if (breed?.wikipediaUrl.isNullOrEmpty()) {
            gone()
            return
        }
        setOnClickListener {
            val intent =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            intent.data = Uri.parse(breed?.wikipediaUrl)
            startActivity(intent)
        }
    }
}
