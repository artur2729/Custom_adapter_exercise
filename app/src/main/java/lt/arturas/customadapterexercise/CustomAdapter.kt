package lt.arturas.customadapterexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import lt.arturas.customadapterexercise.databinding.ItemBinding

class CustomAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    private val list = mutableListOf<Employee>()

    /*
    fun add(item: Item){
        list.add(item)
        notifyDataSetChanged()
    }
     */

    fun add(vararg employee: Employee){
        list.addAll(employee)
        notifyDataSetChanged()
    }

    fun add(employees: List<Employee>){
        list.addAll(employees)
        notifyDataSetChanged()
    }

    fun clear(){
        list.clear()
        notifyDataSetChanged()
    }

    fun remove(vararg employee: Employee){
        list.removeAll(employee)
        notifyDataSetChanged()
    }

    fun remove(employees: List<Employee>){
        list.removeAll(employees)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val view = convertView ?: inflater.inflate(R.layout.item,parent, false)

        var view = convertView
        val binding: ItemBinding
        if(view == null){
            binding = ItemBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        }else{
            binding = view.tag as ItemBinding
        }

        //view.findViewById<TextView>(R.id.idTextView).text = list[position].id.toString()
        //view.findViewById<TextView>(R.id.text01TextView).text = list[position].firstName
        //view.findViewById<TextView>(R.id.text02TextView).text = list[position].lastName
        //view.findViewById<TextView>(R.id.text03TextView).text = list[position].position

        binding.idTextView.text = list[position].id.toString()
        binding.text01TextView.text = list[position].firstName
        binding.text02TextView.text = list[position].lastName
        binding.text03TextView.text = list[position].position

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size
}