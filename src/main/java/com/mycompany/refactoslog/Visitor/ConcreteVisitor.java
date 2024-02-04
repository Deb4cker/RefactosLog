package com.mycompany.refactoslog.Visitor;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mycompany.refactoslog.Model.Address;
import com.mycompany.refactoslog.Model.Company;
import com.mycompany.refactoslog.Model.Residence;

public class ConcreteVisitor implements Visitor{

    @Override
    public String visitResidence(Residence residence) {
        return "Tipo de recibo: Residência \n\n" +
        residence.toString();
    }

    @Override
    public String visitCompany(Company company) {
        return "Tipo de recibo: Empresa \n\n" +
        company.toString();
    }

    public void generateDeliveryReceipt(Address address){
        String contentText = address.accept(this);
        JFrame frame = new JFrame();
        JLabel content = new JLabel(contentText);
        content.setBounds(10, 30, 400, 600);
        frame.add(content);
        frame.setSize(500, 800);
        frame.setVisible(true);
    }
}
