package mchehab.com.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonEdit;
    private Button buttonCancel;

    private Person person;
    private int index = -1;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setupViews();
        initPerson();
        setButtonCancelListener();
        setButtonEditListener();
    }

    private void setupViews(){
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonCancel = findViewById(R.id.buttonCancel);
    }

    private void initPerson() {
        Intent intent = getIntent();
        if(intent != null){
            isEdit = intent.getBooleanExtra(Constants.PERSON_INTENT_EDIT, false);
            if(isEdit){
                person = intent.getParcelableExtra(Constants.PERSON_INTENT_OBJECT);
                index = intent.getIntExtra(Constants.PERSON_INTENT_INDEX, -1);
                if(index == -1){
                    setResult(RESULT_CANCELED);
                    finish();
                }
                editTextFirstName.setText(person.getFirstName());
                editTextLastName.setText(person.getLastName());
                buttonEdit.setText(getString(R.string.button_edit));
            }else{
                person = new Person();
                buttonEdit.setText(getString(R.string.button_add));
            }
        }
    }

    private void setButtonCancelListener(){
        buttonCancel.setOnClickListener(e -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private void setButtonEditListener(){
        buttonEdit.setOnClickListener(e -> {
            String firstName = editTextFirstName.getText().toString().trim();
            String lastName = editTextLastName.getText().toString().trim();
            person.setFirstName(firstName);
            person.setLastName(lastName);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.PERSON_INTENT_OBJECT, person);
            bundle.putBoolean(Constants.PERSON_INTENT_EDIT, isEdit);
            bundle.putInt(Constants.PERSON_INTENT_INDEX, index);
            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
