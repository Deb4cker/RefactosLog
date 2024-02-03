package com.mycompany.refactoslog.AdaptableServices.TotalPrice;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Decorators.CustomService;
import com.mycompany.refactoslog.AdaptableServices.Shipping.ShippingCostCalculator;
import com.mycompany.refactoslog.AdaptableServices.Shipping.ShippingCostCalculatorAdapter;

public class TotalPriceCalculator {

    public TotalPriceCalculator() {}

    public float calculateTotalPrice(Order order, CustomService services){
        var costCalculator = new ShippingCostCalculatorAdapter(new ShippingCostCalculator());

        int regionFrom = order.getFromAddress().getRegion();
        int regionTo = order.getToAddress().getRegion();
        float weight = order.getWeight();

        float additionalServiceCost = services == null ? 0 : services.calculatePrice();
        float shippingCost = costCalculator.calculateShippingCost(regionFrom, regionTo, weight);
        float finalShippingCost = shippingCost + additionalServiceCost;

        return finalShippingCost;
    }

}
