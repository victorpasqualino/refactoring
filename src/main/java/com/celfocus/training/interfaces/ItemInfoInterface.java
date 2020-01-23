package com.celfocus.training.interfaces;

import com.celfocus.training.entity.ItemInfo;

import java.util.List;

public interface ItemInfoInterface {
    void addItemIfNotExists(String itemName, double itemValue);
    ItemInfo getItemInfoFromName(String itemName);
}
