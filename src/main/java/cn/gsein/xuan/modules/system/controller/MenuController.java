package cn.gsein.xuan.modules.system.controller;

import cn.gsein.xuan.common.controller.BaseController;
import cn.gsein.xuan.common.entity.JsonResult;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.service.MenuService;
import cn.hutool.core.lang.tree.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 菜单相关控制器
 *
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@RequestMapping("/sys/menu")
@RestController
public class MenuController extends BaseController {

    @Resource
    private MenuService menuService;

    /**
     * 根据登录用户获取菜单树
     */
    @GetMapping("/tree")
    public JsonResult<List<Tree<Optional<Menu>>>> tree() {
        return JsonResult.ok(menuService.getMenuTreeForLoginUser());
    }

    @GetMapping("/list")
    public JsonResult<Page<Menu>> list(Integer current, Integer size, Menu menu) {
        if (current == null || size == null) {
            return JsonResult.error("当前页码和每页条数不能为空！");
        }
        PageRequest pageRequest = PageRequest.of(current - 1, size);
        return JsonResult.ok(menuService.page(menu, pageRequest));
    }

    @GetMapping("/sublist")
    public JsonResult<List<Menu>> sublist(Menu menu) {
        return JsonResult.ok(menuService.list(menu));
    }
}
