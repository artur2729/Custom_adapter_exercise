package lt.arturas.customadapterexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListView = findViewById(R.id.itemListView)

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
    }

    private fun generateListOfItems(items: MutableList<Employee>) {
        for (item in 1..10){
            items.add(
                Employee(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }
    }

}