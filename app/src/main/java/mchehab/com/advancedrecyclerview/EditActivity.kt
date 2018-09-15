package mchehab.com.advancedrecyclerview

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    var isEdit = false
    var index = -1
    var person = Person()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setupActivity()
    }

    private fun setButtonEditListener(){
        buttonEdit.setOnClickListener {
            person.firstName = editTextFirstName.text.toString()
            person.lastName = editTextLastName.text.toString()

            val intent = Intent()
            val bundle = Bundle()
            bundle.putBoolean(PERSON_INTENT_EDIT, isEdit)
            bundle.putInt(PERSON_INTENT_INDEX, index)
            bundle.putParcelable(PERSON_INTENT_OBJECT, person)
            intent.putExtras(bundle)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun setButtonCancelListener(){
        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun setupActivity(){
        val intent = intent
        isEdit = intent.getBooleanExtra(PERSON_INTENT_EDIT, false)
        if(isEdit){
            index = intent.getIntExtra(PERSON_INTENT_INDEX, -1)
            person = intent.getParcelableExtra(PERSON_INTENT_OBJECT)
            editTextFirstName.setText(person.firstName)
            editTextLastName.setText(person.lastName)
            buttonEdit.text = getString(R.string.button_edit)
        }else{
            buttonEdit.text = getString(R.string.button_add)
        }

        setButtonEditListener()
        setButtonCancelListener()
    }
}
