package com.lucasdias.breed.domain

import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.breed.domain.repository.CatRepository
import com.lucasdias.breed.domain.repository.DogRepository
import com.lucasdias.breed.domain.use_case.GetBreedByNameAndAnimalTypeUseCase
import com.lucasdias.breed.util.CoroutinesTestRule
import com.lucasdias.core.state.State
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBreedByNameAndAnimalTypeUseCaseTest {
    private lateinit var getBreedByNameAndAnimalTypeUseCase: GetBreedByNameAndAnimalTypeUseCase
    private val catRepository: CatRepository = mockk()
    private val dogRepository: DogRepository = mockk()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        getBreedByNameAndAnimalTypeUseCase = GetBreedByNameAndAnimalTypeUseCase(
            catRepository,
            dogRepository,
            coroutinesTestRule.testDispatcher
        )
    }

    @Test
    fun `IF app request for a cat breed THEN it will request for cat breeds`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            catRepository.getBreeds(name)
        } returns catSuccessState

        val actual = getBreedByNameAndAnimalTypeUseCase(name, AnimalType.CAT)

        coVerify(exactly = 1) {
            catRepository.getBreeds(name)
        }
        coVerify(exactly = 0) {
            dogRepository.getBreeds(name)
        }
        Assert.assertEquals(catSuccessState, actual)
    }

    @Test
    fun `IF app request for a dog breed THEN it will request for dog breeds`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            dogRepository.getBreeds(name)
        } returns dogSuccessState

        val actual = getBreedByNameAndAnimalTypeUseCase(name, AnimalType.DOG)

        coVerify(exactly = 1) {
            dogRepository.getBreeds(name)
        }
        coVerify(exactly = 0) {
            catRepository.getBreeds(name)
        }
        Assert.assertEquals(dogSuccessState, actual)
    }

    @Test
    fun `IF app request for cat and dog breed THEN it will request for cat and dog breeds`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            dogRepository.getBreeds(name)
        } returns dogSuccessState
        coEvery {
            catRepository.getBreeds(name)
        } returns catSuccessState

        val actual: State.Success<List<Breed>> = getBreedByNameAndAnimalTypeUseCase(name, AnimalType.CAT_AND_DOG) as State.Success

        coVerify(exactly = 1) {
            dogRepository.getBreeds(name)
        }
        coVerify(exactly = 1) {
            catRepository.getBreeds(name)
        }
        Assert.assertEquals(catSuccessState.data.first(), actual.data[0])
        Assert.assertEquals(dogSuccessState.data.first(), actual.data[1])
    }

    private companion object {
        const val name = "name_parameter"
        val catBreed = Breed(
            "CAT_ID",
            "CAT_NAME",
            "CAT_TEMPERAMENT",
            "CAT_IMAGE_URL",
            "CAT_LIFETIME",
            "CAT_WIKIPEDIA_URL",
            "CAT_ENERGY_LEVEL"
        )
        val dogBreed = Breed(
            "DOG_ID",
            "DOG_NAME",
            "DOG_TEMPERAMENT",
            "DOG_IMAGE_URL",
            "DOG_LIFETIME",
            "DOG_WIKIPEDIA_URL",
            "DOG_ENERGY_LEVEL"
        )
        val catSuccessState = State.Success(listOf(catBreed))
        val dogSuccessState = State.Success(listOf(dogBreed))
    }
}
