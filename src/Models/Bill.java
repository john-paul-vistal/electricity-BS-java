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
public class Bill {

    private int id;
    private int electricityLineNo;
    private int reading;
    private double amountDue;
    private int status;
    private String recordedDate;
    private String dueDate;
     private int recordedBy;

    public Bill(int id, int electricityLineNo, int reading, double amountDue, int status, String recordedDate, String dueDate, int recordedBy) {
        this.id = id;
        this.electricityLineNo = electricityLineNo;
        this.reading = reading;
        this.amountDue = amountDue;
        this.status = status;
        this.recordedDate = recordedDate;
        this.dueDate = dueDate;
        this.recordedBy = recordedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getElectricityLineNo() {
        return electricityLineNo;
    }

    public void setElectricityLineNo(int electricityLineNo) {
        this.electricityLineNo = electricityLineNo;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(int recordedBy) {
        this.recordedBy = recordedBy;
    }
    
    
   

  
}
