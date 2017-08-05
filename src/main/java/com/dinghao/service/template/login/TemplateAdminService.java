package com.dinghao.service.template.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.dinghao.constant.SystemConstant;
import com.dinghao.dao.manage.admin.AdminDao;
import com.dinghao.dao.template.templateadmin.TemplateAdminDao;
import com.dinghao.entity.manage.admin.Admin;
import com.dinghao.entity.manage.role.Role;
import com.dinghao.entity.template.admin.TemplateAdmin;
import com.dinghao.entity.vo.manage.adminvo.AdminVo;
import com.dinghao.entity.vo.manage.PageVo;
import com.dinghao.entity.vo.template.templateadmin.TemplateAdminVo;
import com.dinghao.exception.AuthException;
import com.dinghao.exception.ValidateException;
import com.dinghao.service.manage.role.RoleService;
import com.dinghao.util.AuthUtils;
import com.dinghao.util.PropertyUtils;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Service
public class TemplateAdminService {

	protected final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TemplateAdminDao templateAdminDao;
	@Autowired
	private RoleService roleService;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 添加管理员
	 * 
	 * @param roleIds
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @return Admin
	 */
	/*
	 * public Long addAdmin(TemplateAdmin adminVo, Long[] roleIds) { Date now =
	 * new Date();
	 * adminVo.setPassword(AuthUtils.getPassword(adminVo.getPassword()));
	 * adminVo.setCreateDate(now); try { templateAdminDao.insert(adminVo); //
	 * 添加用户 角色信息表 this.addAdminRoles(adminVo.getId(), roleIds); } catch
	 * (Exception e) { // TODO: handle exception logger.error(e.getMessage()); }
	 * return adminVo.getId(); }
	 */
	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除管理员
	 * 
	 * @param adminId
	 * @return Integer
	 */
	/*
	 * public int deleteAdmin(long adminId) { // 删除角色信息表 int i =
	 * templateAdminDao.deleteAdminRoleByAdminId(adminId); if (i > 0) { //
	 * 删除管理员表 templateAdminDao.deleteByPrimaryKey(adminId); } return i; }
	 */
	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改管理员资料
	 * 
	 * @param adminId
	 * @param name
	 * @param password
	 * @param status
	 * @return Admin
	 * @throws AuthException
	 * @throws ValidateException
	 */
	/*
	 * @CacheEvict(value ="getRolesByadminId", allEntries = true) public void
	 * updateAdminByAmdinId(AdminVo adminVo, Long[] roleIds) throws
	 * AuthException, ValidateException { if
	 * (StringUtils.isNotBlank(adminVo.getPassword())) {
	 * adminVo.setPassword(AuthUtils.getPassword(adminVo.getPassword())); }
	 * adminVo.setModifyDate(new Date()); int result =
	 * templateAdminDao.updateByPrimaryKeySelective(adminVo); // 更新成功 if (result
	 * == 1) { // 清除原有的数据
	 * templateAdminDao.deleteAdminRoleByAdminId(adminVo.getId());
	 * this.addAdminRoles(adminVo.getId(), roleIds); } else { throw new
	 * ValidateException("修改失败！"); } }
	 */
	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 管理员登陆
	 * 
	 * @param email
	 * @param password
	 * @param request
	 * @throws IOException
	 */
	public void adminLogin(String userName, String password,
			HttpServletRequest request) throws AuthException, IOException {
		TemplateAdmin admin = templateAdminDao.getAdminByName(userName);
		if (admin == null) {
			throw new AuthException("用户名或密码错误");
		}
		String loginPassword = AuthUtils.getPassword(password);
		if (loginPassword.equals(admin.getPassword())) {
			HttpSession session = request.getSession();
			admin.setPassword("");
			session.setAttribute(SystemConstant.TEMPLATE_ADMIN, admin);// admin放入Session
			session.setAttribute(SystemConstant.CONFIG_V,
					PropertyUtils.getValue("dinghao.config_v"));// JS ,Css版本
		} else {
			throw new AuthException("用户名或密码错误");
		}
	}

	/**
	 * 通过Id获得指定管理员资料
	 */
	public TemplateAdmin getAdminById(long adminId) {
		TemplateAdmin admin = templateAdminDao.selectByPrimaryKey(adminId);
		// 获取rols对象集
		List<Role> roles = roleService.getRolesByadminId(adminId);
		List<Long> list = new ArrayList<Long>();
		for (Role role : roles) {
			list.add(role.getId());
		}
		admin.setRoles(list);
		return admin;
	}

	public boolean usernameExists(String username) {
		TemplateAdminVo adminVo = new TemplateAdminVo();
		adminVo.setUsername(username);
		return templateAdminDao.selectByStatementCount(adminVo)>0?true:false;
	}

	public void deleteByPrimaryKey(long id) {
		templateAdminDao.deleteByPrimaryKey(id);
	}

	/**
	 * 获得所有管理员的分页数据
	 * 
	 * @param offset
	 * @param rows
	 * @return List<Admin>
	 */
	/*
	 * public List<TemplateAdmin> getAllList(long offset, long rows) { return
	 * templateAdminDao.getAllList(offset, rows); }
	 */
	/*
	*//**
	 * 获得所有管理员的数量
	 * 
	 * @return Integer
	 */
	/*
	 * public int getAllListCount() { return templateAdminDao.getAllListCount();
	 * }
	 *//**
	 * 获得所有管理员的分页
	 * 
	 * @param Integer
	 * @return PageVo<Admin>
	 */
	/*
	 * public PageVo<Admin> getAllListPage(int pageNum) { PageVo<Admin> pageVo =
	 * new PageVo<Admin>(pageNum); pageVo.setRows(20); List<Admin> list = this
	 * .getAllList(pageVo.getOffset(), pageVo.getRows()); pageVo.setList(list);
	 * pageVo.setCount(this.getAllListCount()); return pageVo; }
	 *//**
	 * 通过用户名获得管理员资料
	 * 
	 * @param email
	 * @return Admin
	 */
	/*
	 * public Admin getAdminByName(String name) { return
	 * templateAdminDao.getAdminByName(name); }
	 * 
	 * public long getSuperAdminId() { Admin admin =
	 * getAdminByName(PropertyUtils.getValue("dinghao.admin")); return
	 * admin.getId(); }
	 * 
	 * public boolean usernameExists(String username) { Admin admin =
	 * templateAdminDao.getAdminByName(username); if (admin != null) { return
	 * true; } else { return false; }
	 * 
	 * }
	 * 
	 * public void addAdminRoles(Long id, Long[] roleIds) { for (Long long1 :
	 * roleIds) { templateAdminDao.addAdminRoles(id, long1); }
	 * 
	 * }
	 * 
	 * public void updatePasswordByAmdinId(Long id, String text) {
	 * templateAdminDao.updatePasswordByAmdinId(id,text); }
	 */
}
