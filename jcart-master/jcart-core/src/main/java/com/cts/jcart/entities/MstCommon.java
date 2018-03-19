/**
 * 
 */
package com.cts.jcart.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ungtq
 *
 */
@Entity
@Table(name="mst_common")
public class MstCommon
{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="common_no", nullable=false)
	@NotEmpty()
	private String commonNo;
	
	@Column(name="class_no", nullable=false)
	@NotEmpty()
	private String classNo;
	
	@Column(name="class_name",nullable=false)
	@NotEmpty()
	private String className;
	
	@Column(name="char_data_1", nullable=true)
	private String charData1;
	
	@Column(name="char_data_2", nullable=true)
	private String charData2;
	
	@Column(name="char_data_3", nullable=true)
	private String charData3;
	
	@Column(name="char_data_4", nullable=true)
	private String charData4;
	
	@Column(name="char_data_5", nullable=true)
	private String charData5;
	
	@Column(name="char_data_6", nullable=true)
	private String charData6;
	
	@Column(name="num_data_1", nullable=true)
	private BigDecimal numData1;
	
	@Column(name="num_data_2", nullable=true)
	private BigDecimal numData2;
	
	@Column(name="num_data_3", nullable=true)
	private BigDecimal numData3;
	
	@Column(name="num_data_4", nullable=true)
	private BigDecimal numData4;
	
	@Column(name="num_data_5", nullable=true)
	private BigDecimal numData5;
	
	@Column(name="num_data_6", nullable=true)
	private BigDecimal numData6;
	
	@Column(name="flg_data_1", nullable=true)
	private boolean flgData1;
	
	@Column(name="flg_data_2", nullable=true)
	private boolean flgData2;
	
	@Column(name="flg_data_3", nullable=true)
	private boolean flgData3;
	
	@Column(name="flg_data_4", nullable=true)
	private boolean flgData4;
	
	@Column(name="flg_data_5", nullable=true)
	private boolean flgData5;
	
	@Column(name="flg_data_6", nullable=true)
	private boolean flgData6;
	
	@Column(name="last_update_emp_id", nullable=true)
	private int lastUpdateEmpId;
	
	@Column(name="last_update_time",nullable=true)
	private Timestamp lastUpdateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommonNo() {
		return commonNo;
	}

	public void setCommonNo(String commonNo) {
		this.commonNo = commonNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCharData1() {
		return charData1;
	}

	public void setCharData1(String charData1) {
		this.charData1 = charData1;
	}

	public String getCharData2() {
		return charData2;
	}

	public void setCharData2(String charData2) {
		this.charData2 = charData2;
	}

	public String getCharData3() {
		return charData3;
	}

	public void setCharData3(String charData3) {
		this.charData3 = charData3;
	}

	public String getCharData4() {
		return charData4;
	}

	public void setCharData4(String charData4) {
		this.charData4 = charData4;
	}

	public String getCharData5() {
		return charData5;
	}

	public void setCharData5(String charData5) {
		this.charData5 = charData5;
	}

	public String getCharData6() {
		return charData6;
	}

	public void setCharData6(String charData6) {
		this.charData6 = charData6;
	}

	public BigDecimal getNumData1() {
		return numData1;
	}

	public void setNumData1(BigDecimal numData1) {
		this.numData1 = numData1;
	}

	public BigDecimal getNumData2() {
		return numData2;
	}

	public void setNumData2(BigDecimal numData2) {
		this.numData2 = numData2;
	}

	public BigDecimal getNumData3() {
		return numData3;
	}

	public void setNumData3(BigDecimal numData3) {
		this.numData3 = numData3;
	}

	public BigDecimal getNumData4() {
		return numData4;
	}

	public void setNumData4(BigDecimal numData4) {
		this.numData4 = numData4;
	}

	public BigDecimal getNumData5() {
		return numData5;
	}

	public void setNumData5(BigDecimal numData5) {
		this.numData5 = numData5;
	}

	public BigDecimal getNumData6() {
		return numData6;
	}

	public void setNumData6(BigDecimal numData6) {
		this.numData6 = numData6;
	}

	public boolean isFlgData1() {
		return flgData1;
	}

	public void setFlgData1(boolean flgData1) {
		this.flgData1 = flgData1;
	}

	public boolean isFlgData2() {
		return flgData2;
	}

	public void setFlgData2(boolean flgData2) {
		this.flgData2 = flgData2;
	}

	public boolean isFlgData3() {
		return flgData3;
	}

	public void setFlgData3(boolean flgData3) {
		this.flgData3 = flgData3;
	}

	public boolean isFlgData4() {
		return flgData4;
	}

	public void setFlgData4(boolean flgData4) {
		this.flgData4 = flgData4;
	}

	public boolean isFlgData5() {
		return flgData5;
	}

	public void setFlgData5(boolean flgData5) {
		this.flgData5 = flgData5;
	}

	public boolean isFlgData6() {
		return flgData6;
	}

	public void setFlgData6(boolean flgData6) {
		this.flgData6 = flgData6;
	}

	public int getLastUpdateEmpId() {
		return lastUpdateEmpId;
	}

	public void setLastUpdateEmpId(int lastUpdateEmpId) {
		this.lastUpdateEmpId = lastUpdateEmpId;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
