package com.cts.jcart.dto;

import java.util.ArrayList;
import java.util.List;

import com.cts.jcart.wh.entities.WhWarehouse;

public class WhRemainDto {
	private int warehouseId;
	private String warehouseName;
	
	private int productId;
	private String productName;
	
	private List<WhAmountProductsOfWarehouse> lstAmountProduct;
	
	public WhRemainDto() {
		//TODO
	}
	
	public WhRemainDto(List<WhWarehouse> lstWarehouse) {
		lstAmountProduct = new ArrayList<WhAmountProductsOfWarehouse>();
		
		//set list of warehouse
		WhAmountProductsOfWarehouse whApOfWh = null;
		for (WhWarehouse wh : lstWarehouse) {
			whApOfWh = new WhAmountProductsOfWarehouse();
			whApOfWh.setWarehouseId(wh.getId());
			lstAmountProduct.add(whApOfWh);
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

	public List<WhAmountProductsOfWarehouse> getLstAmountProduct() {
		return lstAmountProduct;
	}

	public void setLstAmountProduct(List<WhAmountProductsOfWarehouse> lstAmountProduct) {
		this.lstAmountProduct = lstAmountProduct;
	}
	
	/**
	 * @author UNGTQ
	 * set amount to warehouse
	 * @param warehouseId
	 * @param amount
	 */
	public void setAmountOfWarehouse(long warehouseId, long amount) {
		if (lstAmountProduct == null || lstAmountProduct.size() == 0) {
			return;
		}
		
		for (WhAmountProductsOfWarehouse item : lstAmountProduct) {
			if (warehouseId == item.getWarehouseId()) {
				item.setTotalAmount(amount);
				break;
			}
		}
	}
}
