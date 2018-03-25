package com.cts.jcart.dto;

public class WhOutFlowDto {
	private int warehouseId;
	private String warehouseName;
	
	private int productId;
	private String productName;
	
	private long totalOutflowAmount;
	
	public WhOutFlowDto(Object[] obj) {
		if (obj[0] != null) {
			warehouseId = Integer.parseInt(obj[0].toString());
		}
		
		if (obj[1] != null) {
			warehouseName = (String)obj[1];
		}
		
		if (obj[2] != null) {
			productId = (int)obj[2];
		}
		
		if (obj[3] != null) {
			productName = (String)obj[3];
		}
		
		if (obj[4] != null) {
			totalOutflowAmount = Long.parseLong(obj[4].toString());
		}
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
