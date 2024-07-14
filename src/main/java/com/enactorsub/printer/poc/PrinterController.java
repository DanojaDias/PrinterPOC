package com.enactorsub.printer.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrinterController {

    @Autowired
    PrinterService printerService;

    @RequestMapping("/printSaleReceipt")
    public boolean printSalesReceipt() {
        printerService.printSalesReceipt();
        return true;
    }
}
