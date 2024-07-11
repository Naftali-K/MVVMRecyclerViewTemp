package com.example.mvvmrecyclerviewtemp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mvvmrecyclerviewtemp.interfaces.DataCallback;
import com.example.mvvmrecyclerviewtemp.models.NicePlace;

public class AddNicePlaceDialog extends DialogFragment {

    public static final String DIALOG_TAG = "AddNicePlaceDialog";

    private EditText placeNameEditText, urlEditText;
    private Button addBtn;
    private DataCallback<NicePlace> callback;

    public AddNicePlaceDialog(DataCallback<NicePlace> callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_nice_place, null);
        setReferences(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareData();
            }
        });

        return builder.create();
    }

    private void setReferences(View view) {
        placeNameEditText = view.findViewById(R.id.place_name_edit_text);
        urlEditText = view.findViewById(R.id.url_edit_text);
        addBtn = view.findViewById(R.id.add_btn);
    }

    private void prepareData() {
        String nicePalace = placeNameEditText.getText().toString();
        String url = urlEditText.getText().toString();

        NicePlace nicePlace = new NicePlace(nicePalace, url);

        callback.setDate(nicePlace);
        dismiss();
    }
}
