package com.mycompany.refactoslog.Services.Shipping;

public class ShippingCostCalculatorAdapter implements ShippingCalculator{
    
    private ShippingCostCalculator shippingCostCalculator;

    public ShippingCostCalculatorAdapter(ShippingCostCalculator shippingCostCalculator) {
        this.shippingCostCalculator = shippingCostCalculator;
    }

    public float calculateShippingCost(int regionCode1, int regioncode2, float weight){
        return shippingCostCalculator.calculateShippingCost(regionCode1, regioncode2, weight);
    }
}
