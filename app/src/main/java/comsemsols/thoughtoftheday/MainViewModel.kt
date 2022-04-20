package comsemsols.thoughtoftheday

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context: Context) : ViewModel() {

    private var thoughtList: Array<QuoteData> = emptyArray()
    private var index = 0

    init {
        thoughtList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<QuoteData> {
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<QuoteData>::class.java)

    }

    fun getCurrentQuote() = thoughtList[index]


    fun nextQuote() = thoughtList[++index]

    fun prevQuote() = thoughtList[--index]



}