SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yuaner_user
-- ----------------------------
DROP TABLE IF EXISTS `yuaner_user`;
CREATE TABLE `yuaner_user`
(
  `user_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
`user_name` varchar(50) CHARACTER
SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
`user_password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '密码（加密后）',
`user_email` varchar(255)CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
`user_info_id` int UNSIGNED NULL DEFAULT NULL COMMENT '对应信息表的ID',
`create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(`user_id`)
USING BTREE,
UNIQUE INDEX `user_email`(`user_email`)
USING BTREE COMMENT '唯一',
UNIQUE INDEX `user_name`(`user_name`)
USING BTREE COMMENT '唯一',
INDEX `连接信息`(`user_info_id`)
USING BTREE,
CONSTRAINT `连接信息` FOREIGN KEY(`user_info_id`)REFERENCES `yuaner_user_info`(`user_info_id`)ON DELETE RESTRICT ON
UPDATE RESTRICT
)ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
