package com.mycompany.refactoslog.AdaptableServices.Shipping;

public interface ShippingCalculator {
    float calculateShippingCost(int regionCode1, int regionCode2, float weight);
}
