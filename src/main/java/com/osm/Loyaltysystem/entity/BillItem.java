package com.osm.Loyaltysystem.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE )
public class BillItem {

    Item item;
    int amount;
    double totalPrice;
    double discount;

    public double getTotalPrice(){
        return amount* item.getPrice();
    }

}
