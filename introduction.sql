/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : tomato

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-04-04 22:18:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for introduction
-- ----------------------------
DROP TABLE IF EXISTS `introduction`;
CREATE TABLE `introduction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `functions` text,
  `operates` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of introduction
-- ----------------------------
INSERT INTO `introduction` VALUES ('1', '1', '初级', '1、微信小程序是一种全新的连接用户与服务的方式，它可以在微信内被便捷地获取和传播，同时具有出色的使用体验。\r\n2、选择“小程序”，点击“查看类型区别”可查看不同类型帐号的区别和优势。\r\n3、请填写未注册过公众平台、开放平台、企业号、未绑定个人号的邮箱。\r\n4、点击激活链接后，继续下一步的注册流程。请选择主体类型选择，完善主体信息和管理员信息。', '<p>1、<span style=\"font-size: 0.875rem;\">选择主体类型并完善主体信息</span></p><p><img src=\"https://mp.weixin.qq.com/debug/wxadoc/dev/image/cat/0.jpg?t=2018315\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>2、<span style=\"font-size: 0.875rem;\">企业类型帐号可选择两种主体验证方式。 方式一：需要用公司的对公账户向腾讯公司打款来验证主体身份。打款信息在提交主体信息后可以查看到。 方式二：通过微信认证验证主体身份，需支付300元认证费。认证通过前，小程序部分功能暂无法使用。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_plugin3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>3、<span style=\"font-size: 0.875rem;\">政府、媒体、其他组织类型帐号，必须通过微信认证验证主体身份。认证通过前，小程序部分功能暂无法使用。微信认证入口：登录小程序 - 设置 - 微信认证详情。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_login3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>4、<span style=\"font-size: 0.875rem;\">登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_smart23b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>5、<span style=\"font-size: 0.875rem;\">登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_pay3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p>', '2018-04-04 21:00:12', '2018-04-04 21:55:44', '1', '0');
INSERT INTO `introduction` VALUES ('2', '2', '进阶', '1、微信小程序是一种全新的连接用户与服务的方式，它可以在微信内被便捷地获取和传播，同时具有出色的使用体验。\r\n2、选择“小程序”，点击“查看类型区别”可查看不同类型帐号的区别和优势。\r\n3、请填写未注册过公众平台、开放平台、企业号、未绑定个人号的邮箱。\r\n4、点击激活链接后，继续下一步的注册流程。请选择主体类型选择，完善主体信息和管理员信息。', '<p>1、<span style=\"font-size: 0.875rem;\">选择主体类型并完善主体信息</span></p><p><img src=\"https://mp.weixin.qq.com/debug/wxadoc/dev/image/cat/0.jpg?t=2018315\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>2、<span style=\"font-size: 0.875rem;\">企业类型帐号可选择两种主体验证方式。 方式一：需要用公司的对公账户向腾讯公司打款来验证主体身份。打款信息在提交主体信息后可以查看到。 方式二：通过微信认证验证主体身份，需支付300元认证费。认证通过前，小程序部分功能暂无法使用。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_plugin3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>3、<span style=\"font-size: 0.875rem;\">政府、媒体、其他组织类型帐号，必须通过微信认证验证主体身份。认证通过前，小程序部分功能暂无法使用。微信认证入口：登录小程序 - 设置 - 微信认证详情。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_login3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>4、<span style=\"font-size: 0.875rem;\">登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_smart23b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p><p>5、<span style=\"font-size: 0.875rem;\">登录微信公众平台小程序，进入用户身份- 开发者，新增绑定开发者。个人主体小程序最多可绑定5个开发者，10个体验者。未认证的组织类型小程序最多可绑定10个开发者，20个体验者已认证的小程序最多可绑定20个开发者，40个体验者。</span></p><p><img src=\"https://res.wx.qq.com/open/zh_CN/htmledition/res/img/pic/slider/banner_index_pay3b8804.png\" style=\"max-width:100%;\"><span style=\"font-size: 0.875rem;\"><br></span></p>', '2018-04-04 21:00:12', '2018-04-04 21:55:44', '1', '0');

