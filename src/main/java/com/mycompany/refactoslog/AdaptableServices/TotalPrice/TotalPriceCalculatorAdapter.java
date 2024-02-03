package com.mycompany.refactoslog.AdaptableServices.TotalPrice;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Decorators.CustomService;

public class TotalPriceCalculatorAdapter implements TotalCalculator{

    private TotalPriceCalculator totalPriceCalculator;

    public TotalPriceCalculatorAdapter(TotalPriceCalculator totalPriceCalculator) {
        this.totalPriceCalculator = totalPriceCalculator;
    }

    @Override
    public float calculateTotal(Order order, CustomService services) {
        return totalPriceCalculator.calculateTotalPrice(order, services);
    }

}
