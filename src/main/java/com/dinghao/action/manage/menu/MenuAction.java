package com.dinghao.action.manage.menu;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dinghao.action.manage.ManageBaseAction;
import com.dinghao.entity.manage.menu.Menu;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.manage.menuvo.MenuVo;
import com.dinghao.exception.FolderNotFoundException;
import com.dinghao.service.manage.menu.MenuService;

@Controller
@RequestMapping("/manage/menu")
public class MenuAction extends ManageBaseAction {
	@Autowired
	MenuService menuService;

	/**
	 * 
	 * 方法名: index<br/>
	 * 方法描述: (导航栏新增)<br/>
	 * 修改时间：2015年12月9日 上午9:42:43
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws FolderNotFoundException
	 *             参数说明 返回类型 String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add.jhtml", method = RequestMethod.GET)
	public String add(HttpServletRequest request, ModelMap modelMap,
			Long parentId) throws FolderNotFoundException {
		MenuVo menuVo = new MenuVo();
		menuVo.setOffset(0);
		menuVo.setRows(Integer.MAX_VALUE);
		modelMap.put("parentId", parentId);
		menuVo.setMenuType(1l);
		modelMap.put("parentList", menuService.findParentMenu(menuVo));
		menuVo.setMenuType(0l);
		modelMap.put("menuList", menuService.findParentMenu(menuVo));
		return "/manage/ftls/menu/add";
	}
	
	@RequestMapping(value = "/add_menu.jhtml", method = RequestMethod.GET)
	public String addMenu(HttpServletRequest request, ModelMap modelMap,
			Long parentId) throws FolderNotFoundException {
		MenuVo menuVo = new MenuVo();
		menuVo.setOffset(0);
		menuVo.setRows(Integer.MAX_VALUE);
		modelMap.put("parentId", parentId);
		modelMap.put("parentList", menuService.findParentMenu(menuVo));
		return "/manage/ftls/menu/addMenu";
	}
	/**
	 * 
	 * 方法名: list
	 * <p/>
	 * 方法描述: (导航栏目设定，目前支持二级)
	 * <p/>
	 * 修改时间：2015年12月9日 上午11:02:42
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws FolderNotFoundException
	 *             参数说明 返回类型 String 返回类型
	 * @throws
	 */

	@RequestMapping(value = "/list.jhtml", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap modelMap,
			MenuVo menuVo) throws FolderNotFoundException {
		menuVo.setMenuType(1l);
		modelMap.put("parentList", menuService.findParentMenu(menuVo));
		return "/manage/ftls/menu/list";
	}

	@ResponseBody
	@RequestMapping(value = "/addJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> updateAdmin(MenuVo menuVo, HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		if(menuVo.getParentId()==null||menuVo.getParentId()<0){
			menuVo.setParentId(menuVo.getGrandparents());
		}
		String checkResult = checkMenu(menuVo);
		// 校验数据
		if (StringUtils.isNotBlank(checkResult)) {
			json.setResult(false);
			json.setMsg(checkResult);
		}
		try {
			// 检测校验结果
			validate(json);
			menuVo.setCreateBy(super.getAdmin(request).getId());
			menuService.insertSelective(menuVo);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			logger.error(e.getMessage());
			json.setMsg(e.getMessage());
		}
		return json;
	}

	private String checkMenu(MenuVo menuVo) {
		StringBuffer stringBuffer = new StringBuffer();
		if (StringUtils.isBlank(menuVo.getName())) {
			stringBuffer.append("导航栏名称不能为空");
		}
		return stringBuffer.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/deleteJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> delete(Long id, HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		// 校验数据
		if (id == null) {
			json.setResult(false);
			json.setMsg("操作有误，请重新操作");
		}
		try {
			// 检测校验结果
			validate(json);
			menuService.deleteByPrimaryKey(id);
			json.setResult(true);
			json.setMsg("操作成功");
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	// 修改导航
	@RequestMapping(value = "/update.jhtml", method = RequestMethod.GET)
	public String update(HttpServletRequest request, ModelMap modelMap,
			MenuVo menuVo){
		// 获取menu对象
		menuVo.setDelFlag(null);
		menuVo.setRows(Integer.MAX_VALUE);
		Menu menu = menuService.selectByPrimaryKey(menuVo);
		modelMap.put("menuOnly", menu);
		menuVo.setMenuType(1l);
		modelMap.put("parentList", menuService.findParentMenu(menuVo));
		return "/manage/ftls/menu/update";
	}

	// 修改
	@ResponseBody
	@RequestMapping(value = "/updateJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> update(MenuVo menuVo, HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		String checkResult = checkMenu(menuVo);
		// 校验数据
		if (StringUtils.isNotBlank(checkResult)) {
			json.setResult(false);
			json.setMsg(checkResult);
		}
		try {
			// 检测校验结果
			validate(json);
			menuVo.setModifyBy(super.getAdmin(request).getId());
			menuService.updateByPrimaryKey(menuVo);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}
