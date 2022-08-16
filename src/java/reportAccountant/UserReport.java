/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportAccountant;

/**
 *
 * @author 84348
 */
public class UserReport {

    public String productID;
    public String name;
    public String brand;
    public int quantityBegin;
    public int quantityEnd;
    public int Import;
    public int Export;

    public UserReport() {
    }

    public UserReport(String productID, String name, String brand, int quantityBegin, int quantityEnd, int Import, int Export) {
        this.productID = productID;
        this.name = name;
        this.brand = brand;
        this.quantityBegin = quantityBegin;
        this.quantityEnd = quantityEnd;
        this.Import = Import;
        this.Export = Export;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantityBegin() {
        return quantityBegin;
    }

    public void setQuantityBegin(int quantityBegin) {
        this.quantityBegin = quantityBegin;
    }

    public int getQuantityEnd() {
        return quantityEnd;
    }

    public void setQuantityEnd(int quantityEnd) {
        this.quantityEnd = quantityEnd;
    }

    public int getImport() {
        return Import;
    }

    public void setImport(int Import) {
        this.Import = Import;
    }

    public int getExport() {
        return Export;
    }

    public void setExport(int Export) {
        this.Export = Export;
    }

}
