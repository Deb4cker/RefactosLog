package com.mycompany.refactoslog.Visitor;

import com.mycompany.refactoslog.Model.Company;
import com.mycompany.refactoslog.Model.Residence;

public interface Visitor {
    String visitResidence (Residence residence);
    String visitCompany (Company company);
}
