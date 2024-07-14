package com.enactorsub.printer.poc.model.impl;


public class Item {
    private final String uniqueId;
    private final String lineOne;
    private final String lineTwo;
    private final String unitName;
    private final String comment;
    private final String itemNumber;
    private final String taxLetter;
    private final String collector;
    private final String price;
    private final String quantity;
    private final String discount;

    public Item(String uniqueId, String lineOne,String lineTwo, String unitName, String comment, String itemNumber, String taxLetter, String collector, double price, double quantity, String discount){
        this.uniqueId = uniqueId;
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.unitName = unitName;
        this.comment = comment;
        this.itemNumber = itemNumber;
        this.taxLetter = taxLetter;
        this.collector = collector;
        this.price = String.valueOf(price);
        this.quantity = String.valueOf(quantity);
        this.discount = discount;

    }

    public String getUniqueId(){
        return this.uniqueId;
    }
    public String getLineOne(){
        return this.lineOne;
    }
    public String getLineTwo(){
        return this.lineTwo;
    }
    public String getUnitName(){
        return this.unitName;
    }
    public String getComment(){
        return this.comment;
    }
    public String getItemNumber(){
        return  this.itemNumber;
    }
    public String getTaxLetter(){
        return this.taxLetter;

    }
    public String getCollector(){
        return this.collector;
    }

    public String getPrice(){
        return this.price;
    }
    public String getQuantity(){
        return this.quantity;
    }
    public String getDiscount(){
        return this.discount;
    }

}
