package mchehab.com.java;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mchehab.com.java.click_listeners.OnDeleteListener;
import mchehab.com.java.click_listeners.OnEditListener;

public class MainActivity extends AppCompatActivity implements OnEditListener, OnDeleteListener{

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    private PersonAdapter personAdapter;
    private List<Person> listPerson;

    private final int REQUEST_CODE_EDIT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPerson = new ArrayList<>();
        for(int i=0;i<10;i++){
            listPerson.add(new Person("F" + (i+1), "L" + (i+1)));
        }

        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);

        personAdapter = new PersonAdapter(listPerson, this, this);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
         false));

        setFloatingActionButtonListener();
    }

    private void setFloatingActionButtonListener() {
        floatingActionButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(Constants.PERSON_INTENT_EDIT, false);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        });
    }

    @Override
    public void deleteItem(Person person) {
        listPerson.remove(person);
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public void editItem(Person person, int index) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.PERSON_INTENT_EDIT, true);
        intent.putExtra(Constants.PERSON_INTENT_INDEX, index);
        intent.putExtra(Constants.PERSON_INTENT_OBJECT, person);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDIT){
            if(resultCode == RESULT_OK){
                if(data == null){
                    return;
                }
                boolean isEdit = data.getBooleanExtra(Constants.PERSON_INTENT_EDIT, false);
                Person person = data.getParcelableExtra(Constants.PERSON_INTENT_OBJECT);
                if(isEdit){
                    int index = data.getIntExtra(Constants.PERSON_INTENT_INDEX, -1);
                    if(index == -1){
                        return;
                    }
                    listPerson.set(index, person);
                    personAdapter.notifyDataSetChanged();
                }else{
                    listPerson.add(person);
                    personAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}