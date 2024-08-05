SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for finance_item
-- ----------------------------
CREATE TABLE `finance_item`  (
                                 `id` bigint(20) NOT NULL COMMENT '主键',
                                 `status` int(11) NOT NULL DEFAULT 0,
                                 `createTime` datetime NULL DEFAULT NULL,
                                 `updateTime` datetime NULL DEFAULT NULL,
                                 `deleted` bit(1) NOT NULL DEFAULT b'0',
                                 `occurTime` datetime NULL DEFAULT NULL,
                                 `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                 `categoryId` bigint(20) NULL DEFAULT NULL,
                                 `categoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                 `amount` decimal(10, 2) NULL DEFAULT NULL,
                                 `createUserId` bigint(20) NULL DEFAULT 0,
                                 `updateUserId` bigint(20) NULL DEFAULT 0,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

CREATE TABLE `core_category`  (
                                  `id` bigint(20) NOT NULL COMMENT '主键',
                                  `status` int(11) NOT NULL DEFAULT 0,
                                  `createTime` datetime NULL DEFAULT NULL,
                                  `updateTime` datetime NULL DEFAULT NULL,
                                  `deleted` bit(1) NOT NULL DEFAULT b'0',
                                  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                  `parentId` bigint(64) NULL DEFAULT NULL,
                                  `categoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                  `createUserId` bigint(20) NULL DEFAULT 0,
                                  `updateUserId` bigint(20) NULL DEFAULT 0,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
