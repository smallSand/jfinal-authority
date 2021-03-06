package com.jayqqaa12.auto.base.system;

import com.jayqqaa12.jbase.jfinal.ext.model.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRoleRes<M extends BaseRoleRes<M>> extends Model<M> implements IBean {

	public static final String ID = "id"; 
	public static final String RES_ID = "res_id"; 
	public static final String ROLE_ID = "role_id"; 
	public M setId(Object id) {
		 return set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setResId(Object resId) {
		 return set("res_id", resId);
	}

	public java.lang.Integer getResId() {
		return get("res_id");
	}

	public M setRoleId(Object roleId) {
		 return set("role_id", roleId);
	}

	public java.lang.Integer getRoleId() {
		return get("role_id");
	}

}
