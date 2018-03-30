package com.cts.jcart.wh.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.jcart.constant.JCartConsts;
import com.cts.jcart.constant.MstCmnConst;
import com.cts.jcart.dto.WhInflowDto;
import com.cts.jcart.dto.WhOutFlowDto;
import com.cts.jcart.dto.WhRemainDto;
import com.cts.jcart.wh.entities.WhInflow;
import com.cts.jcart.wh.entities.WhWarehouse;
import com.cts.jcart.wh.impl.WhWarehousesData;

/**
 * Provides methods which retrieve inflow data
 * from the database in the way needed
 * to form the current stock remains
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class WhRemainsDataImpl implements WhRemainsData {
	
	@Autowired
    WhWarehousesData warehousesData;
    
    @Autowired
    SessionFactory sessionFactory;

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    /**
     * Gets inflows from the database
     * ordered by the product name
     * and by the warehouse name
     * @return list of inflows
     */
    @SuppressWarnings("unchecked")
	public List<WhInflow> get() {
        return sessionFactory.getCurrentSession()
                .createCriteria(WhInflow.class)
                .createAlias("product", "p")
                .createAlias("warehouse", "w")
                .addOrder(Order.asc("p.name"))
                .addOrder(Order.asc("w.name"))
                .list();
    }
    
    /**
     * @author UNGTQ
     * get remain List
     * @return List<WhRemainDto>
     */
    public List<WhRemainDto> getRemainList() {
    	
    	List<WhRemainDto> lstAllRemain = new ArrayList<WhRemainDto>();
    	
    	//get all inflow
    	List<WhInflowDto> lstAllInFlow = getAllInFlow();
    	
    	//get all outflow
    	List<WhOutFlowDto> lstAllOutFlow = getAllOutFlow();
    	
    	List<WhWarehouse> lstWh = warehousesData.get();
    	
    	boolean isExistOutFlow = false;
    	
    	for (WhInflowDto inflowDto : lstAllInFlow) {
    		isExistOutFlow = false;
    		
    		//init and set list of warehouse
    		WhRemainDto remainDto = new WhRemainDto(lstWh);
    		
    		for (int j = 0; j < lstAllOutFlow.size(); j++) {
    			WhOutFlowDto outFlow = lstAllOutFlow.get(j);
    			
    			if (outFlow.getProductId() == inflowDto.getProductId() 
    				&& outFlow.getWarehouseId() == inflowDto.getWarehouseId()) {
    				remainDto.setWarehouseId(inflowDto.getWarehouseId());
    				remainDto.setWarehouseName(inflowDto.getWarehouseName());
    				remainDto.setProductId(inflowDto.getProductId());
    				remainDto.setProductName(inflowDto.getProductName());
    				remainDto.setAmountOfWarehouse(inflowDto.getWarehouseId(),
    						inflowDto.getTotalInflowAmount() - outFlow.getTotalOutflowAmount());
    				
    				isExistOutFlow = true;
    				
    				lstAllRemain.add(remainDto);
    			}
    		}
    		
    		if (!isExistOutFlow) {
    			remainDto.setWarehouseId(inflowDto.getWarehouseId());
				remainDto.setWarehouseName(inflowDto.getWarehouseName());
				remainDto.setProductId(inflowDto.getProductId());
				remainDto.setProductName(inflowDto.getProductName());
				remainDto.setAmountOfWarehouse(inflowDto.getWarehouseId(),
						inflowDto.getTotalInflowAmount());
				lstAllRemain.add(remainDto);
    		}
    	}
    	
    	return lstAllRemain;
    }
    
    /**
     * @author UNGTQ
     * @return InFlow Dto
     */
    @SuppressWarnings("unchecked")
	public List<WhInflowDto> getAllInFlow() {
    	List<WhInflowDto> lstWhInFlowDto = new ArrayList<WhInflowDto>();
    	
    	EntityManager em = emf.createEntityManager(); 
    	
    	//get list object of inflow
    	List<Object[]> lstObj = em.createNativeQuery(getSqlAllInFlow()).getResultList();
  
    	//convert list object to list DTO
    	WhInflowDto whInFlowDto = null;
    	for (Object[] obj : lstObj) {
    		whInFlowDto = new WhInflowDto(obj);
    		lstWhInFlowDto.add(whInFlowDto);
    	}
    	
    	return lstWhInFlowDto;
    }
    
    /**
     * @author UNGTQ
     * @return sql get allInFlow
     */
    private String getSqlAllInFlow() {
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append("SELECT A.WAREHOUSE as warehouseId, ")
    	.append("D.NAME as warehouseName, ")
    	.append("B.ID as productId, ")
    	.append("B.NAME as productName, ")
    	.append("SUM(A.AMOUNT) AS totalInflowAmount ")
    	.append("FROM WH_INFLOWS A ")
    	.append("INNER JOIN PRODUCTS B ON A.PRODUCT = B.ID ")
    	.append("INNER JOIN WH_WAREHOUSES D ON A.WAREHOUSE = D.ID ")
    	.append("GROUP BY A.WAREHOUSE, D.NAME, B.ID, B.NAME ")
    	.append("ORDER BY A.WAREHOUSE ");
    	
    	return builder.toString();
    }
    
    /**
     * @author UNGTQ
     * get all outFlow
     * @return List<WhOutFlowDto>
     */
    @SuppressWarnings("unchecked")
	public List<WhOutFlowDto> getAllOutFlow() {
    	List<WhOutFlowDto> lstWhOutFlowDto = new ArrayList<WhOutFlowDto>();
    	
    	EntityManager em = emf.createEntityManager(); 
    	
    	//get list object of outFlow
    	List<Object[]> lstObj = em.createNativeQuery(getSqlOutFlow()).getResultList();
    	
    	//convert list object to list DTO
    	WhOutFlowDto whOutFlowDto = null;
    	for (Object[] obj : lstObj) {
    		whOutFlowDto = new WhOutFlowDto(obj);
    		lstWhOutFlowDto.add(whOutFlowDto);
    	}
    	
    	return lstWhOutFlowDto;
    }
    
    /**
     * @author UNGTQ
     * @return sql get allInFlow
     */
    private String getSqlOutFlow() {
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append("SELECT B.WAREHOUSE AS warehouseId,  ")
    	.append("C.NAME as warehouseName, ")
    	.append("D.ID AS productId, ")
    	.append("D.NAME AS productName, ")
    	.append("SUM(A.AMOUNT) AS totalOutflowAmount ")
    	.append("FROM WH_OUTFLOWS A ")
    	.append("INNER JOIN WH_INFLOWS B ON A.INFLOW = B.ID ")
    	.append("INNER JOIN WH_WAREHOUSES C ON B.WAREHOUSE = C.ID ")
    	.append("INNER JOIN PRODUCTS D ON B.PRODUCT = D.ID ")
    	.append("GROUP BY B.WAREHOUSE, C.NAME, D.ID, D.NAME ")
    	.append("ORDER BY B.WAREHOUSE ");
    	
    	return builder.toString();
    }
    
    /**
     * get Payment
     * @author ungtq
     * @param dateYmd
     * @return
     */
    public BigDecimal getPaymentByDateOrYm(String byType, String dateYmd) {
    	EntityManager em = emf.createEntityManager();
    	
    	BigDecimal payment = null;
    	
    	if (byType.equals(JCartConsts.BY_DAY)) {
    		payment = (BigDecimal)em.createNativeQuery(getSqlPayment(JCartConsts.BY_DAY))
    				.setParameter("dateYmd", dateYmd).getSingleResult();
    	} else {
    		payment = (BigDecimal)em.createNativeQuery(getSqlPayment(JCartConsts.BY_MONTH))
    				.setParameter("dateYmd", dateYmd).getSingleResult();
    	}
    	
    	return payment;
    }
    
    /**
     * get SQL INFLOWS - Payment
     * @param byType
     * @return String
     */
    private String getSqlPayment(String byType) {
    	StringBuilder builder = new StringBuilder();
    	
    	if (byType.equals(JCartConsts.BY_DAY)) {
    		builder.append("SELECT COALESCE(SUM(I.amount * I.price), 0) from WH_INFLOWS I  ")
    		.append(" WHERE TO_CHAR(I.LAST_UPD_DATE, 'YYYYMMDD') = :dateYmd ")
    		.append(" AND I.STATUSKBN ='").append(MstCmnConst.MST_STATUS_APPROVE).append("'");
    	} else {
    		builder.append("SELECT COALESCE(SUM(I.amount * I.price), 0) from WH_INFLOWS I  ")
    		.append(" WHERE TO_CHAR(I.LAST_UPD_DATE, 'YYYYMM') = :dateYmd ")
    		.append(" AND I.STATUSKBN ='").append(MstCmnConst.MST_STATUS_APPROVE).append("'");
    	}
    	
    	return builder.toString();
    }
    
    /**
     * get Payment
     * @author ungtq
     * @param dateYmd
     * @return
     */
    public BigDecimal getRevenueByDateOrYm(String byType, String dateYmd) {
    	EntityManager em = emf.createEntityManager();
    	
    	BigDecimal payment = null;
    	
    	if (byType.equals(JCartConsts.BY_DAY)) {
    		payment = (BigDecimal)em.createNativeQuery(getSqlRevenue(JCartConsts.BY_DAY))
    				.setParameter("dateYmd", dateYmd).getSingleResult();
    	} else {
    		payment = (BigDecimal)em.createNativeQuery(getSqlRevenue(JCartConsts.BY_MONTH))
    				.setParameter("dateYmd", dateYmd).getSingleResult();
    	}
    	
    	return payment;
    	
    }
    
    /**
     * get SQL INFLOWS - Payment
     * @param byType
     * @return String
     */
    private String getSqlRevenue(String byType) {
    	StringBuilder builder = new StringBuilder();
    	
    	if (byType.equals(JCartConsts.BY_DAY)) {
    		builder.append("SELECT COALESCE(SUM(I.amount * I.price), 0) from WH_OUTFLOWS I  ")
    		.append(" WHERE TO_CHAR(I.LAST_UPD_DATE, 'YYYYMMDD') = :dateYmd ")
    		.append(" AND I.STATUSKBN ='").append(MstCmnConst.MST_STATUS_APPROVE).append("'");
    	} else {
    		builder.append("SELECT COALESCE(SUM(I.amount * I.price), 0) from WH_OUTFLOWS I  ")
    		.append(" WHERE TO_CHAR(I.LAST_UPD_DATE, 'YYYYMM') = :dateYmd ")
    		.append(" AND I.STATUSKBN ='").append(MstCmnConst.MST_STATUS_APPROVE).append("'");
    	}
    	
    	return builder.toString();
    }
}