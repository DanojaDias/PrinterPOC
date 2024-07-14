package com.enactorsub.printer.poc.model.impl;

import com.enactorsub.printer.poc.model.api.IPrinter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class HungaryPriorCash implements IPrinter {

    @Override
    public String addItem(Item item){
        return "command=sbt\t"+item.getUniqueId()+"\t"+item.getLineOne()+"\t"+item.getLineTwo()+"\t"+item.getUnitName()+"\t"+item.getComment()+"\t"+item.getItemNumber()+"\t"+item.getTaxLetter()+"\t"+item.getCollector()+"\t"+item.getPrice()+"\t"+item.getQuantity()+"\t"+item.getDiscount();
    }

    @Override
    public String addSubTotal(boolean printSubTotal, boolean displaySubTotal, String discount){
        return "command=st\t"+printSubTotal+"\t"+displaySubTotal+"\n"+discount;
    }

    @Override
    public String openSalesReceipt(String clerkId, String password, Customer customer, int noOfCopies){
        String out = "command=ofr\t"+clerkId+"\t"+password+"\t0\t0";
        if(customer != null){
            out+="\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t";
        }
        if(noOfCopies ==1){
            out+="\t1";
        }
        else{
            out+="\t2";
        }
        return out;
    }

    @Override
    public String addPayment(String lineOne, String lineTwo, String paidMode, double amount){
        return "command=pmt\t"+lineOne+"\t"+lineTwo+"\t"+paidMode+"\t"+amount;
    }

    @Override
    public String closeFiscalReceipt(){
        return "command=cfr";
    }

    @Override
    public String dayOpen(String mode, HashMap<String, Double> pay){
        if(mode == null && pay == null){
            return "command=fod";
        } else if (mode=="R") {
            return "command=fod\tR";
        }else{
            String out = "command=fod\t";
            Object[] keys = pay.keySet().toArray();
            for (int i = 0; i < keys.length; i++) {
                out+=keys[i]+","+pay.get(keys[i]);
                if(i<keys.length-1){
                    out+="|";
                }
            }
//            System.out.println(out);
            return out;
        }
    }

    @Override
    public String openInvoice(String clerkId, String password, Customer customer, int noOfCopies){
        String out = "command=ofr\t"+clerkId+"\t"+password+"\t0\tI\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t";
        if(noOfCopies ==1){
            out+="\t1";
        }
        else{
            out+="\t2";
        }
        return out;
    }


    @Override
    public String returnReceipt(String apNumber, String date, String receiptType, int z1Count, int recNo, int reasonNo, Customer customer, String paidMode, HashMap<Integer, Double> itemsList){
        String out = "command=bbr\t"+apNumber+"\t"+date+"\t"+receiptType+"\t"+z1Count+"\t"+recNo+"\t"+reasonNo+"\t"+customer.getTaxRefNum()+"\t"+customer.getCustomerName()+"\t"+customer.getPostCode()+"\t"+customer.getCity()+"\t"+customer.getAddress()+"\t"+customer.getStreetType()+"\t"+customer.getHouseNumber()+"\t"+paidMode;
        if(itemsList==null){
            return out;
        }
        else{
            out+="\t";
            Object[] keys = itemsList.keySet().toArray();
            for (int i = 0; i < itemsList.size(); i++) {
                if(itemsList.get(keys[i]) == null){
                    out+=String.valueOf(keys[i]);
                }
                else{
                    out+= keys[i] +":"+ itemsList.get(keys[i]);

                }
                if(i<itemsList.size()-1){
                    out+="\t";
                }


            }
            System.out.println(out);
            return out;
        }

    }

    @Override
    public String payIn(int reasonNo, HashMap<String, Double> pay, int noOfCopies){
        String out = "command=ra\t"+reasonNo+"\t";
        Object[] keys = pay.keySet().toArray();
        for (int i = 0; i < pay.size(); i++) {
            out+=keys[i]+","+pay.get(keys[i])+"|";

        }
        if(noOfCopies==1){
            return out+="COPY1";
        }
        else{
            out+="COPY2";
        }
        return out;
    }

    @Override
    public String payOut(int reasonNo, HashMap<String, Double> pay, int noOfCopies){
        String out = "command=po\t"+reasonNo+"\t";
        Object[] keys = pay.keySet().toArray();
        for (int i = 0; i < pay.size(); i++) {
            out+=keys[i]+","+pay.get(keys[i])+"|";

        }
        if(noOfCopies==1){
            return out+="COPY1";
        }
        else{
            out+="COPY2";
        }
        return out;
    }

    @Override
    public String changePaymentMode(String fromPaidMode, double amount, String toPaidMode){
        System.out.println("command=cpmm\t"+fromPaidMode+","+amount+"\t"+toPaidMode);
        return "command=cpmm\t"+fromPaidMode+","+amount+"\t"+toPaidMode;
    }

    @Override
    public String getZReport(int printOption){
        return "command=pgr\t"+printOption+"\t"+19;
    }

    @Override
    public String getXReport(int printOption){
        return "command=pgr\t"+printOption+"\t"+2;
    }

    @Override
    public String getCurrentDayEJReport(int printOption){
        return "command=pcdej\t"+printOption;
    }

    @Override
    public String getBeforeDayEJReport(int printOption){
        return "command=pbdej\t"+printOption;
    }

    @Override
    public String checkAEEStatus(){
        return "command=rs";
    }

    ///for bottle refund
    @Override
    public String openRefundReceipt(String clerkId, String password, int reasonNo){
        return "command=ofr\t"+clerkId+"\t"+password+"\t0\tR\t"+reasonNo;
    }

    @Override
    public String changeFtEuroRate(double ftEuroRate){
        return "command=wfc\t"+ftEuroRate;
    }

    @Override
    public String readForeignCurrencyRate(){
        return "command=rfc";
    }

    @Override
    public String writeTailMessage(int lineNumber, String message){
        return "command=wtm\t"+lineNumber+"\t"+message;
    }

    @Override
    public String readTailMessage(int lineNumber){
        return "command=rtm\t"+lineNumber;
    }

    @Override
    public String writeTailSpaceLine(int spaceLine){
        return "command=wts\t"+spaceLine;
    }

    @Override
    public String readTailSpaceLine(){
        return "command=rts";
    }

    @Override
    public String giveNormaldiscount(String mode, double value, String comment){
        String out="command=d\t"+mode+","+value;
        if(comment != null){
            out+=","+comment;
        }
        return out;
    }

    @Override
    public String giveTaxDiscount(String mode, String value, String comment, String taXLetter, String collector){
        String out="command=d\t"+mode+","+value;
        if(comment != null){
            out+=","+comment;
        }
        out+="\n"+taXLetter+","+collector;
        return out;
    }

    @Override
    public String writeDevTime(int dd, int mm, int yy, int hh, int min){
        return "command=wdt\t"+dd+"-"+mm+"-"+yy+" "+hh+":"+min;
    }

    @Override
    public String closeRefundReceipt(int recyclePayment){
        return "command=crr\t"+recyclePayment;
    }

    @Override
    public String cancelFiscalReceipt(){
        return "command=celfr";
    }

    @Override
    public String readXData(int dataType){
        return "command=rxd\t"+dataType;
    }

    @Override
    public String writeCustomPaymentsName(ArrayList<String> paymentNames){
        String out = "command=wcpm\t";
        for (int i = 0; i < paymentNames.size(); i++) {
            out+= paymentNames.get(i);
            if(i< paymentNames.size()-1){
                out+="\n";
            }
        }
        return out;
    }

    @Override
    public String writeOperator(int id, String name, String password){
        return "command=wo\t"+id+"\t"+name+"\t"+password;
    }

    @Override
    public String openDrawer(){
        return "command=od";
    }

    @Override
    public String clear(){
        return "command=clr";
    }

    @Override
    public String clearLastAddedItem(){
        return "command=ec";
    }

    @Override
    public String openNonFiscalReceipt(){
        return "command=onfr";
    }

    @Override
    //    TODO: Test print non fiscal Text Function
    public String printNonFiscalText(HashMap<String, String> data){
        String out = "command=pnft\t";
        Object[] formats = data.keySet().toArray();
        for (int i = 0; i < data.size(); i++) {
            out+=formats[i]+"\r"+data.get(formats[i]);
            if(i<data.size()-1){
                out+="\n";
            }
        }
        return out;
    }

    @Override
    public String closeNonFiscalReceipt(){
        return "command=cnfr";
    }

    @Override
    public String printFiscalMemoryDetailReportByNumber(int fromZCount, int toZCount){
        return "command=pfdrn\t"+fromZCount+"\t"+toZCount;
    }

    @Override
    public String printFiscalMemoryDetailReportByDate(int fromDate, int toDate){
        return "command=pfdrd\t"+fromDate+"\t"+toDate;
    }

    @Override
    public String printFiscalMemoryShortReportByNumber(String fromZCount, String toZCount){
        return "command=pfsrn\t"+fromZCount+"\t"+toZCount;
    }

    @Override
    public String printFiscalMemoryShortReportByDate(String fromDate, String toDate){
        return "command=pfsrd\t"+fromDate+"\t"+toDate;
    }

    @Override
    public String printDiagnInfo(int option){
        return "command=pdi\t"+option;
    }

    @Override
    public String printBarcode(String type, String text){
        return "command=pcd\tC\t"+type+","+text;
    }

    @Override
    //    TODO: Test print custom tail message functionality
    public String printCustomTailMessage(int packIndex, HashMap<String, String> data){
        String out="command=pcd\tT"+packIndex+"\t";
        Object[] formats = data.keySet().toArray();
        for (int i = 0; i < data.size(); i++) {
            out+=formats[i]+"\r"+data.get(formats[i]);
            if(i<data.size()-1){
                out+="\n";
            }
        }
        return out;
    }

    @Override
    public String clearDisplay(){
        return "command=cd";
    }

    @Override
    public String showDateTime(){
        return "command=sdt";
    }

    @Override
    //    TODO: Test readReceipt functionality
    public String readReceipt(int receiptMode, int zCount, int receiptNum){
        return "command=rfr\t"+receiptMode+"\t"+zCount+"\t"+receiptMode;
    }

    @Override
    public String testConnection(int type){
        return "command=tcg\t"+type;
    }

    @Override
    public String readCustomForeignCurrency(){
        return "command=rcfc";
    }

    @Override
    public String setCustomForeignCurrency(HashMap<String, Double> currencies){
        String out = "command=wcfc";
        Object[] keys = currencies.keySet().toArray();
        for (int i = 0; i < currencies.size(); i++) {
            out+="\t"+keys[i]+"\t"+currencies.get(keys[i]);
        }
        return out;

    }

    @Override
    public String readLastFullReceiptStatus(){
        return "command=rlrn";
    }

    @Override
    public String readDrawerContentByVATCategory(int type){
        return "command=rvdd\t"+type;
    }


    @Override
    public String printReceiptCopy(String z1Count, String recNumber){
        return "command=pzrej\t1\t1\t"+z1Count+recNumber;
    }

    @Override
    public String getEJtoUSBbyDate(String fromDate, String toDate){
        return "command=exml\t0,0,"+fromDate+","+toDate;
    }

    @Override
    public String getEJtoUSBbyNumber(String from, String to){
        return "command=exml\t0,0,"+from+","+to;
    }

}
