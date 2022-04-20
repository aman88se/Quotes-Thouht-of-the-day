package comsemsols.thoughtoftheday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
    get() = findViewById(R.id.quoteText)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getCurrentQuote())



    }

    private fun setQuote(quoteData: QuoteData){
        quoteText.text = quoteData.text
        quoteAuthor.text = quoteData.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.prevQuote())

    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())

    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getCurrentQuote().text)
        startActivity(intent)
    }
}