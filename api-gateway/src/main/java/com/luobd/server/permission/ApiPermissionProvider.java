package com.luobd.server.permission;

import com.luobd.server.common.permission.PermissionNode;
import com.luobd.server.common.permission.PermissionProvider;

public class ApiPermissionProvider implements PermissionProvider {
    @Override
    public PermissionNode getPermissionTree() {
        return getNodesByClass(PermissionDefine.class, PermissionDefine.ROOT);
    }
}
