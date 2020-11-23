/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author 1styrGroupC
 */
public class TransactionRecords {
    private int id;
    private int lineNo;
    private int billNumber;
    private double penalty;
    private double cashRendered;
    private double cashChange;
    private double totalAmount;
    private int recordedBy;
    private String recordedDate;

    public TransactionRecords(int id, int lineNo, int billNumber,double penalty, double cashRendered, double cashChange, double totalAmount, int recordedBy, String recordedDate) {
        this.id = id;
        this.lineNo = lineNo;
        this.billNumber = billNumber;
        this.penalty = penalty;
        this.cashRendered = cashRendered;
        this.cashChange = cashChange;
        this.totalAmount = totalAmount;
        this.recordedBy = recordedBy;
        this.recordedDate = recordedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }


    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getCashRendered() {
        return cashRendered;
    }

    public void setCashRendered(double cashRendered) {
        this.cashRendered = cashRendered;
    }

    public double getCashChange() {
        return cashChange;
    }

    public void setCashChange(double cashChange) {
        this.cashChange = cashChange;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(int recordedBy) {
        this.recordedBy = recordedBy;
    }
    
    
    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }
    
    

}
