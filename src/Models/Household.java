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
public class Household {

    private int electricityLineNo;
    private String lName;
    private String fName;
    private String mName;
    private String contactNumber;
    private String address;
    private String streetNumber;

  

    public Household(int electricityLineNo, String lName, String fName, String mName, String contactNumber, String address, String streetNumber) {
        this.electricityLineNo = electricityLineNo;
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.streetNumber = streetNumber;
    }
    
 

    public int getElectricityLineNo() {
        return electricityLineNo;
    }

    public void setElectricityLineNo(int electricityLineNo) {
        this.electricityLineNo = electricityLineNo;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
      public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    

   
}
