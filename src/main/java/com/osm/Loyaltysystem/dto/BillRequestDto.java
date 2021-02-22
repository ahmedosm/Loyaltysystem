package com.osm.Loyaltysystem.dto;

import com.osm.Loyaltysystem.entity.BillItem;
import com.osm.Loyaltysystem.entity.Customer;
import java.util.List;
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
public class BillRequestDto {

    Customer customer;
    List<BillItem> items;
}
