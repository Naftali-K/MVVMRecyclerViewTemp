package com.example.mvvmrecyclerviewtemp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmrecyclerviewtemp.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlacesRepositories {

    private static NicePlacesRepositories instance;
    private List<NicePlace> dataSet = new ArrayList<>();

    private NicePlacesRepositories() {
    }

    public static NicePlacesRepositories getInstance() {
        if (instance == null) {
            instance = new NicePlacesRepositories();
        }

        return instance;
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces() {
        setNicePlaces();

        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces() {
        dataSet.add(new NicePlace("Kyoto", "https://a2.cdn.japantravel.com/photo/234-216159/1440x960!/kyoto-kyoto-prefecture-216159.jpg"));
        dataSet.add(new NicePlace("Nara", "https://cdn.cheapoguides.com/wp-content/uploads/sites/3/2017/10/Deer-and-Gate-1024x600.jpg"));
        dataSet.add(new NicePlace("Hiroshima", "https://media-cdn.tripadvisor.com/media/attractions-splice-spp-720x480/11/ef/37/d8.jpg"));
        dataSet.add(new NicePlace("Kiev", "https://lp-cms-production.imgix.net/image_browser/Kyevo-Pecherska-Lavra-kiev-landmark.jpg"));
        dataSet.add(new NicePlace("Jerusalem", "https://assets.editorial.aetnd.com/uploads/2017/08/jerusalem-gettyimages-1239997543.jpg"));
    }
}
