package com.mycompany.refactoslog.AdaptableServices.Shipping;

public class ShippingCostCalculator {

    public ShippingCostCalculator() {}

    public float calculateShippingCost(int fromRegion, int toRegion, float weight){

        var minorRegion = Math.min(fromRegion, toRegion);
        var majorRegion = Math.max(fromRegion, toRegion);

        var delta = majorRegion - minorRegion;

        return (delta + 1) * weight;
    };
}
