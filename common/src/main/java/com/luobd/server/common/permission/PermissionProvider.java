package com.luobd.server.common.permission;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PermissionProvider {



    PermissionNode getPermissionTree();




    default List<PermissionNode> getNodesByClass(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<PermissionNode> nodes = Lists.newArrayList();
        for (Field field : fields) {
            PermissionProperty annotation = field.getAnnotation(PermissionProperty.class);
            if (annotation != null) {
                String desc = annotation.desc();
                String parent = annotation.parent();
                PermissionType type = annotation.type();
                Object o;
                try {
                    o = field.get(clazz);
                    if (o instanceof String) {
                        PermissionNode node = new PermissionNode();
                        node.setDesc(desc);
                        node.setParent(parent);
                        node.setType(type.name());
                        nodes.add(node);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    default PermissionNode   getNodesByClass(Class<?> clazz,String root) {
        return treeNodeByClass(getNodesByClass(clazz),root);
    }




    default PermissionNode treeNodeByClass(List<PermissionNode> nodes,String root) {
        Optional<PermissionNode> first = nodes.stream().filter(node -> node.getDesc().equals(root)).findFirst();
        if (!first.isPresent()) {
            return null;
        }
        Map<String, List<PermissionNode>> map = nodes.stream().collect(Collectors.groupingBy(PermissionNode::getParent));
        for (PermissionNode node : nodes) {
            if (map.containsKey(node.getDesc())) {
                node.setChildren(map.get(node.getDesc()));
            }
        }
        return first.get();
    }






}
