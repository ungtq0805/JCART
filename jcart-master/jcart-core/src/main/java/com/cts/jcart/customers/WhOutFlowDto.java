package com.cts.jcart.customers;

public class WhOutFlowDto {
	private int warehouseId;
	private String warehouseName;
	
	private int productId;
	private String productName;
	
	private long totalOutflowAmount;
	
	public WhOutFlowDto(Object[] obj) {
		warehouseId = (int)obj[0];
		warehouseName = (String)obj[1];
		
		productId = (int)obj[2];
		productName = (String)obj[3];
		totalOutflowAmount = (long)obj[4];
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getTotalOutflowAmount() {
		return totalOutflowAmount;
	}

	public void setTotalOutflowAmount(long totalOutflowAmount) {
		this.totalOutflowAmount = totalOutflowAmount;
	}
}
