package com.celfocus.training.controllers;

import com.celfocus.training.entity.ItemInfo;
import com.celfocus.training.interfaces.ItemInfoInterface;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoController implements ItemInfoInterface {


    private static final List<ItemInfo> itens = new ArrayList<>();

    public static List<ItemInfo> getItens() {
        return itens;
    }

    @Override
    public void addItemIfNotExists(String itemName, double itemValue) {
        ItemInfo item = getItemInfoFromName(itemName);
        if (item == null) {
            ItemInfo newItem = new ItemInfo();
            newItem.itemName = itemName;
            newItem.value = itemValue;
            itens.add(newItem);
        }
    }

    @Override
    public ItemInfo getItemInfoFromName(String itemName) {
        return itens.stream().filter(item -> item.itemName.equals(itemName)).findAny().orElse(null);
    }

}
