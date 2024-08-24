package com.luobd.server.permission;

import com.luobd.server.common.permission.PermissionProperty;
import com.luobd.server.common.permission.PermissionType;

public class PermissionDefine {
    @PermissionProperty(desc = "萝卜丁管家")
    public static final String ROOT = "ROOT";

    @PermissionProperty(desc = "礼金管理", type = PermissionType.MENU,parent = ROOT)
    public static final String CASH_MANAGE = "cash";

    @PermissionProperty(desc = "礼金项目", type = PermissionType.MENU,parent = CASH_MANAGE)
    public static final String CASH_PROJECT = "cash.project";

    @PermissionProperty(desc = "创建项目", type = PermissionType.BUTTON,parent = CASH_PROJECT)
    public static final String PROJECT_CREATE = CASH_PROJECT +"_create";

    @PermissionProperty(desc = "更新项目", type = PermissionType.BUTTON,parent = CASH_PROJECT)
    public static final String PROJECT_UPDATE = CASH_PROJECT +"_update";

    @PermissionProperty(desc = "删除项目", type = PermissionType.BUTTON,parent = CASH_PROJECT)
    public static final String PROJECT_DELETE = CASH_PROJECT +"_delete";

    @PermissionProperty(desc = "礼金清单", type = PermissionType.MENU,parent = CASH_MANAGE)
    public static final String CASH_ITEM = "cash.item";

    @PermissionProperty(desc = "创建清单", type = PermissionType.BUTTON,parent = CASH_ITEM)
    public static final String ITEM_CREATE = CASH_ITEM +"_create";

    @PermissionProperty(desc = "删除清单", type = PermissionType.BUTTON,parent = CASH_ITEM)
    public static final String ITEM_DELETE = CASH_ITEM +"_delete";

    @PermissionProperty(desc = "更新清单", type = PermissionType.BUTTON,parent = CASH_ITEM)
    public static final String ITEM_UPDATE = CASH_ITEM +"_update";

}
