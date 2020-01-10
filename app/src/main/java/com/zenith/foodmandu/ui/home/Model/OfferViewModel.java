package com.zenith.foodmandu.ui.home.Model;

import java.util.ArrayList;
import java.util.List;

public class OfferViewModel {
    static List<OfferViewModel> offerList = new ArrayList<>();
    private int image;

    public static List<OfferViewModel> getOfferList() {
        return offerList;
    }

    public static void setOfferList(List<OfferViewModel> offerList) {
        OfferViewModel.offerList = offerList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public OfferViewModel(int image) {
        this.image = image;
    }
}
