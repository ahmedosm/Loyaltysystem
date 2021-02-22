package com.osm.Loyaltysystem.business;


public interface BusinessService<I, O> {

    O doAction(I req);


    O execute(I request);


    O run(I request);
}
