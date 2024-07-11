package com.example.mvvmrecyclerviewtemp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmrecyclerviewtemp.adapters.RecyclerViewAdapter;
import com.example.mvvmrecyclerviewtemp.interfaces.DataCallback;
import com.example.mvvmrecyclerviewtemp.models.NicePlace;
import com.example.mvvmrecyclerviewtemp.viewmodels.MainActivityModelView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * https://youtu.be/ijXjCtCXcN4?si=Ej29MFqq_KKCU-EG - this project
 * https://youtu.be/UEXZQId3hIg?si=BtCpJT8ab1KaDmMC - more temp
 *
 * PS. ViewModelProviders.of(this).get(MainActivityModelView.class); not working anymore.
 *      now need use new version: new ViewModelProvider(this).get(MainActivityModelView.class);
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    private List<NicePlace> nicePlaceList;

    private MainActivityModelView mainActivityModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        mainActivityModelView = new ViewModelProvider(this).get(MainActivityModelView.class);
        //For fragment: mainActivityModelView = new ViewModelProvider(requireActivity()).get(MainActivityModelView.class);
        mainActivityModelView.init();
        mainActivityModelView.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                if (nicePlaces != null) {
                    nicePlaceList = nicePlaces;
                    adapter.setList(nicePlaces);
                }
            }
        });
        mainActivityModelView.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgressBar();
                } else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityModelView.getNicePlaces().getValue().size()-1);
                }
            }
        });

        initRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainActivityModelView.addNewValue(new NicePlace("Kremenchuk", "https://upload.wikimedia.org/wikipedia/commons/c/c2/Collage_of_Kremenchuk.jpg"));
                openAddDialog();
            }
        });
    }

    private void setReferences() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        fab = findViewById(R.id.fab);
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(getBaseContext(), mainActivityModelView.getNicePlaces().getValue());
        recyclerView.setAdapter(adapter);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void openAddDialog() {
        AddNicePlaceDialog dialog = new AddNicePlaceDialog(new DataCallback<NicePlace>() {
            @Override
            public void setDate(NicePlace data) {
                mainActivityModelView.addNewValue(data);
            }
        });
        dialog.show(getSupportFragmentManager(), AddNicePlaceDialog.DIALOG_TAG);
    }
}