package vi.learning.hellokotlin.data

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
class ApiRepository {

    fun doRequest(url: String) : Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}