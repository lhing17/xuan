package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.Permission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PermissionDaoTest {

    @Resource
    private PermissionDao permissionDao;

    @Test
    @Transactional
    void save() {
        Permission permission = new Permission();
        permission.setName("sys:user:delete");
        Permission saved = permissionDao.save(permission);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(saved.getName(), permission.getName());
    }
}