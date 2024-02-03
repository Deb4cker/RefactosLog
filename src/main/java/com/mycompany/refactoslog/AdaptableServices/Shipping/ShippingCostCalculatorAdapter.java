package com.mycompany.refactoslog.AdaptableServices.Shipping;

public class ShippingCostCalculatorAdapter implements ShippingCalculator{
    
    private final ShippingCostCalculator shippingCostCalculator;

    public ShippingCostCalculatorAdapter(ShippingCostCalculator shippingCostCalculator) {
        this.shippingCostCalculator = shippingCostCalculator;
    }

    public float calculateShippingCost(int regionCode1, int regionCode2, float weight){
        return shippingCostCalculator.calculateShippingCost(regionCode1, regionCode2, weight);
    }
}
