package com.mycompany.refactoslog.Visitor;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.Company;
import com.mycompany.refactoslog.Model.Residence;

public class ConcreteVisitor implements Visitor{

    @Override
    public String visitResidence(Residence residence) {
        return "Tipo de recibo: Residência\n" +
        residence.toString();
    }

    @Override
    public String visitCompany(Company company) {
        return "Tipo de recibo: Empresa\n" +
        company.toString();
    }

    public void generateDeliveryReceipt(Address address){
        String contentText = address.accept(this);
        JFrame frame = new JFrame();
        JLabel content = new JLabel(contentText);
        frame.add(content);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
