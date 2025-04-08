package com.entity.view;

import com.entity.JieyuejiluEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 借阅记录
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jieyuejilu")
public class JieyuejiluView extends JieyuejiluEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 状态的值
		*/
		private String jieyuejiluValue;



		//级联表 tushu
			/**
			* 图书编号
			*/
			private String tushuUuidNumber;
			/**
			* 图书名称
			*/
			private String tushuName;
			/**
			* 图书封面
			*/
			private String tushuPhoto;
			/**
			* 图书类型
			*/
			private Integer tushuTypes;
				/**
				* 图书类型的值
				*/
				private String tushuValue;
			/**
			* 书架
			*/
			private Integer shujiaTypes;
				/**
				* 书架的值
				*/
				private String shujiaValue;
			/**
			* 图书库存
			*/
			private Integer tushuKucunNumber;
			/**
			* 逻辑删除
			*/
			private Integer tushuDelete;
			/**
			* 图书预览
			*/
			private String tushuContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

	public JieyuejiluView() {

	}

	public JieyuejiluView(JieyuejiluEntity jieyuejiluEntity) {
		try {
			BeanUtils.copyProperties(this, jieyuejiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 状态的值
			*/
			public String getJieyuejiluValue() {
				return jieyuejiluValue;
			}
			/**
			* 设置： 状态的值
			*/
			public void setJieyuejiluValue(String jieyuejiluValue) {
				this.jieyuejiluValue = jieyuejiluValue;
			}
















				//级联表的get和set tushu
					/**
					* 获取： 图书编号
					*/
					public String getTushuUuidNumber() {
						return tushuUuidNumber;
					}
					/**
					* 设置： 图书编号
					*/
					public void setTushuUuidNumber(String tushuUuidNumber) {
						this.tushuUuidNumber = tushuUuidNumber;
					}
					/**
					* 获取： 图书名称
					*/
					public String getTushuName() {
						return tushuName;
					}
					/**
					* 设置： 图书名称
					*/
					public void setTushuName(String tushuName) {
						this.tushuName = tushuName;
					}
					/**
					* 获取： 图书封面
					*/
					public String getTushuPhoto() {
						return tushuPhoto;
					}
					/**
					* 设置： 图书封面
					*/
					public void setTushuPhoto(String tushuPhoto) {
						this.tushuPhoto = tushuPhoto;
					}
					/**
					* 获取： 图书类型
					*/
					public Integer getTushuTypes() {
						return tushuTypes;
					}
					/**
					* 设置： 图书类型
					*/
					public void setTushuTypes(Integer tushuTypes) {
						this.tushuTypes = tushuTypes;
					}


						/**
						* 获取： 图书类型的值
						*/
						public String getTushuValue() {
							return tushuValue;
						}
						/**
						* 设置： 图书类型的值
						*/
						public void setTushuValue(String tushuValue) {
							this.tushuValue = tushuValue;
						}
					/**
					* 获取： 书架
					*/
					public Integer getShujiaTypes() {
						return shujiaTypes;
					}
					/**
					* 设置： 书架
					*/
					public void setShujiaTypes(Integer shujiaTypes) {
						this.shujiaTypes = shujiaTypes;
					}


						/**
						* 获取： 书架的值
						*/
						public String getShujiaValue() {
							return shujiaValue;
						}
						/**
						* 设置： 书架的值
						*/
						public void setShujiaValue(String shujiaValue) {
							this.shujiaValue = shujiaValue;
						}
					/**
					* 获取： 图书库存
					*/
					public Integer getTushuKucunNumber() {
						return tushuKucunNumber;
					}
					/**
					* 设置： 图书库存
					*/
					public void setTushuKucunNumber(Integer tushuKucunNumber) {
						this.tushuKucunNumber = tushuKucunNumber;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getTushuDelete() {
						return tushuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setTushuDelete(Integer tushuDelete) {
						this.tushuDelete = tushuDelete;
					}
					/**
					* 获取： 图书预览
					*/
					public String getTushuContent() {
						return tushuContent;
					}
					/**
					* 设置： 图书预览
					*/
					public void setTushuContent(String tushuContent) {
						this.tushuContent = tushuContent;
					}




				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}



}
