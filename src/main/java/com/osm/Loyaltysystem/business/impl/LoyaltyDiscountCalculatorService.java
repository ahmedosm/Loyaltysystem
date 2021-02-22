package com.osm.Loyaltysystem.business.impl;

import com.osm.Loyaltysystem.business.AbstractBusinessService;
import com.osm.Loyaltysystem.dto.BillRequestDto;
import com.osm.Loyaltysystem.dto.BillResponseDto;
import com.osm.Loyaltysystem.entity.BillItem;
import com.osm.Loyaltysystem.entity.Customer;
import com.osm.Loyaltysystem.enums.ItemType;
import com.osm.Loyaltysystem.enums.UserType;
import com.osm.Loyaltysystem.exception.ErrorCode;
import com.osm.Loyaltysystem.exception.InvalidDataException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class LoyaltyDiscountCalculatorService extends AbstractBusinessService<BillRequestDto, BillResponseDto> {

    @Override
    protected void validateRequest(BillRequestDto request) {
        if( null == request || null == request.getCustomer() || CollectionUtils.isEmpty( request.getItems() ) ) {
            throw new InvalidDataException( ErrorCode.ERROR_EMPTY_REQUEST.getCode(),
                ErrorCode.ERROR_EMPTY_REQUEST.getMessage() );
        }
    }

    @Override
    public BillResponseDto execute(BillRequestDto request) {
        BillResponseDto response = BillResponseDto.builder().build();
        double discountPercentage = calculateDiscount( request.getCustomer() );
        for( BillItem billItem : request.getItems() ) {
            response.setTotalDiscount(
                response.getTotalDiscount() + billItem.getTotalPrice() - getItemPrice( billItem, discountPercentage ) );
            response.setTotalPrice( response.getTotalPrice() + getItemPrice( billItem, discountPercentage ) );
        }
        response.setTotalDiscount( response.getTotalDiscount() + getExtraDiscount( response.getTotalPrice() ) );
        response.setTotalPrice( Math.round( response.getTotalPrice() - getExtraDiscount( response.getTotalPrice() ) ) );
        return response;
    }

    @Override
    protected void logTransaction(BillRequestDto request, BillResponseDto response) {
        log.info( "discount calculted Successfully for customer  {0} ", request.getCustomer().getId() );
    }

    private double calculateDiscount(Customer customer) {
        UserType userType = UserType.getType( customer.getType() );
        if( null != userType && (UserType.EMPLOYEE == userType || UserType.AFFILIATE == userType || isCustomerEligible(
            customer.getRegistrationDate(), userType )) ) {
            return userType.getDiscount();
        }
        return 0;
    }

    public double getItemPrice(BillItem billItem, double discountPercentage) {
        if( ItemType.getType( billItem.getItem().getType() ) == null && discountPercentage != 0 ) {
            return (billItem.getTotalPrice() - billItem.getTotalPrice() * discountPercentage / 100);
        }
        return billItem.getTotalPrice();
    }

    public boolean isCustomerEligible(LocalDateTime registrationDate, UserType type) {
        return (UserType.CUSTOMER == type) && registrationDate.isBefore( LocalDateTime.now().minusYears( 2 ) );
    }

    private double getExtraDiscount(double totalPrice) {
        return Math.floor( totalPrice / 100 ) * 5;
    }

    @Override
    protected String getServiceName() {
        return LoyaltyDiscountCalculatorService.class.getName();
    }
}
