package com.boman.upms.server.controller.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.boman.common.base.BaseController;
import com.boman.common.util.ShiroUtils;
import com.boman.common.util.StringUtil;
import com.boman.common.validator.LengthValidator;
import com.boman.common.validator.NotNullValidator;
import com.boman.upms.common.constant.UpmsResult;
import com.boman.upms.common.constant.UpmsResultConstant;
import com.boman.upms.dao.model.MessageText;
import com.boman.upms.dao.model.MessageTextExample;
import com.boman.upms.rpc.api.MessageTextService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 消息Controller
 * @author Administrator 2019/3/5 17:24
 */
@Controller
@Api(value = "消息管理", description = "消息管理")
@RequestMapping("/manage/message")
public class UpmsMessageController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UpmsMessageController.class);
	
	@Autowired
	private MessageTextService messageTextService;
	
	
	
	/**
	 * 
	 * 查看消息的首页
	 */
	@ApiOperation(value = "消息首页")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		MessageText messageText = new MessageText();
		messageText.setRectName("admin");
		messageText.setTittle("测试发送消息的功能！");
		messageText.setMessage("我在测试测试发送消息的功能，测试发送消息的功能，测试发送消息的功能");
		//messageTextService.PushMessage(messageText);
		return "/manage/message/index.jsp";
	}
	
	/**
	 * 
	 * 查看消息的列表
	 * @param offset
	 * @param limit
	 * @param search
	 * @param sort
	 * @param order
	 */
	@ApiOperation(value = "消息列表")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "0", value = "limit") int limit,
			@RequestParam(required = false, defaultValue = "", value = "search") String search,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		MessageTextExample messageTextExample = new MessageTextExample();
		if(!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			messageTextExample.setOrderByClause(StringUtil.humpToLine(sort) + " " + order);
		}
		if(StringUtils.isNotBlank(search)){
			messageTextExample.or().andTittleLike("%" + search + "%");
		}
		MessageText messageText = new MessageText();
		messageText.setRectName(ShiroUtils.getLoginName());
		messageText.setTittle(search);
		List<MessageText> rows = messageTextService.selectByMessageForOffsetPage(messageText, offset, limit);
		long total = messageTextService.countByMessage(ShiroUtils.getLoginName());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}
	
	/**
	 * 测试新增消息的功能
	 * @param messageText
	 */
	@ApiOperation(value = "测试新增消息")
	@RequiresPermissions("upms:system:read")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(MessageText messageText) {
		ComplexResult result = FluentValidator.checkAll()
				.on(messageText.getType(), new LengthValidator(1, 10, "消息类型"))
				.on(messageText.getMessage(), new NotNullValidator("消息内容"))
				.doValidate().result(ResultCollectors.toComplex());
		
		if(!result.isSuccess()) {		
			return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
		}
		long time = System.currentTimeMillis();
		messageText.setCtime(time);
		int results = messageTextService.creatMessage(messageText);
		return null;
	}
	
	/**
	 * 统计未读消息总数
	 */
	@ApiOperation(value = "统计未读消息总数")
	@RequestMapping(value = "/messageNum", method = RequestMethod.GET)
	@ResponseBody
	public int messageNum() {
		int num = 0;
		try {
			num = messageTextService.getMessageNum(ShiroUtils.getLoginName());
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}
	
	@ApiOperation(value = "查询未读消息")
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	@ResponseBody
	public List<MessageText> notice() {
		List<MessageText> lists = messageTextService.selectByLoginName(ShiroUtils.getLoginName());
		return lists;
	}
	
	@ApiOperation(value = "查看日志详情")
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Integer id,ModelMap mmap){
		MessageText messageText = messageTextService.selectById(id);
		messageText.setStatus(messageText.getStatus() != null ? messageText.getStatus() : "0");
		messageText.setId(id);
		messageText.setRectName(ShiroUtils.getLoginName());
		mmap.put("messageText", messageText);
		return "/manage/message/view.jsp";
	}
}
