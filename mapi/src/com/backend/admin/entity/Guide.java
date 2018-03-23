package com.backend.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 番茄管家使用指南
 * </p>
 *
 * @author 
 * @since 2018-03-23
 */
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 标题
     */
	private String title;
    /**
     * 1,初级2,进阶
     */
	private Integer type;
    /**
     * 功能说明
     */
	private String functionDes;
    /**
     * 操作介绍文本
     */
	private String opIntroduceTxt1;
    /**
     * 操作介绍图片url
     */
	private String opIntroduceImg1;

	private String opIntroduceTxt2;

	private String opIntroduceImg2;

	private String opIntroduceTxt3;

	private String opIntroduceImg3;

	private String opIntroduceTxt4;

	private String opIntroduceImg4;

	private String opIntroduceTxt5;

	private String opIntroduceImg5;

	/**
	 * 0,无效1,有效
	 */
	private Integer status;

	private Integer isDelete;

	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFunctionDes() {
		return functionDes;
	}

	public void setFunctionDes(String functionDes) {
		this.functionDes = functionDes;
	}

	public String getOpIntroduceTxt1() {
		return opIntroduceTxt1;
	}

	public void setOpIntroduceTxt1(String opIntroduceTxt1) {
		this.opIntroduceTxt1 = opIntroduceTxt1;
	}

	public String getOpIntroduceImg1() {
		return opIntroduceImg1;
	}

	public void setOpIntroduceImg1(String opIntroduceImg1) {
		this.opIntroduceImg1 = opIntroduceImg1;
	}

	public String getOpIntroduceTxt2() {
		return opIntroduceTxt2;
	}

	public void setOpIntroduceTxt2(String opIntroduceTxt2) {
		this.opIntroduceTxt2 = opIntroduceTxt2;
	}

	public String getOpIntroduceImg2() {
		return opIntroduceImg2;
	}

	public void setOpIntroduceImg2(String opIntroduceImg2) {
		this.opIntroduceImg2 = opIntroduceImg2;
	}

	public String getOpIntroduceTxt3() {
		return opIntroduceTxt3;
	}

	public void setOpIntroduceTxt3(String opIntroduceTxt3) {
		this.opIntroduceTxt3 = opIntroduceTxt3;
	}

	public String getOpIntroduceImg3() {
		return opIntroduceImg3;
	}

	public void setOpIntroduceImg3(String opIntroduceImg3) {
		this.opIntroduceImg3 = opIntroduceImg3;
	}

	public String getOpIntroduceTxt4() {
		return opIntroduceTxt4;
	}

	public void setOpIntroduceTxt4(String opIntroduceTxt4) {
		this.opIntroduceTxt4 = opIntroduceTxt4;
	}

	public String getOpIntroduceImg4() {
		return opIntroduceImg4;
	}

	public void setOpIntroduceImg4(String opIntroduceImg4) {
		this.opIntroduceImg4 = opIntroduceImg4;
	}

	public String getOpIntroduceTxt5() {
		return opIntroduceTxt5;
	}

	public void setOpIntroduceTxt5(String opIntroduceTxt5) {
		this.opIntroduceTxt5 = opIntroduceTxt5;
	}

	public String getOpIntroduceImg5() {
		return opIntroduceImg5;
	}

	public void setOpIntroduceImg5(String opIntroduceImg5) {
		this.opIntroduceImg5 = opIntroduceImg5;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Guide{" +
			", id=" + id +
			", title=" + title +
			", type=" + type +
			", functionDes=" + functionDes +
			", opIntroduceTxt1=" + opIntroduceTxt1 +
			", opIntroduceImg1=" + opIntroduceImg1 +
			", opIntroduceTxt2=" + opIntroduceTxt2 +
			", opIntroduceImg2=" + opIntroduceImg2 +
			", opIntroduceTxt3=" + opIntroduceTxt3 +
			", opIntroduceImg3=" + opIntroduceImg3 +
			", opIntroduceTxt4=" + opIntroduceTxt4 +
			", opIntroduceImg4=" + opIntroduceImg4 +
			", opIntroduceTxt5=" + opIntroduceTxt5 +
			", opIntroduceImg5=" + opIntroduceImg5 +
			", status=" + status +
			", isDelete=" + isDelete +
			", createTime=" + createTime +
			"}";
	}
}
