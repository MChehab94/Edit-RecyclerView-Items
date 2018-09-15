package mchehab.com.advancedrecyclerview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mchehab.com.advancedrecyclerview.click_listeners.OnDeleteListener
import mchehab.com.advancedrecyclerview.click_listeners.OnEditListener

class MainActivity : AppCompatActivity(), OnDeleteListener, OnEditListener {

    private val REQUEST_CODE = 101

    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listPerson = mutableListOf(Person("f1", "f2"),
                Person("f2", "l2"),
                Person("f3", "l3"),
                Person("F4", "L4"),
                Person("f5", "l5"),
                Person("f6", "l6"),
                Person("F7", "L7"),
                Person("f8", "l8"),
                Person("f9", "l9"),
                Person("F10", "L10"))

        personAdapter = PersonAdapter(listPerson, this, this)
        recyclerView.adapter = personAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        setFloatingActionButtonListener()
    }

    private fun setFloatingActionButtonListener(){
        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra(PERSON_INTENT_EDIT, false)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun deleteItem(person: Person) {
        personAdapter.listPerson.remove(person)
        personAdapter.notifyDataSetChanged()
    }

    override fun editItem(person: Person, index: Int) {
        val intent = Intent(this@MainActivity, EditActivity::class.java)
        intent.putExtra(PERSON_INTENT_EDIT, true)
        intent.putExtra(PERSON_INTENT_OBJECT, person)
        intent.putExtra(PERSON_INTENT_INDEX, index)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK && data != null){
                val bundle = data.extras
                val isEdit = bundle.getBoolean(PERSON_INTENT_EDIT, false)
                val person = bundle.getParcelable<Person>(PERSON_INTENT_OBJECT)
                if(isEdit){
                    val index = bundle.getInt(PERSON_INTENT_INDEX, -1)
                    // something very wrong occurred, do nothing
                    if (index == -1)
                        return
                    personAdapter.listPerson.set(index, person)
                    personAdapter.notifyItemChanged(index)
                }else{
                    personAdapter.listPerson.add(person)
                    personAdapter.notifyItemInserted(personAdapter.listPerson.size-1)
                }
            }
        }
    }
}