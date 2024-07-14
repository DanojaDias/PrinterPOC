package com.enactorsub.printer.poc;

import com.enactorsub.printer.poc.model.api.IPrinter;
import com.enactorsub.printer.poc.model.impl.Customer;
import com.enactorsub.printer.poc.model.impl.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PrinterService {

    @Autowired
    private IPrinter printer;

    ArrayList<Item> items;

    Customer customer;

    Item bottle;

    String clerkId;

    String pw;

    public PrinterService() {
        populateData();
    }
    public void populateData(){
        items = new ArrayList<Item>();
        items.add(new Item("11111","Item 4","","Kg","","1","C","00",120,2,"0"));
        items.add(new Item("22222","Item 3","","Kg","","1","C","00",50,3,"0"));
        customer = new Customer("12345678911", "Ashan Silva", "10230", "Madapatha", "Batakettara", "Polgahakottanuwa", "370/6");
        bottle = new Item("33333", "Vodka Bottle", "", "db", "","322144", "C","00", 150, 10, "0");
        clerkId = "0";
        pw = "000000";
    }

    public void printSalesReceipt(){
        try{
            sendRequest(printer.openSalesReceipt(clerkId, pw, null, 1));
            for (int i = 0; i < items.size(); i++) {
                sendRequest(printer.addItem(items.get(i)));

            }
            sendRequest(printer.addPayment("","","P",400));
            sendRequest(printer.closeFiscalReceipt());
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void printInvoice(String clerkId, String password, String paidMode, double amount){
        try{
            sendRequest(printer.openInvoice(clerkId, password, customer, 1));
            for (int i = 0; i < items.size(); i++) {
                sendRequest(printer.addItem(items.get(i)));
            }
            sendRequest(printer.addPayment("","",paidMode,amount));
            sendRequest(printer.closeFiscalReceipt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printReturnReceipt(String apNumber, String date, String receiptType, int z1Count, int recNo, int reasonNo, String paidMode, HashMap<Integer, Double> itemsList){
        try{
            sendRequest(printer.returnReceipt(apNumber, date, receiptType, z1Count, recNo, reasonNo, customer, paidMode, itemsList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void dayOpen(String mode, HashMap<String,Double> pay){
        try{
            sendRequest(printer.dayOpen(mode, pay));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printPayIn(int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        try {
            sendRequest(printer.payIn(reasonNo, pay,noOfCopies));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printPayOut(int reasonNo, HashMap<String,Double> pay, int noOfCopies){
        try{
            sendRequest(printer.payOut(reasonNo, pay, noOfCopies));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printPaymentMediaChange(String fromPaidMode, double amount, String toPaidMode){
        try {
            sendRequest(printer.changePaymentMode(fromPaidMode, amount, toPaidMode));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printZReport(int printOption){
        try {
            sendRequest(printer.getZReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printXReport(int printOption){
        try {
            sendRequest(printer.getXReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printCurrentDayEJReport(int printOption){
        try {
            sendRequest(printer.getCurrentDayEJReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printBeforeDayEJReport(int printOption){
        try {
            sendRequest(printer.getBeforeDayEJReport(printOption));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkAEEStatus(){
        try {
            sendRequest(printer.checkAEEStatus());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printBottleRefund(String clerkId, String password, int reasonNo){
        try {
            sendRequest(printer.openRefundReceipt(clerkId, password, reasonNo));
            sendRequest(printer.addItem(bottle));
            sendRequest(printer.closeRefundReceipt(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void cancelFiscalReceipt(){
        try {
            sendRequest(printer.cancelFiscalReceipt());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printReceiptCopy(String z1Count, String recNumber){
        try {
            sendRequest(printer.printReceiptCopy(z1Count, recNumber));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getEJtoUSBbyDate(String fromDate, String toDate){
        try {
            sendRequest(printer.getEJtoUSBbyDate(fromDate, toDate));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getEJtoUSBbyNumber(String from, String to){
        try {
            sendRequest(printer.getEJtoUSBbyNumber(from, to));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRequest(String command) throws IOException {
        URL url = new URL("http://127.0.0.1:15000/Request");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Host","127.0.0.1");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(command.length()));
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        try{
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            byte[] body = command.getBytes((StandardCharsets.UTF_8));
            outputStream.write(body);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }
}
