package cn.itcast.activiti;

import java.io.Serializable;
import java.util.Date;

public class AppayBillBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929185013012043689L;
	private Integer id;
	private Integer cost;//花费
	private String appayPerson;//申请人
	private Date date;//申请时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getAppayPerson() {
		return appayPerson;
	}
	public void setAppayPerson(String appayPerson) {
		this.appayPerson = appayPerson;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
