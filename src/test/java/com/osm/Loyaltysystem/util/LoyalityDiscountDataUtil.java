package com.osm.Loyaltysystem.util;

import com.osm.Loyaltysystem.entity.BillItem;
import com.osm.Loyaltysystem.entity.Customer;
import com.osm.Loyaltysystem.entity.Item;
import com.osm.Loyaltysystem.enums.ItemType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;

public class LoyalityDiscountDataUtil {


    public static Customer getCustomer(String type, int years) {
        return Customer.builder().customerFullName( "User" ).id( 88866l ).type( type ).registrationDate(
            LocalDateTime.now().minusYears( years ) ).build();
    }
    public static List<BillItem> getMultipleItems() {
        List<BillItem> billItems = Lists.newArrayList( getGroceriesItem() );
        billItems.addAll( getNonGroceriesItem() );
        return billItems;

    }


    public static List<BillItem> getGroceriesItem() {
        List<BillItem> items = new ArrayList<>();
        BillItem billItem = new BillItem();
        billItem.setAmount( 1 );
        Item item = new Item();
        item.setType( ItemType.GROCERIES.getType() );
        item.setPrice( 100 );
        item.setName( "groceries1" );
        item.setDescription( "groceries desc1" );
        billItem.setItem( item );
        items.add( billItem );
        billItem = new BillItem();
        billItem.setAmount( 1 );
        item = new Item();
        item.setType( ItemType.GROCERIES.getType() );
        item.setName( "groceries2" );
        item.setDescription( "groceries desc2" );
        item.setPrice( 200 );
        billItem.setItem( item );
        items.add( billItem );

        return items;
    }


    public static List<BillItem> getNonGroceriesItem() {
        List<BillItem> items = new ArrayList<>();
        BillItem billItem = new BillItem();
        billItem.setAmount( 2 );
        Item item = new Item();
        item.setType( "ELECTRONICS" );
        item.setName( "electronics" );
        item.setDescription( "electronics desc1" );
        item.setPrice( 100 );
        billItem.setItem( item );
        items.add( billItem );
        billItem = new BillItem();
        billItem.setAmount( 1 );
        item = new Item();
        item.setName( "electronics2" );
        item.setDescription( "electronics desc2" );
        item.setType( "ELECTRONICS" );
        item.setPrice( 300 );
        billItem.setItem( item );
        items.add( billItem );

        return items;
    }

    public static BillItem getItem(String type, double price, int amount) {
        BillItem billItem = new BillItem();
        billItem.setAmount( amount );
        Item item = new Item();
        item.setType( type );
        item.setPrice( price );
        billItem.setItem( item );
        return billItem;
    }
}
