package com.example.mvvmrecyclerviewtemp.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmrecyclerviewtemp.models.NicePlace;
import com.example.mvvmrecyclerviewtemp.repositories.NicePlacesRepositories;

import java.util.List;

public class MainActivityModelView extends ViewModel {
    private MutableLiveData<List<NicePlace>> nicePlaces;
    private NicePlacesRepositories repositories;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void  init() {
        if (nicePlaces != null) {
            return;
        }

        repositories = NicePlacesRepositories.getInstance();
        nicePlaces = repositories.getNicePlaces();
    }
    public LiveData<List<NicePlace>> getNicePlaces() {
        return nicePlaces;
    }

    public void addNewValue(NicePlace nicePlace) {
        isUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                List<NicePlace> currentPlaces = nicePlaces.getValue();
                currentPlaces.add(nicePlace);
                nicePlaces.setValue(currentPlaces);
                isUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    throw new RuntimeException(e);
                }
                return null;
            }
        }.execute();
    }

    public MutableLiveData<List<NicePlace>> getNicePlacesNew() {
        return nicePlaces;
    }

    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }
}
