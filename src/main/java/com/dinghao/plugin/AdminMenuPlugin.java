package com.dinghao.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dinghao.entity.manage.menu.Menu;

public abstract class AdminMenuPlugin implements Plugin {
	public enum MenuGroup {
		tools
	}

	public static Map<String, List<Menu>> adminMenuList = new HashMap<String, List<Menu>>();

	/**
	 * 增加菜单
	 * 
	 * @param menuGroup
	 * @param name
	 * @param url
	 */
	public void addMenu(MenuGroup menuGroup, String name, String url) {
		//adminMenuList.get(menuGroup.name()).add(new MenuVo(name, url));
	}
}
