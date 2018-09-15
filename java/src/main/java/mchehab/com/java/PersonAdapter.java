package mchehab.com.java;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mchehab.com.java.click_listeners.OnDeleteListener;
import mchehab.com.java.click_listeners.OnEditListener;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Person> listPerson;
    private OnDeleteListener deleteListener;
    private OnEditListener editListener;

    public PersonAdapter(List<Person> listPerson, OnDeleteListener deleteListener, OnEditListener
            editListener){
        this.listPerson = listPerson;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_person, parent,
                false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        PersonViewHolder personViewHolder = (PersonViewHolder)holder;
        personViewHolder.bindData(listPerson.get(position));
        personViewHolder.getView().setOnLongClickListener(e -> {
            deleteListener.deleteItem(listPerson.get(position));
            return true;
        });
        personViewHolder.getView().setOnClickListener(e -> {
            editListener.editItem(listPerson.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return listPerson.size();
    }
}