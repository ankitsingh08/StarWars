package ankit.com.starwarepisodes.view.episodes

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ankit.com.starwarepisodes.data.repository.EpisodeRepository
import ankit.com.starwarepisodes.model.Episode
import ankit.com.starwarepisodes.util.UIResponseState
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by AnkitSingh on 11/11/20.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EpisodesViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Mock
    private lateinit var episodeRepository: EpisodeRepository

    @Mock
    private lateinit var episodeListObserver: Observer<List<Episode>>

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EpisodesViewModel

    @Before
    fun setUp() {
        viewModel = EpisodesViewModel(episodeRepository)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun testGetAllEpisodeSuccessScenario() = testCoroutineScope.runBlockingTest {
        val episode1 = Episode("1", "Skywalker", "Luke", 2f, "none", 3)
        val episode2 = Episode("2", "Force Awakens", "Yoda", 3f, "none", 4)
        val episodeList = mutableListOf<Episode>()
        episodeList.add(episode1)
        episodeList.add(episode2)
        whenever(episodeRepository.getAllEpisode()).thenReturn(flowOf(UIResponseState.Success(
            episodeList)))

        viewModel.getAllEpisode()
        viewModel.getEpisodeLiveData().observeForever(episodeListObserver)

        verify(episodeRepository).getAllEpisode()
        verify(episodeListObserver).onChanged(episodeList)
        viewModel.getEpisodeLiveData().removeObserver(episodeListObserver)
    }

    @Test
    fun testGetAllEpisodesErrorScenario() = testCoroutineScope.runBlockingTest {
        val exception = NetworkErrorException()
        whenever(episodeRepository.getAllEpisode()).thenReturn(flowOf(UIResponseState.Error(exception)))

        viewModel.getAllEpisode()
        viewModel.getEpisodeLiveData().observeForever(episodeListObserver)

        verify(episodeRepository).getAllEpisode()

        //fallback to empty list in case of error
        verify(episodeListObserver).onChanged(emptyList())
        viewModel.getEpisodeLiveData().removeObserver(episodeListObserver)
    }
}