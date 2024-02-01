package com.mycompany.refactoslog.Services;

public class ShippingCostCalculator {

    public float calculateShippingCost(int fromRegion, int toRegion){

        var minorRegion = Math.min(fromRegion, toRegion);
        var majorRegion = Math.max(fromRegion, toRegion);

        var delta = majorRegion - minorRegion;

        return delta + 1;
    };

}
