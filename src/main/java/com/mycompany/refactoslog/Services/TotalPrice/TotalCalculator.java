package com.mycompany.refactoslog.Services.TotalPrice;

import com.mycompany.refactoslog.Model.Order;
import com.mycompany.refactoslog.Decorators.CustomService;

public interface TotalCalculator {
    float calculateTotal(Order order, CustomService services);
}
