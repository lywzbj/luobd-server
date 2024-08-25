SET NAMES utf8mb4;

CREATE TABLE `core_roles`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defaulted` bit(1) NOT NULL DEFAULT b'1',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


CREATE TABLE `core_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `userInfoId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;



CREATE TABLE `core_permissions`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `parentId` bigint(20) NOT NULL,
  `permissionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


CREATE TABLE `core_role_permissions`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `roleId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


