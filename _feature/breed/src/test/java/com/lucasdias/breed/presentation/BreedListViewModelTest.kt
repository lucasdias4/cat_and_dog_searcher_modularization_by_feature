package com.lucasdias.breed.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.lucasdias.android_core.ui_state.UIState
import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.breed.domain.use_case.GetBreedByNameAndAnimalTypeUseCase
import com.lucasdias.breed.presentation.view_model.BreedListViewModel
import com.lucasdias.breed.presentation.view_model.model.UIBreed
import com.lucasdias.breed.util.CoroutinesTestRule
import com.lucasdias.common_ui_model.UIAnimalType
import com.lucasdias.core.state.State
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class BreedListViewModelTest {
    private val _requestLiveData: MutableLiveData<UIState<List<UIBreed>>> = mockk()
    private val getBreedByNameAndAnimalTypeUseCase: GetBreedByNameAndAnimalTypeUseCase = mockk()
    private lateinit var breedListViewModel: BreedListViewModel

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        breedListViewModel = BreedListViewModel(getBreedByNameAndAnimalTypeUseCase).also {
            it._requestLiveData = _requestLiveData
        }
    }

    @Test
    fun `IF the app still waiting for a breed search call and the screen is empty THEN do not make another call`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                getBreedByNameAndAnimalTypeUseCase(name, domainAnimalType)
            } returns successState
            coEvery {
                breedListViewModel._requestLiveData.value
            } returns UIState.Loading()
            coEvery {
                breedListViewModel._requestLiveData.value = any()
            } just Runs

            breedListViewModel.getBreeds(name, uiAnimalType, true)

            coVerify(exactly = 0) {
                getBreedByNameAndAnimalTypeUseCase(name, any())
            }
        }

    @Test
    fun `IF the app still waiting for a breed search call and the screen is not empty THEN do not make another call`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                getBreedByNameAndAnimalTypeUseCase(name, domainAnimalType)
            } returns successState
            coEvery {
                breedListViewModel._requestLiveData.value
            } returns UIState.Loading()
            coEvery {
                breedListViewModel._requestLiveData.value = any()
            } just Runs

            breedListViewModel.getBreeds(name, uiAnimalType, false)

            coVerify(exactly = 0) {
                getBreedByNameAndAnimalTypeUseCase(name, any())
            }
        }

    @Test
    fun `IF the app is not waiting for a breed search call and the screen is not empty THEN do not make another call`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                getBreedByNameAndAnimalTypeUseCase(name, domainAnimalType)
            } returns successState
            coEvery {
                breedListViewModel._requestLiveData.value
            } returns null
            coEvery {
                breedListViewModel._requestLiveData.value = any()
            } just Runs

            breedListViewModel.getBreeds(name, uiAnimalType, false)

            coVerify(exactly = 0) {
                getBreedByNameAndAnimalTypeUseCase(name, any())
            }
        }

    @Test
    fun `IF the app is not waiting for a breed search call and the screen is not empty  THEN set loading state, make a call and once this call return a success, set success state`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                getBreedByNameAndAnimalTypeUseCase(name, domainAnimalType)
            } returns successState
            coEvery {
                breedListViewModel._requestLiveData.value
            } returns null
            coEvery {
                breedListViewModel._requestLiveData.value = any()
            } just Runs

            breedListViewModel.getBreeds(name, uiAnimalType, false)

            coVerify(exactly = 0) {
                getBreedByNameAndAnimalTypeUseCase(name, any())
            }
        }

    @Test
    fun `IF the app is not waiting for a breed search call and the screen is empty  THEN set loading state, make a call and once this call return a success, set success state`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                getBreedByNameAndAnimalTypeUseCase(name, domainAnimalType)
            } returns successState
            coEvery {
                breedListViewModel._requestLiveData.value
            } returns null
            coEvery {
                breedListViewModel._requestLiveData.value = any()
            } just Runs

            breedListViewModel.getBreeds(name, uiAnimalType, true)

            coVerify {
                breedListViewModel._requestLiveData.value = any<UIState.Loading<List<UIBreed>>>()
            }

            coVerify {
                breedListViewModel._requestLiveData.value = any<UIState.Success<List<UIBreed>>>()
            }

            coVerify(exactly = 1) {
                getBreedByNameAndAnimalTypeUseCase(name, any())
            }
        }

    private companion object {
        const val name = "name_parameter"
        val domainAnimalType = AnimalType.CAT_AND_DOG
        val uiAnimalType = UIAnimalType.CAT_AND_DOG
        val breed = Breed(
            "ID",
            "NAME",
            "TEMPERAMENT",
            "IMAGE_URL",
            "LIFETIME",
            "WIKIPEDIA_URL",
            "ENERGY_LEVEL"
        )
        val successState = State.Success(listOf(breed))
    }
}
