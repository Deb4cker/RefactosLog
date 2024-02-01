package com.mycompany.refactoslog.Services.Shipping;

public interface ShippingCalculator {
    float calculateShippingCost(int regionCode1, int regioncode2, float weight);
}
