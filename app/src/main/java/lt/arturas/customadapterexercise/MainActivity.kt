package lt.arturas.customadapterexercise

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView
    lateinit var openSecondActivityButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListView = findViewById(R.id.itemListView)
        openSecondActivityButton = findViewById(R.id.button)

        val employees = mutableListOf<Employee>()
        generateListOfItems(employees)

        adapter = CustomAdapter(this)
        adapter.add(employees)
        adapter.add(Employee(101, "text01","text02", "text03"))
        adapter.add(
            Employee(102, "text01","text02", "text03"),
            Employee(103, "text01","text02", "text03"),
            Employee(104, "text01","text02", "text03"),
            Employee(105, "text01","text02", "text03")
        )



        itemListView.adapter = adapter

        setClickOpenItemDetails()
        setClickOpenSecondActivity()
    }

    private fun generateListOfItems(items: MutableList<Employee>) {
        for (item in 1..10){
            items.add(
                Employee(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item),
                    "Text03_%06x".format(item)
                )
            )
        }
    }
    private fun setClickOpenSecondActivity() {
        openSecondActivityButton.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
            startActivityForResult.launch(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setClickOpenItemDetails() {
        itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Employee = adapterView.getItemAtPosition(position) as Employee

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, item.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, item.firstName)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, item.lastName)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, item.position)
            startActivity(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resul ->
            when(resul.resultCode){
                Activity.RESULT_OK -> {
                    val item = Employee(
                        id = resul.data
                            ?.getIntExtra(SecondActivity.SECOND_ACTIVITY_ITEM_ID, 0) ?: 0 ,
                        firstName = resul.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT01) ?:"",
                        lastName = resul.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT02) ?:"",
                        position = resul.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT03) ?:""
                    )

                    adapter.add(item)
                }
            }
        }


    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "package lt.arturas.customadapterexercise.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "package lt.arturas.customadapterexercise.androidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "package lt.arturas.customadapterexercise.androidtopics_item_text02"
        const val MAIN_ACTIVITY_ITEM_TEXT03 = "package lt.arturas.customadapterexercise.androidtopics_item_text02"
    }

}