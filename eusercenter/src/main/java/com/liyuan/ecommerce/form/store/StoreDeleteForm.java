package com.liyuan.ecommerce.form.store;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺表")
public class StoreDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotEmpty(message = "主键不能为空")
	private String id;

}