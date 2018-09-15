package mchehab.com.java;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PersonViewHolder extends RecyclerView.ViewHolder{

    private View view;
    private TextView textViewFirstName;
    private TextView textViewLastName;

    public PersonViewHolder(View view) {
        super(view);
        this.view = view;
        textViewFirstName = view.findViewById(R.id.textViewFirstName);
        textViewLastName = view.findViewById(R.id.textViewLastName);
    }

    public View getView(){
        return  view;
    }

    public void bindData(Person person){
        textViewFirstName.setText(person.getFirstName());
        textViewLastName.setText(person.getLastName());
    }
}