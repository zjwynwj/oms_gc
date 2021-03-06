package com.dinghao.action.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dinghao.entity.manage.admin.Admin;
import com.dinghao.entity.manage.menu.Menu;
import com.dinghao.entity.template.business.common.CommResponse;
import com.dinghao.entity.vo.JsonVo;
import com.dinghao.entity.vo.manage.adminvo.AdminVo;
import com.dinghao.entity.vo.manage.menuvo.MenuVo;
import com.dinghao.service.manage.menu.MenuService;
import com.dinghao.service.manage.role.RoleService;
import com.dinghao.util.AuthUtils;

/**
 * 管理员action
 * 
 * @author zihan
 * 
 */
@Controller
@RequestMapping("/manage/admin")
public class ManageAdminAction extends ManageBaseAction {
	@Autowired
	RoleService roleService;
	@Autowired
	MenuService menuService;

	/**
	 * 进入添加admin页面
	 * 
	 */
	@RequestMapping(value = "/add.jhtml", method = RequestMethod.GET)
	public String addUser(ModelMap modelMap) {
		modelMap.put("roles", roleService.getAllRole());
		return "manage/ftls/admin/add";
	}

	/**
	 * 
	 * 进入管理员管理页面
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", adminService.getAllListPage(pageNum));
		return "manage/ftls/admin/manage";
	}

	/**
	 * 添加Admin
	 * 
	 */
	@RequestMapping(value = "/addNew.jhtml", method = RequestMethod.POST)
	public String addNewUser(AdminVo adminVo, Long[] roleIds,
			ModelMap modelMap, HttpServletRequest request) {
		if (StringUtils.isNotBlank(checkAdminVo(adminVo))) {
			modelMap.put("error", checkAdminVo(adminVo));
		}
		try {
			// 检测校验结果
			adminService.addAdmin(adminVo, roleIds);
			modelMap.put("success", "操作成功");
		} catch (Exception e) {
			modelMap.put("error", e.getMessage());
		}
		modelMap.put("roles", roleService.getAllRole());
		return "manage/ftls/admin/add";
	}

	/**
	 * 
	 * 方法名: checkAdminVo
	 * <p/>
	 * 方法描述: (监测用户信息正确性)
	 * <p/>
	 * 修改时间：2015年12月7日 下午5:06:19
	 * 
	 * @param adminVo
	 * @return 参数说明 返回类型 String 返回类型
	 * @throws
	 */
	private String checkAdminVo(AdminVo adminVo) {
		StringBuilder builder = new StringBuilder();
		if (adminService.usernameExists(adminVo.getUsername())) {
			builder.append("管理员名称不能重复; ");
		}
		if (StringUtils.isBlank(adminVo.getUsername())) {
			builder.append("管理员名称不能为空; ");
		}
		if (StringUtils.isBlank(adminVo.getPassword())) {
			builder.append("管理员密码不能为空; ");
		} else if (adminVo.getPassword().length() < 6) {
			builder.append("密码不能小于6位; ");
		} else if (adminVo.getPassword().length() > 16) {
			builder.append("密码不能大于16位; ");
		}
		return builder.toString();
	}

	/**
	 * 进入管理员列表页面
	 * 
	 */
	@RequestMapping(value = "/page.jhtml", method = RequestMethod.GET)
	public String allList(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			ModelMap modelMap) {
		modelMap.put("pageVo", adminService.getAllListPage(pageNum));
		return "manage/ftls/admin/all";
	}

	/**
	 * 进入单个admmin页面
	 * 
	 */
	@RequestMapping(value = "/update.jhtml", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "id", defaultValue = "0") long id,
			ModelMap modelMap, HttpServletRequest request) {
		Admin admin = adminService.getAdminById(id);
		modelMap.put("roles", roleService.getAllRole());
		modelMap.put("admin", admin);
		return "manage/ftls/admin/update";
	}

	/**
	 * 进入admin密码页面
	 * 
	 */
	@RequestMapping(value = "/updatePassword.jhtml", method = RequestMethod.GET)
	public String updatePassword(ModelMap modelMap, HttpServletRequest request) {
		Admin admin = this.getAdmin(request);
		modelMap.put("admin", admin);
		return "manage/ftls/admin/updatePassword";
	}

	@ResponseBody
	@RequestMapping(value = "/updatePassword.jhtml", method = RequestMethod.POST)
	public JsonVo<String> updatePassword(
			@RequestParam(value = "password") String password,
			String OldPassword, HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(password)
					|| StringUtils.isBlank(OldPassword)) {
				json.getErrors().put("password", "密码不能为空");
			}
			if (password.length() < 6) {
				json.getErrors().put("password", "密码不能小于6位数");
			}
			if (password.length() > 18) {
				json.getErrors().put("password", "密码不能大于18位数");
			}
			Admin admin = this.getAdmin(request);
			admin = adminService.getAdminByName(admin.getUsername());
			if (!AuthUtils.getPassword(OldPassword).equals(admin.getPassword())) {
				json.getErrors().put("password", "原始密码错误");
			}
			// 检测校验结果
			validate(json);
			adminService.updatePasswordByAmdinId(admin.getId(),
					AuthUtils.getPassword(password));
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 
	 * 方法名: myPage<br/>
	 * 方法描述: 首次加载页面<br/>
	 * 修改时间：2015年12月11日 下午2:45:46
	 * 
	 * @param id
	 * @param modelMap
	 * @param request
	 * @return 参数说明 返回类型 String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/myPage.jhtml", method = RequestMethod.GET)
	public String myPage(
			@RequestParam(value = "menuParentId", defaultValue = "-1") Long menuParentId,
			ModelMap modelMap, HttpServletRequest request) {
		Admin admin = this.getAdmin(request);
		List<Menu> menusParent = new ArrayList<Menu>();
		// 内置管理员
		if (admin.getId() == 1l) {
			// 获取所有导航
			MenuVo menuVo = new MenuVo();
			menuVo.setDelFlag(null);
			menuVo.setMenuType(1l);
			menuVo.setRows(Integer.MAX_VALUE);
			menusParent = menuService.findParentMenu(menuVo).getList();
		} else {
			// 获取父节点
			menusParent = menuService.findParentMenuByAdminId(admin.getId()
					.intValue());
		}
		// 依据父获取所有子节点
		if (menuParentId < 0) {
			menuParentId = menusParent.get(0).getId();
		}
		List<Menu> menusChildren = menuService.findChildrenMenu(menuParentId
				.intValue(),admin.getId());
		request.getSession().removeAttribute("menuParentId");
		request.getSession().setAttribute("menuParentId", menuParentId);
		return "redirect:" + menusChildren.get(0).getHref();
	}

	/**
	 * 修改指定的admin资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/updateJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> updateAdmin(AdminVo adminVo, Long[] roleIds,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		// 数据校验
		if (StringUtils.isBlank(adminVo.getPassword())) {
			json.getErrors().put("error", "管理员密码不能为空");
		} else if (adminVo.getPassword().length() < 6) {
			json.getErrors().put("error", "密码不能小于6位");
		} else if (adminVo.getPassword().length() > 16) {
			json.getErrors().put("error", "密码不能大于16位");
		}
		try {
			// 检测校验结果
			validate(json);
			adminService.updateAdminByAmdinId(adminVo, roleIds);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * 删除管理员
	 * 
	 */

	@ResponseBody
	@RequestMapping(value = "/deleteJson.jhtml", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "id") long id,
			HttpServletRequest request) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			adminService.deleteAdmin(id);
			json.setResult(true);
			json.setMsg("操作成功！");
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	


		
}
