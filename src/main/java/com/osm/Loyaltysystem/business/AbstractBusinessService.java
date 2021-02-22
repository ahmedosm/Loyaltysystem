package com.osm.Loyaltysystem.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.osm.Loyaltysystem.exception.LoyaltyException;
import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;

@Slf4j
public abstract class AbstractBusinessService<I, O> implements BusinessService<I, O> {

    private static final String SERVICE_RETURNED_RESPONSE = "Service: {} returned response: {}";
    private static final String SERVICE_CALLED_BY_REQUEST = "Service: {} called by request: {}";

    @Override
    public final O doAction(I request) {

        String serviceName = getServiceName();
        long startTime = System.currentTimeMillis();
        logRequestOrResponse( request, SERVICE_CALLED_BY_REQUEST );
        //2. validate request
        validateRequest( request );
        //3. execute service
        try {
            O response = run( request );
            log.info( "Service: {} took : {} Millisecond", serviceName, System.currentTimeMillis() - startTime );
            return response;
        } catch( LoyaltyException exp ) {
            handleFailure( exp, request );
            throw exp;
        }
    }

    @Override
    // override if u want to make transaction on execute and log transaction
    public O run(I request) {
        O response = execute( request );
        logRequestOrResponse( response, SERVICE_RETURNED_RESPONSE );
        //4. log transaction
        logTransaction( request, response );
        return response;
    }

    protected abstract String getServiceName();


    protected abstract void validateRequest(I request);

    protected abstract void logTransaction(I request, O response);

    protected void handleFailure(LoyaltyException exp, I request) {

        log.error( MessageFormat.format( "Error: {0} happened in service {1}", exp.getMessage(), getServiceName() ),
            exp );
    }

    private <T> void logRequestOrResponse(T t, String type) {
        try {
            if( t instanceof ByteArrayResource ) {
                return;
            }
            log.info( type, getServiceName(),
                new ObjectMapper().disable( SerializationFeature.FAIL_ON_EMPTY_BEANS ).writeValueAsString( t ) );
        } catch( Exception ex ) {
            log.error( "Exception in processing log for response::", ex );
        }
    }
}
