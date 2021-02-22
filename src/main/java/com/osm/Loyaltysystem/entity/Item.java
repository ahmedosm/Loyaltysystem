package com.osm.Loyaltysystem.entity;

import com.osm.Loyaltysystem.enums.ItemType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE )
public class Item {
    String type;
    String name;
    String description;
    double price;
    Long id;



}
