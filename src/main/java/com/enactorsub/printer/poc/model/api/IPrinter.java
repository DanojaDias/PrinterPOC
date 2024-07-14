package com.enactorsub.printer.poc.model.api;

import com.enactorsub.printer.poc.model.impl.Customer;
import com.enactorsub.printer.poc.model.impl.Item;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPrinter {

    Customer customer = new Customer("12345678911", "Ashan Silva", "10230", "Madapatha", "Batakettara", "Polgahakottanuwa", "370/6");

    Item bottle = new Item("33333", "Vodka Bottle", "", "db", "","322144", "C","00", 150, 10, "0");

    String addItem(Item item);

    String addSubTotal(boolean printSubTotal, boolean displaySubTotal, String discount);

    String openSalesReceipt(String clerkId, String password, Customer customer, int noOfCopies);

    String addPayment(String lineOne, String lineTwo, String paidMode, double amount);

    String closeFiscalReceipt();

    String dayOpen(String mode, HashMap<String, Double> pay);

    String openInvoice(String clerkId, String password, Customer customer, int noOfCopies);

    String returnReceipt(String apNumber, String date, String receiptType, int z1Count, int recNo, int reasonNo, Customer customer, String paidMode, HashMap<Integer, Double> itemsList);

    String payIn(int reasonNo, HashMap<String, Double> pay, int noOfCopies);

    String payOut(int reasonNo, HashMap<String, Double> pay, int noOfCopies);

    String changePaymentMode(String fromPaidMode, double amount, String toPaidMode);

    String getZReport(int printOption);

    String getXReport(int printOption);

    String getCurrentDayEJReport(int printOption);

    String getBeforeDayEJReport(int printOption);

    String checkAEEStatus();

    ///for bottle refund
    String openRefundReceipt(String clerkId, String password, int reasonNo);

    String changeFtEuroRate(double ftEuroRate);

    String readForeignCurrencyRate();

    String writeTailMessage(int lineNumber, String message);

    String readTailMessage(int lineNumber);

    String writeTailSpaceLine(int spaceLine);

    String readTailSpaceLine();

    String giveNormaldiscount(String mode, double value, String comment);

    String giveTaxDiscount(String mode, String value, String comment, String taXLetter, String collector);

    String writeDevTime(int dd, int mm, int yy, int hh, int min);

    String closeRefundReceipt(int recyclePayment);

    String cancelFiscalReceipt();

    String readXData(int dataType);

    String writeCustomPaymentsName(ArrayList<String> paymentNames);

    String writeOperator(int id, String name, String password);

    String openDrawer();

    String clear();

    String clearLastAddedItem();

    String openNonFiscalReceipt();

    //    TODO: Test print non fiscal Text Function
    String printNonFiscalText(HashMap<String, String> data);

    String closeNonFiscalReceipt();

    String printFiscalMemoryDetailReportByNumber(int fromZCount, int toZCount);

    String printFiscalMemoryDetailReportByDate(int fromDate, int toDate);

    String printFiscalMemoryShortReportByNumber(String fromZCount, String toZCount);

    String printFiscalMemoryShortReportByDate(String fromDate, String toDate);

    String printDiagnInfo(int option);

    String printBarcode(String type, String text);

    String printCustomTailMessage(int packIndex, HashMap<String, String> data);

    String clearDisplay();

    String showDateTime();

    String readReceipt(int receiptMode, int zCount, int receiptNum);

    String testConnection(int type);

    String readCustomForeignCurrency();

    String setCustomForeignCurrency(HashMap<String, Double> currencies);

    String readLastFullReceiptStatus();

    String readDrawerContentByVATCategory(int type);

    String printReceiptCopy(String z1Count, String recNumber);

    String getEJtoUSBbyDate(String fromDate, String toDate);

    String getEJtoUSBbyNumber(String from, String to);
}
