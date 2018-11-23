package vi.learning.hellokotlin.data

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by taufiqotulfaidah on 11/22/18.
 */
class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val repository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        repository.doRequest(url)
        verify(repository).doRequest(url)
    }
}