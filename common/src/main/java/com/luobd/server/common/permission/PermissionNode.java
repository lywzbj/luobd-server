package com.luobd.server.common.permission;

import lombok.Data;

import java.util.List;

@Data
public class PermissionNode {


    private String desc;


    private String parent;


    private String type;


    private List<PermissionNode> children;


}
