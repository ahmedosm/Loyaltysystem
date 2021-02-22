package com.osm.Loyaltysystem.business;


import com.osm.Loyaltysystem.business.impl.LoyaltyDiscountCalculatorService;
import com.osm.Loyaltysystem.dto.BillRequestDto;
import com.osm.Loyaltysystem.dto.BillResponseDto;
import com.osm.Loyaltysystem.enums.UserType;
import com.osm.Loyaltysystem.exception.InvalidDataException;
import com.osm.Loyaltysystem.util.LoyalityDiscountDataUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.Silent.class )
public class LoyaltyDiscountCalculatorServiceTest {

    private LoyaltyDiscountCalculatorService loyaltyDiscountCalculatorService;

    @Before
    public void setup() {
        loyaltyDiscountCalculatorService = new LoyaltyDiscountCalculatorService();
    }


    @Test
    public void testAffiliateWithMultipleItems() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.AFFILIATE.getType(), 2 ) ).items(
            LoyalityDiscountDataUtil.getMultipleItems() ).build();
        BillResponseDto billResponseDto = loyaltyDiscountCalculatorService.doAction( requestDto );
        Assert.assertEquals( new Double( 85 ), new Double( billResponseDto.getTotalDiscount() ) );
        Assert.assertEquals( new Double( 715 ), new Double( billResponseDto.getTotalPrice() ) );
    }

    @Test
    public void testEmployeeWithMultipleItems() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.EMPLOYEE.getType(), 2 ) ).items(
            LoyalityDiscountDataUtil.getMultipleItems() ).build();
        BillResponseDto billResponseDto = loyaltyDiscountCalculatorService.doAction( requestDto );
        Assert.assertEquals( new Double( 180 ), new Double( billResponseDto.getTotalDiscount() ) );
        Assert.assertEquals( new Double( 620 ), new Double( billResponseDto.getTotalPrice() ) );
    }

    @Test
    public void testCustomerHasMoreThanTwoYears() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.CUSTOMER.getType(), 3 ) ).items(
            LoyalityDiscountDataUtil.getMultipleItems() ).build();
        BillResponseDto billResponseDto = loyaltyDiscountCalculatorService.doAction( requestDto );
        Assert.assertEquals( new Double( 60 ), new Double( billResponseDto.getTotalDiscount() ) );
        Assert.assertEquals( new Double( 740 ), new Double( billResponseDto.getTotalPrice() ) );
    }

    @Test
    public void testCustomerHasLessThanTwoYears() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.CUSTOMER.getType(), 1 ) ).items(
            LoyalityDiscountDataUtil.getMultipleItems() ).build();
        BillResponseDto billResponseDto = loyaltyDiscountCalculatorService.doAction( requestDto );
        Assert.assertEquals( new Double( 40 ), new Double( billResponseDto.getTotalDiscount() ) );
        Assert.assertEquals( new Double( 760 ), new Double( billResponseDto.getTotalPrice() ) );
    }

    @Test
    public void testEmployeeWithNonDiscountableItems() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.EMPLOYEE.getType(), 2 ) ).items(
            LoyalityDiscountDataUtil.getGroceriesItem() ).build();
        BillResponseDto billResponseDto = loyaltyDiscountCalculatorService.doAction( requestDto );
        Assert.assertEquals( new Double( 15 ), new Double( billResponseDto.getTotalDiscount() ) );
        Assert.assertEquals( new Double( 285 ), new Double( billResponseDto.getTotalPrice() ) );
    }


    @Test( expected = InvalidDataException.class )
    public void testInvalidDataException() {
        BillRequestDto requestDto = BillRequestDto.builder().customer(
            LoyalityDiscountDataUtil.getCustomer( UserType.EMPLOYEE.getType(), 2 ) ).build();
        loyaltyDiscountCalculatorService.doAction( requestDto );
    }

}

