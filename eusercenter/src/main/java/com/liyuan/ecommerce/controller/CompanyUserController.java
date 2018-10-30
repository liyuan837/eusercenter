package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.condition.companyuser.CompanyUserCondition;
import com.liyuan.ecommerce.form.companyuser.*;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.service.CompanyUserService;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@RestController
@RequestMapping("/companyuser")
@Api(value = "/companyuser", description = "商家用户表")
public class CompanyUserController extends BaseController {

	@Autowired
	private CompanyUserService companyUserService;

	@ApiOperation(value = "查询商家用户表",notes = "根据ID查询商家用户表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<CompanyUserVo> query(@ApiParam(value = "主键", required = true)@RequestParam String id) throws eusercenterException {
		CompanyUserPo po = companyUserService.queryWithValid(id);
		CompanyUserVo vo = CopyUtil.transfer(po, CompanyUserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询商家用户表数量",notes = "查询商家用户表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid CompanyUserQueryForm form) throws eusercenterException {
		CompanyUserCondition condition = CopyUtil.transfer(form, CompanyUserCondition.class);
		int count = companyUserService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询商家用户表列表",notes = "查询商家用户表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<CompanyUserVo>> queryList(@RequestBody@Valid CompanyUserQueryForm form) throws eusercenterException {
		CompanyUserCondition condition = CopyUtil.transfer(form, CompanyUserCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<CompanyUserPo> poList = companyUserService.queryList(condition);
		List<CompanyUserVo> voList = CopyUtil.transfer(poList, CompanyUserVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询商家用户表列表(带分页)",notes = "查询商家用户表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<CompanyUserVo>> queryPageList(@RequestBody@Valid CompanyUserQueryForm form) throws eusercenterException {
		CompanyUserCondition condition = CopyUtil.transfer(form, CompanyUserCondition.class);
		List<CompanyUserVo> voList = new ArrayList<>();
		int count = companyUserService.queryCount(condition);
		if (count > 0) {
			List<CompanyUserPo> poList = companyUserService.queryList(condition);
			voList = CopyUtil.transfer(poList, CompanyUserVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增商家用户表",notes = "新增商家用户表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<CompanyUserVo> add(@RequestBody@Valid CompanyUserCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		CompanyUserPo po = CopyUtil.transfer(form, CompanyUserPo.class);
		po.setAddTime(optTime);
		companyUserService.insert(po);
		CompanyUserVo vo = CopyUtil.transfer(po, CompanyUserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改商家用户表",notes = "修改商家用户表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid CompanyUserUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		CompanyUserPo po = CopyUtil.transfer(form, CompanyUserPo.class);
		po.setOptTime(optTime);
		companyUserService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除商家用户表",notes = "删除商家用户表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid CompanyUserDeleteForm form) throws eusercenterException {
		companyUserService.delete(form.getId());
		return getSuccessResult();
	}

}