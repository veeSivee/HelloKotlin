package vi.learning.hellokotlin.data.footballclubfromdb

import java.net.URL

/**
 * Created by taufiqotulfaidah on 10/30/18.
 */
class ApiRepository {

    fun doRequest(url: String) : String {
        return URL(url).readText()
    }
}