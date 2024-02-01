package com.mycompany.refactoslog.Services.TotalPrice;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Model.Decorators.CustomService;

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
