CREATE TABLE `guide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `type` tinyint(1) DEFAULT '1' COMMENT '1,初级2,进阶',
  `function_des` varchar(500) NOT NULL COMMENT '功能说明',
  `op_introduce_txt1` varchar(1000) DEFAULT NULL COMMENT '操作介绍文本',
  `op_introduce_img1` varchar(500) DEFAULT NULL COMMENT '操作介绍图片url',
  `op_introduce_txt2` varchar(1000) DEFAULT NULL,
  `op_introduce_img2` varchar(500) DEFAULT NULL,
  `op_introduce_txt3` varchar(1000) DEFAULT NULL,
  `op_introduce_img3` varchar(500) DEFAULT NULL,
  `op_introduce_txt4` varchar(1000) DEFAULT NULL,
  `op_introduce_img4` varchar(500) DEFAULT NULL,
  `op_introduce_txt5` varchar(1000) DEFAULT NULL,
  `op_introduce_img5` varchar(500) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0,无效1,有效',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '0未删除1已删除',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='番茄管家使用指南';

INSERT INTO `guide` VALUES (1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (2, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (3, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 2, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (4, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 2, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (5, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 2, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (6, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (7, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (8, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (9, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (10, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (11, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (12, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (13, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (14, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (15, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (16, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (17, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
INSERT INTO `guide` VALUES (18, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibui', 1, 'biubiubiubibuibubiubibuibuibubiubibuibubiubibuibuibubibui', '', '', '', '', '', '', '', '', '', '', 1, 0, '2018-3-23 08:50:26');
