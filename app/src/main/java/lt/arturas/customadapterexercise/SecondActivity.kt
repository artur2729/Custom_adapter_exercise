package lt.arturas.customadapterexercise

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var text01EditText: EditText
    lateinit var text02EditText: EditText
    lateinit var text03EditText: EditText
    lateinit var closeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        idEditText = findViewById(R.id.idEditText)
        text01EditText = findViewById(R.id.text01EditText)
        text02EditText = findViewById(R.id.text02EditText)
        text03EditText = findViewById(R.id.text03EditText)
        closeButton = findViewById(R.id.closeButton)

        /*
        if (savedInstanceState != null){
             with(savedInstanceState){
                 idEditText.setText(getString(SECOND_ACTIVITY_ITEM_ID))
                 text01EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT01))
                 text02EditText.setText(getString(SECOND_ACTIVITY_ITEM_TEXT02))
             }
         }else{
             getIntentExtra()
         }
         */

        getIntentExtra()
        setClickListenerOfCloseButton()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putString(SECOND_ACTIVITY_ITEM_ID, idEditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            putString(SECOND_ACTIVITY_ITEM_TEXT03, text03EditText.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState){
            idEditText.setText(this.getString(SECOND_ACTIVITY_ITEM_ID))
            text01EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT01))
            text02EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT02))
            text03EditText.setText(this.getString(SECOND_ACTIVITY_ITEM_TEXT03))
        }
    }

    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_ID, -1).toString()
        )
        text01EditText.setText(
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT01)
        )
        text02EditText.setText(
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT02)
        )
        text03EditText.setText(
            intent.getStringExtra(MainActivity.MAIN_ACTIVITY_ITEM_TEXT03)
        )
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT01, text01EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT02, text02EditText.text.toString())
            finishIntent.putExtra(SECOND_ACTIVITY_ITEM_TEXT03, text03EditText.text.toString())
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    companion object{
        const val   SECOND_ACTIVITY_ITEM_ID = "lt.arturas.customadapterexercise.secondactivity_item_id"
        const val   SECOND_ACTIVITY_ITEM_TEXT01 = "lt.arturas.customadapterexercise.secondactivity_item_text01"
        const val   SECOND_ACTIVITY_ITEM_TEXT02 = "lt.arturas.customadapterexercise.secondactivity_item_text02"
        const val   SECOND_ACTIVITY_ITEM_TEXT03 = "lt.arturas.customadapterexercise.secondactivity_item_text03"
    }
}