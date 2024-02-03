package com.mycompany.refactoslog.AdaptableServices.TotalPrice;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Decorators.CustomService;

public interface TotalCalculator {
    float calculateTotal(Order order, CustomService services);
}
