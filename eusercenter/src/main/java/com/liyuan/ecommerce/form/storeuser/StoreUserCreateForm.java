package com.liyuan.ecommerce.form.storeuser;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺员工表")
public class StoreUserCreateForm implements Serializable {

	@ApiModelProperty(value = "昵称，可用作登录验证")
	private String nickName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

    @ApiModelProperty(value = "密码")
    private String password;

	@ApiModelProperty(value = "手机号，可用作登录验证")
	private String phone;

	@ApiModelProperty(value = "性别，0未知，1男2女")
	private Integer sex;

	@ApiModelProperty(value = "生日,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "头像")
	private String headerImg;

	@ApiModelProperty(value = "用户角色id")
	private Integer userRoleId;

}