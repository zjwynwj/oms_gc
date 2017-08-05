package com.dinghao.action.template.business.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dinghao.action.template.BaseAction;
import com.dinghao.entity.vo.template.business.goods.GdsAtvVo;
import com.dinghao.service.template.business.goods.GdsAtvService;

/**
  * @ClassName: GdsAtvAction
  * @Description: TODO  商品可选属性值管理 控制层
  * @author helong 
  * @date 2016年1月5日 下午4:06:03
  * @version V1.0
  *
 */
@Controller
@RequestMapping("/template")
public class GdsAtvAction extends BaseAction{
	@Autowired
	private GdsAtvService atvService;
	
	//添加可选属性值
	@RequestMapping(value = {"/gdsAtv/addAtv.jhtml"})
	public void addAtv(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAtvVo gdsAtvVo) throws Exception {
        this.returnData(request, response, atvService.addGdsAtv(gdsAtvVo));
    }
	
	//修改可选属性值
	@RequestMapping(value = {"/gdsAtv/modAtv.jhtml"})
	public void modAtv(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAtvVo gdsAtvVo) throws Exception {
        this.returnData(request, response, atvService.modGdsAtv(gdsAtvVo));
    }
	
	//删除可选属性值
	@RequestMapping(value = {"/gdsAtv/delAtv.jhtml"})
	public void delAtv(HttpServletRequest request ,HttpServletResponse response ,
			ModelMap modelMap, GdsAtvVo gdsAtvVo) throws Exception {
        this.returnData(request, response, atvService.delGdsAtv(gdsAtvVo));
    }
}
