ALTER TABLE `luobd`.`core_user_info` DROP COLUMN `userInfoId`;

CREATE TABLE `chat_conversation`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `conversationName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


CREATE TABLE `chat_record`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `status` int(11) NOT NULL DEFAULT 0,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createUserId` bigint(20) NULL DEFAULT 0,
  `updateUserId` bigint(20) NULL DEFAULT 0,
  `chatType` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chatModel` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `conversationId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;