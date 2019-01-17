package com.keyboardman.tool.root.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource(value = {"classpath:commonMessage.properties"}, encoding = "UTF-8")
public class CommonMessage {

	@Value("${addSuccess}")
	private String addSuccess;//添加成功
	@Value("${addFail}")
	private String addFail;//添加失败
	@Value("${updateSuccess}")
	private String updateSuccess;//更新成功
	@Value("${updateFail}")
	private String updateFail;//更新失败
	@Value("${deleteSuccess}")
	private String deleteSuccess;//删除成功
	@Value("${deleteFail}")
	private String deleteFail;//删除失败
	public String getAddSuccess() {
		return addSuccess;
	}
	public void setAddSuccess(String addSuccess) {
		this.addSuccess = addSuccess;
	}
	public String getAddFail() {
		return addFail;
	}
	public void setAddFail(String addFail) {
		this.addFail = addFail;
	}
	public String getUpdateSuccess() {
		return updateSuccess;
	}
	public void setUpdateSuccess(String updateSuccess) {
		this.updateSuccess = updateSuccess;
	}
	public String getUpdateFail() {
		return updateFail;
	}
	public void setUpdateFail(String updateFail) {
		this.updateFail = updateFail;
	}
	public String getDeleteSuccess() {
		return deleteSuccess;
	}
	public void setDeleteSuccess(String deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}
	public String getDeleteFail() {
		return deleteFail;
	}
	public void setDeleteFail(String deleteFail) {
		this.deleteFail = deleteFail;
	}


}
