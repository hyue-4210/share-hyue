/*
 Navicat Premium Data Transfer

 Source Server         : bl-pro
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 212.64.1.36:3306
 Source Schema         : sharera

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 27/11/2019 10:19:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dedut_point_order
-- ----------------------------
DROP TABLE IF EXISTS `dedut_point_order`;
CREATE TABLE `dedut_point_order`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `projectID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商场id',
  `deductPoint` int(255) DEFAULT NULL COMMENT '抵扣积分数额',
  `orderNum` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方订单号',
  `deductMoney` int(255) DEFAULT NULL COMMENT '抵扣金额',
  `serviceType` int(10) DEFAULT NULL COMMENT '1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿',
  `deviceID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goThirdUrl
-- ----------------------------
DROP TABLE IF EXISTS `goThirdUrl`;
CREATE TABLE `goThirdUrl`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `thirdUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appId` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小程序appid',
  `baolongId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宝龙id',
  `type` int(10) DEFAULT NULL,
  `device_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_yum_logs
-- ----------------------------
DROP TABLE IF EXISTS `member_yum_logs`;
CREATE TABLE `member_yum_logs`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `orderNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单编号',
  `startTime` datetime(0) DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime(0) DEFAULT NULL COMMENT '结束时间',
  `equipmentNo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备编号',
  `placeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '网点名称',
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `memberLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员等级',
  `levelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员等级名称',
  `usedTimes` int(50) DEFAULT NULL COMMENT '使用次数',
  `orderMoney` int(255) DEFAULT NULL COMMENT '订单金额',
  `consumePoints` int(255) DEFAULT NULL COMMENT '消耗积分',
  `earnPoints` int(255) DEFAULT NULL COMMENT '获得积分',
  `payMoney` int(255) DEFAULT NULL COMMENT '支付金额',
  `projectId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商场id',
  `type` int(20) DEFAULT NULL COMMENT '1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿',
  `orderType` int(10) DEFAULT NULL COMMENT '订单类型 1使用订单 2抵扣劵订单',
  `rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权益规则',
  `exchangeMoney` int(255) DEFAULT NULL COMMENT '消耗积分所兑换的金额 ',
  `usedTime` int(255) DEFAULT NULL COMMENT '使用时长',
  INDEX `id_index`(`id`, `orderNo`, `type`, `orderType`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for messagesetting
-- ----------------------------
DROP TABLE IF EXISTS `messagesetting`;
CREATE TABLE `messagesetting`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `partnerName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合作方名称',
  `picUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '展示图片',
  `status` int(10) DEFAULT NULL COMMENT '1开启  2禁用',
  `projectId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商场id',
  `ruleMoney` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规定金额兑换数值',
  `type` int(10) DEFAULT NULL COMMENT '1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息设置' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for right_rules
-- ----------------------------
DROP TABLE IF EXISTS `right_rules`;
CREATE TABLE `right_rules`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ids` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `projectId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商场id',
  `createTime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `createPeople` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime(0) DEFAULT NULL COMMENT '更改时间',
  `updatePeople` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更改人',
  `effectiveStartTime` datetime(0) DEFAULT NULL COMMENT '生效开始时间',
  `effectiveEndTime` datetime(0) DEFAULT NULL COMMENT '生效结束时间',
  `topPoint` int(255) DEFAULT NULL COMMENT '积分上线',
  `memberType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员类型',
  `postPoint` int(255) DEFAULT NULL COMMENT '消费积分',
  `getMoney` int(255) DEFAULT NULL COMMENT '获得金额',
  `postMoney` int(255) DEFAULT NULL COMMENT '消费金额',
  `getPoint` int(255) DEFAULT NULL COMMENT '获得积分',
  `submitType` int(255) DEFAULT NULL COMMENT '1提交 2暂存',
  `status` int(10) DEFAULT NULL COMMENT '1待审核 2审核通过 3已驳回 4已发布 5已下架 6审核中 7暂存',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注 审核人',
  `type` int(5) DEFAULT NULL COMMENT '1云纸 2乐摩吧 3来电 4熊猫溜娃 5摩伞 6黄小鹿',
  `backReason` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '驳回理由',
  `isCommon` int(128) DEFAULT NULL COMMENT '是否通用 1是 2不是',
  `nextAutor` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '待审核人',
  `isAddPoint` int(255) DEFAULT NULL COMMENT '是否增加积分 1是 2否',
  `memberTypeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '会员名称'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权益设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for table_key_pair
-- ----------------------------
DROP TABLE IF EXISTS `table_key_pair`;
CREATE TABLE `table_key_pair`  (
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密钥系统类型',
  `SECRET_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密钥id',
  `SECRET_KEY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密钥key'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '密钥配置表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/*共享平台基础数据*/
INSERT INTO `messagesetting` VALUES ('0090ac71dd0245f0bb6500362f6b74be', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF441231', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('019723c27a22421e803278de5d7c2796', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702ec49066012ed7a1aac1225e', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('01e2311066b6417d9641cfd7bed716b3', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444232', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('05169c5960954995ace3ec14e9a8e8d4', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C0689QWERTY24O89045EB123456E002E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('061c1b6268fc4ce6b005deb79093ba46', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702db81ec3012dbca135f20c6a', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('06b85c533aec486cbb3c796417c4e65d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '4028347027a51b8f0127b336b2a30003', '323', 1);
INSERT INTO `messagesetting` VALUES ('07114d3373d74e23949770a57f892445', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702fda2365012fdd1bd6e108ee', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('071177c640124a408b2cd8fb9c21e8fd', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a432c1612345', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('083aebd7b7254f8aaa8966735dc0c41d', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF441231', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('08b77f53b4b94bc791f2febf73b023b1', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '542gw502d453f27012d4ef49eb58o07F', '', 6);
INSERT INTO `messagesetting` VALUES ('09189f35a6e94bcca692cfd598b7060b', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444241', '323', 1);
INSERT INTO `messagesetting` VALUES ('09921db9453c4990b7768c5bcafcd0b1', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '1C7F6A3394524EA4ADFEBACFB92E570D', '', 6);
INSERT INTO `messagesetting` VALUES ('09985a9774d745329bb53c58497b26c0', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '542gw502d453f27012d4ef49eb58o07F', '323', 1);
INSERT INTO `messagesetting` VALUES ('0aa3ec53451d4a90a74d6348785a8abe', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702b486353012b4ce115170629', '', 6);
INSERT INTO `messagesetting` VALUES ('0c0bffa7747444f5ba5803454d9edb03', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '4028347027a51b8f0127b336b2a3000d', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('0c4c8111e59e4757ae73f5bb974437fb', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702b8b7fc8012b99606d320f1c', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('0c61708defc74ce1a77b87d0612c995f', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '4028347027a51b8f0127b336b2a30005', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('0cc8b47a11394ee1bf63928814cb8cb3', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C0689QWERTY24123480EBEB52D6E002F', '', 6);
INSERT INTO `messagesetting` VALUES ('0cdb4c3a0ef144399958da6bfd9dd178', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a433cd3100d5', '102,4,6', 4);
INSERT INTO `messagesetting` VALUES ('0ce20918c6ad4ae78653b4a318494cfe', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C0689QWERTY24123480EBEB52D6E002E', '', 6);
INSERT INTO `messagesetting` VALUES ('0eaa6e9e2a584b1e975d93c970fbae9f', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '1C7F6A3394524EA4ADFEBACFB92E570D', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('0fee10ca72c84bec9372af71a7adfaf0', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444258', '323', 1);
INSERT INTO `messagesetting` VALUES ('10420bc76d514e5894fa20923b3a4f9b', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C068924639924123480EBEB52D6E002E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('11c0dd1b5ac04afba95285d6ddc18738', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702b486353012b4ce115170629', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('138d80d15e55431c82b486993f0d9961', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444239', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('1549af809a2b4520836329bd2ee07fb3', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 1, '6D7E1C7AFAFB43E986670A81CF444253', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('15ea03020eb04dada781d1204878c72d', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444255', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('19342c8e218b4f2a8815c4f6f27e5b81', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '16084DA3912DDAE4E050007F01005603', '323', 1);
INSERT INTO `messagesetting` VALUES ('1a29383edf4a4c28af17b2ea98a35aff', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444250', '323', 1);
INSERT INTO `messagesetting` VALUES ('1b4863f9c6e84bc497668f4a29411286', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C0689QWERTY24O89045EB123456E002E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('1e158763fc2f427b8feb65d1ccd0b8b1', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a432c1612345', '', 6);
INSERT INTO `messagesetting` VALUES ('1f41afe945484279bc281ce05d44db0f', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7F6A3394246EA4ADFEBACFB92E808E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('1fdcbcbc82084912b1ffb9fdb2285b5b', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C060624639924CDEA80EBEB52D6E002B', '', 6);
INSERT INTO `messagesetting` VALUES ('206090479d3c47a7b14cdfea6bd501f0', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702b486353012b4ce115170629', '323', 1);
INSERT INTO `messagesetting` VALUES ('22859006f29b46759f412abb1ca2ac18', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'qpeq_4028347027a51b8f0127b336b2a30002', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('22f476cd4fbe4106a77acb3e8b4f8a61', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF436499', '323', 1);
INSERT INTO `messagesetting` VALUES ('23532805eb6a4a538d1a4014d4ad8b0c', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702d453f27012d4ef49eb508c9', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('245b735ab8f54df194b3514133d3d128', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C0689QWERTY24123480EBEB52D6E002F', '323', 1);
INSERT INTO `messagesetting` VALUES ('24a080c1fb22429b84d91ce696893aea', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF441231', '0,12300', 1);
INSERT INTO `messagesetting` VALUES ('24dfca09f84d4521a0891566e40018c0', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C0689QWERTY24123480EBEB52D6E002E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('26a633ea45f54e55b62638b0768addf6', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a433647400aa', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('28c96ae058e84cad96b0e1ff9f6b22f8', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444260', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('28d94d617fe241d18c262dc8860d595a', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444232', '323', 1);
INSERT INTO `messagesetting` VALUES ('296d8ac63ec54fba9b016f246bb37339', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a435d5c30126', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('2c7b3de8494043b5a6b7d204290a94bf', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a4316b830001', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('314ea652bad1479ea35a4c2f4e85ca9a', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a433647400ab', '323', 1);
INSERT INTO `messagesetting` VALUES ('33afa2ac13b046dea167cd2f9408d574', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444255', '323', 1);
INSERT INTO `messagesetting` VALUES ('33dea0e47b704463951286830bf2d09c', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '4028347027a51b8f0127b336b2a30006', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('34e8ccd69a714b73b61f543f61c9157c', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444239', '323', 1);
INSERT INTO `messagesetting` VALUES ('35bdb954914348e6b83d10dab2eea1ad', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 1, '6D7E1C7AFAFB43E986670A81CF444233', '200,500', 2);
INSERT INTO `messagesetting` VALUES ('3721dffb794b4870bf9029b6d251c786', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444258', '', 6);
INSERT INTO `messagesetting` VALUES ('3935af8a5102472fb6b3e1f466455901', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444240', '', 6);
INSERT INTO `messagesetting` VALUES ('3b967fa57c5241159ca29e2ec12541d3', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444232', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('3bdc6e705c0e4e82b7d4b5bdc7b729f1', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444251', '323', 1);
INSERT INTO `messagesetting` VALUES ('3d696a91c84c4c509bc3fc2bdbea03d6', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702b8b7fc8012b99606d320f1c', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('3e3beb8519924234b7346530c31704c9', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a433647400ab', '', 6);
INSERT INTO `messagesetting` VALUES ('41e500dfd7b644ba8b418f053f74881e', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a4347f2b00fc', '323', 1);
INSERT INTO `messagesetting` VALUES ('4338629c9cfb438bb15928bbb2106dc8', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702db81ec3012dbca135f20c6a', '323', 1);
INSERT INTO `messagesetting` VALUES ('4350c95f80f744bea9601b26e6b5d6e4', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a432c1645678901', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('44151a08df374a77a935400db2d691d0', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444260', '323', 1);
INSERT INTO `messagesetting` VALUES ('448ff5efb5bc4fe4b4e4e11b05d00366', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444257', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('44f5518a28484f68b968f7ad6006e518', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a4316b830001', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('45012b94ffd34e4e8e2e757e9b7cb135', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444250', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('45a7cdd8192542448f0eca449da08939', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a433647400aa', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('45bfcf767f2145afafe3c0f7e59d6f91', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444232', '', 6);
INSERT INTO `messagesetting` VALUES ('4841c5e1917d41c9ae42d4781cd983e1', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444241', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('49031ebdc52c4a2d967ba98763e1d27d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702b8b7fc8012b99606d320f1c', '323', 1);
INSERT INTO `messagesetting` VALUES ('4f60dba8ded44c1abff296b84e95553e', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444251', '', 6);
INSERT INTO `messagesetting` VALUES ('50f28e1415284233b0d72e08fe4efe28', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702ccfd13c012ce80cb01222f6', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('51624fc77bcc4b6d9e869ef5bfc561e3', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444255', '', 6);
INSERT INTO `messagesetting` VALUES ('518b9992c18141118b2d74c92bb3026d', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '542gw502d453f27012d4ef49eb58o07F', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('51c62d60057846c08e14ecb90db2f1ca', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a432c1645678901', '323', 1);
INSERT INTO `messagesetting` VALUES ('51e44c4f284d428c97100cb3eaf0bb2d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'YZ7E1C7BCAFB56E986670A81CF345259', '323', 1);
INSERT INTO `messagesetting` VALUES ('53eacf11d40b42928a9336a9e6e5ed7c', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702d453f27012d4ef49eb508c9', '', 6);
INSERT INTO `messagesetting` VALUES ('54728676b4954148919da1353b4acda5', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '542gw502d453f27012d4ef49eb58o07F', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('54c05f7f6ce642269f14fe0cd5dbcb5d', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'YZ7E1C7BCAFB56E986670A81CF345259', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('54def951b99941e291f23ce4b248d219', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702bb9b1b9012bbce154320202', '', 6);
INSERT INTO `messagesetting` VALUES ('55a08fca51de4f189a146ea1139d35da', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a432c1645678901', '', 6);
INSERT INTO `messagesetting` VALUES ('55bafee905574b4c90e0145adfa2aa37', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a432c16e0049', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('57209eff46a744998e467b4b876c9406', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7F6A3394246EA4ADFEBACFB92E808E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('5a2f745fa9854717b1859b3506061a12', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '4CJYSY20170807A4ADFEBACFB92E808E', '323', 1);
INSERT INTO `messagesetting` VALUES ('5aeb88b9a0124d56892b8cf233621cde', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '4028347027a51b8f0127b336b2a30005', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('5b68f36604aa492abe8007ff16d7f852', '云纸', '云纸哦', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 1, '6D7E1C7AFAFB43E986670A81CF444233', '426', 1);
INSERT INTO `messagesetting` VALUES ('5b68f36604aa492abe8007ff16d7f853', '来电', '没电了', 'https://qiniu.powerlong.com/2019-11-25/e623b3f1d80f4e238ceec514adf52902.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', '100,200', 3);
INSERT INTO `messagesetting` VALUES ('5b68f36604aa492abe8007ff16d7f854', '熊猫遛娃', '娃累了', 'https://qiniu.powerlong.com/2019-11-25/2c36c9a1ae2745a99daf90b2076d2a5b.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', '300,200', 4);
INSERT INTO `messagesetting` VALUES ('5b68f36604aa492abe8007ff16d7f856', '黄小鹿', '美爆了', 'https://qiniu.powerlong.com/2019-11-25/f4c04abf6c514f5b9b93c91b3a8fa552.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', '999,500,900', 6);
INSERT INTO `messagesetting` VALUES ('5b68f36604aa492abe8007ff16d7f857', '乐摩吧', '按摩了', 'https://qiniu.powerlong.com/2019-11-25/d5e374aad09641bea6b1170132f0555b.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', '200,300,500,1000', 2);
INSERT INTO `messagesetting` VALUES ('5c08ef7342944f938405215fef653b33', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF436499', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('5c7b4ec923e243d0a62f76aadab996ee', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702d453f27012d4ef49eb508c9', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('5d89f8796e5d49febde8afe26946ef75', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C060624639924CDEA80EBEB52D6E002E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('5e6dce2164454cf6b4c6bf3cc49f62ff', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444260', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('5fe0f498932c4c2a8cdcecc28f81820d', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444257', '', 6);
INSERT INTO `messagesetting` VALUES ('609241ffb17c4179a30845029d54bdc6', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a432c1612345', '323', 1);
INSERT INTO `messagesetting` VALUES ('616f7d08da59433f92d5619118272529', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF436598', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('62c47afe24c34a238734aea0ab6c1f8b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702ccfd13c012ce80cb01222f6', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('64014860ed7b4c2899630c5ab697f091', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '16084DA3912DDAE4E050007F01005603', '', 6);
INSERT INTO `messagesetting` VALUES ('6417809ffa1d4fdf9aa6d478d91c9e14', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a4347f2b00fc', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('64cfce7ed8544d09a3e3d285c94e884d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '4028347027a51b8f0127b336b2a30005', '323', 1);
INSERT INTO `messagesetting` VALUES ('650e1a887c3a455fae54de1033f5c33b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '4028347027a51b8f0127b336b2a30003', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('662a4f2ea65245f8b19e32cdf4002c8b', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '542gw502d453f27012d4ef49eb58o07D', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('6671f426fac44c389fde16ca281d9447', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444239', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('6716d56b3a7546438714c4cafe224123', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444345', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('69bdff3db9e7473b974adf5b65781dc4', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'YZ7E1C7BCAFB56E986670A81CF345259', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('69ea0b55595f4b4182cd2577d494b4d6', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702fda2365012fdd1bd6e108ee', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('6a11c96a53b444918ecb6b89e7fdf380', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF436499', '', 6);
INSERT INTO `messagesetting` VALUES ('6ae190f86c474deb9126d9e239720be5', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF441231', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('6b2a6aaf111d4f489bd2212cfaaa12c2', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702ccfd13c012ce80cb01222f6', '', 6);
INSERT INTO `messagesetting` VALUES ('6b37241b24cf4937bf4e0416677fc625', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a4347f2b00fc', '', 6);
INSERT INTO `messagesetting` VALUES ('707cc3e2a21b4bcb86b9a468091935d2', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a432c1645678901', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('720b4ba5999c444fad838bf09f83eb5e', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 1, '6D7E1C7AFAFB43E986670A81CF444233', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('7234fc5f8089451095e63af90222aca1', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444260', '', 6);
INSERT INTO `messagesetting` VALUES ('7290d2ab0dd8434fae4135dbf6a1bd9c', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C060624639924CDEA80EBEB52D6E002E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('72be02cb6cf54429beb88aa04219c806', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7F6A3394246EA4ADFEBACFB92E808E', '', 6);
INSERT INTO `messagesetting` VALUES ('73d5eabddaaf4bc1a8bf891db29e181c', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a433cd3100d5', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('74c64a4d6ecb4170be4d03dde514f60e', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '4CJYSY20170807A4ADFEBACFB92E808E', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('75bbce3fa1ee4c5ba3bb1e526ee917c4', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C0689QWERTY24123480EBEB52D6E002F', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('76e9bb15d78946f78b153310be660c33', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702ec49066012ed7a1aac1225e', '323', 1);
INSERT INTO `messagesetting` VALUES ('7830d93fba274fd5a91dcb9ee70ecca0', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '16084DA3912DDAE4E050007F01005603', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('78fbc11583284ec79738b69e53039284', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702bb9b1b9012bbce154320202', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('7aa5238006f844c6a481a7ab6074b007', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C0689QWERTY24123480EBEB52D6E002F', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('7ac7b529de5044d5a0a4557182f00238', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a4316b830001', '323', 1);
INSERT INTO `messagesetting` VALUES ('7c2d2994bdab461abd19453800cb4f08', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'qpeq_4028347027a51b8f0127b336b2a30002', '323', 1);
INSERT INTO `messagesetting` VALUES ('7ce20a0b87e542b8a334ea07384291e2', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a435d5c30126', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('808a60fb2aa74c68b60cc50b790c55e5', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a435d5c30126', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('8272c353520442628eea0e7222a99de4', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702d453f27012d4ef49eb508c9', '323', 1);
INSERT INTO `messagesetting` VALUES ('82fb1dcc46d5471fb4ec2a566e1e7152', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702db81ec3012dbca135f20c6a', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('83aeca6194174bea9ac60bfd303734e5', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a4347f2b00fc', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('84eb36f43bf34790939001500f705dbd', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444251', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('8536753570e94d459174dd3edf5b48c5', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444240', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('86e42eb8936343f5809991c09525c63f', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a4316b830001', '', 6);
INSERT INTO `messagesetting` VALUES ('871f47bc206c4a41b15d9b253eeb5baf', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702b8b7fc8012b99606d320f1c', '', 6);
INSERT INTO `messagesetting` VALUES ('87333cd3917e4e08b1a77d7a652d024d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C0689QWERTY24123480EBEB52D6E002E', '323', 1);
INSERT INTO `messagesetting` VALUES ('87a8a5ba056344e58c230743ccf4ba3e', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '4028347027a51b8f0127b336b2a3000d', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('886e89f215234bb18eff4a6be36c84d2', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a433647400ab', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('8880d7c5990144fe95629dff9ed4e6e9', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'qpeq_4028347027a51b8f0127b336b2a30002', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('8a07abcbfc3e4373b9d477649e80ed47', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702ec49066012ed7a1aac1225e', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('8b1b2cd42e1c40aaafd784d03464011d', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C068924639924123480EBEB52D6E002E', '', 6);
INSERT INTO `messagesetting` VALUES ('8c0c3c6114d645709fa713dd37931a5b', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C0689QWERTY24123480EBEB52D6E002F', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('8c298002b2fc4a2aa3c812443ec9871c', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a432c1645678901', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('8c696aba61ab4bbd8b1faaac455bfe1d', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '4028347027a51b8f0127b336b2a30006', '', 6);
INSERT INTO `messagesetting` VALUES ('8d42880d40c34223b98d55d5e2fe410c', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a432c16e0049', '323', 1);
INSERT INTO `messagesetting` VALUES ('8df0f2bb6e3f4b919389a6b0ce43a396', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444239', '', 6);
INSERT INTO `messagesetting` VALUES ('8e2bb452a7fb46bc846f51c1840b9955', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702fda2365012fdd1bd6e108ee', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('9075407c52634ef99f19bdda055c91ff', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444345', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('907bc0f7af994f06b0e1f86f94740590', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'YZ7E1C7BCAFB56E986670A81CF345259', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('90dd4ce45b8541d088ac3d0ccf35d97c', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702e90c8fb012e9436a78f0a0a', '323', 1);
INSERT INTO `messagesetting` VALUES ('91743d17509149a4b36cb4cb41ff3300', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a433cd3100d5', '323', 1);
INSERT INTO `messagesetting` VALUES ('91c347dee4824e4c86af0de3b1c3804b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444250', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('923a0bac28e34f23a5400f2bde9ff974', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a433647400aa', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('924a211fa6fc4a7696c4c6210a32c68e', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '4028347027a51b8f0127b336b2a30006', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('9337d3ed00dd4cc0850bc85cbdab7339', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702bb9b1b9012bbce154320202', '323', 1);
INSERT INTO `messagesetting` VALUES ('93d770256f1649a2bfa5f28768a2eb46', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '542gw502d453f27012d4ef49eb58o07D', '323', 1);
INSERT INTO `messagesetting` VALUES ('944dfd82a1f5462a8f53892f7ab088e7', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444255', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('94cc4537a9d34b9ebb45aa51b96978cf', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C060624639924CDEA80EBEB52D6E002B', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('9576e69bf6b24d2a9847a36ea5e09650', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF436598', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('9636f734ca864fdab8bc63419fb494b7', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 1, '6D7E1C7AFAFB43E986670A81CF444233', '500,250', 3);
INSERT INTO `messagesetting` VALUES ('983517afa10941f39bcf9070a41ec715', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702b486353012b4ce115170629', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('987cad65e0fd4a3d8e8847d7ade93977', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a432c1612345', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('98b8f0786a0849099346125daa86db2a', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702e90c8fb012e9436a78f0a0a', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('999134a5696f4ff6b261d4ffc3f7c5a8', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702bb9b1b9012bbce154320202', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('9a7344a620e64e01a5fc5e5025db083f', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '4CJYSY20170807A4ADFEBACFB92E808E', '', 6);
INSERT INTO `messagesetting` VALUES ('9b581a2f3fc440eeb7df905eea27ce5f', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a433647400aa', '', 6);
INSERT INTO `messagesetting` VALUES ('9b7f3b6939824f5db725f4e6074e98a6', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C060624639924CDEA80EBEB52D6E002E', '', 6);
INSERT INTO `messagesetting` VALUES ('9ca8507ab25a4e53b8e0f51fc4fcb4d0', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '542gw502d453f27012d4ef49eb58o07D', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('9e02799aa6c148d490540a3c4030c2f6', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702ec49066012ed7a1aac1225e', '', 6);
INSERT INTO `messagesetting` VALUES ('9e36ae0edcfa4cc29c5789a43488e02b', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702e90c8fb012e9436a78f0a0a', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('9e6eac40611c4533b2308b539a495f17', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C068924639924123480EBEB52D6E002E', '323', 1);
INSERT INTO `messagesetting` VALUES ('9e8de55b11014e049a9390be00a30268', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702b8b7fc8012b99606d320f1c', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('9faeb472a6e5434ea1d3a7809bdfc8f1', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '16084DA3912DDAE4E050007F01005603', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('9ff7388dc9eb4c9f907f6ce5941fdfe5', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702e90c8fb012e9436a78f0a0a', '', 6);
INSERT INTO `messagesetting` VALUES ('a0225c08ce594da4aac24e22fb55420b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702ec49066012ed7a1aac1225e', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('a191f956d96342e7b825c0901977c94b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a433647400ab', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('a2099937eed24f82aa89fefad88f767c', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF436598', '323', 1);
INSERT INTO `messagesetting` VALUES ('a3273acf59ec424294e60ea0697fb43d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '1C7F6A3394524EA4ADFEBACFB92E570D', '323', 1);
INSERT INTO `messagesetting` VALUES ('a32ff777c90c4d96aa1d82798a9d1c6f', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702ccfd13c012ce80cb01222f6', '323', 1);
INSERT INTO `messagesetting` VALUES ('a3e2c674bc524ee182342ce7d172bb86', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a4347f2b00fc', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('a47f53901b9f456c995f70894f2f3a90', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444345', '', 6);
INSERT INTO `messagesetting` VALUES ('a546feb8ebbe4c69a039bf665a1ab515', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '4CJYSY20170807A4ADFEBACFB92E808E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('a57b794d1e1f4ce3acd9f728033a945d', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '4028347027a51b8f0127b336b2a30005', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('a5a2befb4f07427ca66504d77902bef3', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, 'C060624639924CDEA80EBEB52D6E002B', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('a5b6b8456804438694fbeb24621a385c', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a433647400aa', '323', 1);
INSERT INTO `messagesetting` VALUES ('a5e09b8ca17f452d9fd9c9e459623033', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '4028347027a51b8f0127b336b2a30006', '323', 1);
INSERT INTO `messagesetting` VALUES ('a6eaa29a23844e29b7d7c9f81bb8ae97', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF436499', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('a88718f6141d47d49212328f6991b4db', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444253', '', 6);
INSERT INTO `messagesetting` VALUES ('ab2acc8724b0438aa2065be9296927df', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444257', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('ac5ee53f734242cc8637733ff7fa629e', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF441231', '', 6);
INSERT INTO `messagesetting` VALUES ('add87a260f1648978d340fecd3369d53', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444258', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('ade704055b844c8bb98c6728cfe09bd7', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C0689QWERTY24O89045EB123456E002E', '323', 1);
INSERT INTO `messagesetting` VALUES ('aef1222df36e471fbfff42264ae5b56b', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444255', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('af1a66f3173d4cf7a2f48dbce27d2035', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a433cd3100d5', '', 6);
INSERT INTO `messagesetting` VALUES ('af8aaea7cc1f4b6e90431afb17a28ebe', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '542gw502d453f27012d4ef49eb58o07D', '', 6);
INSERT INTO `messagesetting` VALUES ('b1403f365f0f4d1088252fe47ebcfdb9', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702e90c8fb012e9436a78f0a0a', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('b153251c14aa4629870f0c0e5dbaeae5', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C060624639924CDEA80EBEB52D6E002E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('b198e6fb722644188ec454e272866a1c', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702bb9b1b9012bbce154320202', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('b28d010de99f48d6977b71d702e33efb', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C068924639924123480EBEB52D6E002E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('b5f94c943ad645ccba142d423f11e319', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF436499', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('b61365002c5344d99b183532ced45728', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '4028347027a51b8f0127b336b2a3000d', '', 6);
INSERT INTO `messagesetting` VALUES ('b6509825f89a48d4a44c51829588cd52', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702fda2365012fdd1bd6e108ee', '', 6);
INSERT INTO `messagesetting` VALUES ('b854e33b93a241dca3bb0390dbdae5bb', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444232', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('b8608dbf69e04c1f960a08acdae63fac', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '402834702d453f27012d4ef49eb508c9', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('b95a65a1b9f04ed9ba9bfdd8c0f3b296', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C060624639924CDEA80EBEB52D6E002B', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('bb22912b6586472ea08ae19a9b9a80c4', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7F6A3394246EA4ADFEBACFB92E808E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('bf6f5bb317274d7783f0fca8f8dc7b16', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444258', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('c06ef43b8f2743fe9e7e3efd07ae7f4f', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '4CJYSY20170807A4ADFEBACFB92E808E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('c1077fbbecf1472191eb08a3daf7e806', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '16084DA3912DDAE4E050007F01005603', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('c14cda63894d4fb197afc667031b83f6', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'YZ7E1C7BCAFB56E986670A81CF345259', '', 6);
INSERT INTO `messagesetting` VALUES ('c4721dc5ad8e4ffca65da75296d3bd5d', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '40282b8927a42dff0127a435d5c30126', '323', 1);
INSERT INTO `messagesetting` VALUES ('c583d47b34af4e8a86686bd9d5cd10c1', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C0689QWERTY24123480EBEB52D6E002E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('c6d03dfadc0c4aaeacb11365536de803', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444241', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('c7213f150dff4d259aad3244828e422f', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF436598', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('c7c440908e2f460188e85d5e61a2985a', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444240', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('c80ac9efebcd414bae33cd9f2a2364c4', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444250', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('c86d39a1851543ae864824bb0873258c', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '4028347027a51b8f0127b336b2a30003', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('c8a5217ab98c4e7b8361963a5fb240a2', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444345', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('ca02bd3946b04e548dbd3bd2be8d6186', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444240', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('ca45fd2c79df49a9a2c453811cc7b0dd', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C060624639924CDEA80EBEB52D6E002E', '323', 1);
INSERT INTO `messagesetting` VALUES ('cb15fa389f2342efa5c890ce3979d342', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a433647400ab', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('cb1f70f534ae4ef9a3511a8792f4165c', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '542gw502d453f27012d4ef49eb58o07F', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('cf4ac35e5ac74a298e0fa2e4aa0f52f5', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '4028347027a51b8f0127b336b2a30006', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('d021544181964c60800a9aa422011965', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '6D7E1C7AFAFB43E986670A81CF444251', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('d18a6d694b5a47a58491ca0d9677a79e', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 1, '6D7E1C7AFAFB43E986670A81CF444253', '2,3,100', 2);
INSERT INTO `messagesetting` VALUES ('d1e7bd64b829424e94e78699f8104bbc', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7F6A3394246EA4ADFEBACFB92E808E', '323', 1);
INSERT INTO `messagesetting` VALUES ('d3f7cf055420409eacced42d50cc95df', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '402834702fda2365012fdd1bd6e108ee', '323', 1);
INSERT INTO `messagesetting` VALUES ('d43401a9d88d4fd4bb5ddf7dc943d52d', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'qpeq_4028347027a51b8f0127b336b2a30002', '', 6);
INSERT INTO `messagesetting` VALUES ('d43d450d93a2451fb9cc5f967aec9dba', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '1C7F6A3394524EA4ADFEBACFB92E570D', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('d739f1188b5641888f811fb276d61673', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444257', '323', 1);
INSERT INTO `messagesetting` VALUES ('d779db0577c64d509ec9296246ff8cc3', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'qpeq_4028347027a51b8f0127b336b2a30002', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('d82e5a2fd8f44ad5ae420078b18ee863', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 1, '6D7E1C7AFAFB43E986670A81CF444253', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('d91150fb6ec14c868eed3495cc2b9524', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '40282b8927a42dff0127a432c16e0049', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('dc6d44f371f24e64873e30300aa4444c', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444258', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('dd945444be16407691b1da211a2067d3', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444241', '', 6);
INSERT INTO `messagesetting` VALUES ('dda0fc75bbc84c068459633fc7aea5c1', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a432c1612345', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('ddacff5cd5794f5b8d6ab461719c33c2', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '40282b8927a42dff0127a4316b830001', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('de6b7972a2a648ffa99ce2886a49b05f', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '4028347027a51b8f0127b336b2a3000d', '323', 1);
INSERT INTO `messagesetting` VALUES ('deebfa56f4b24c7d918b302467e9cfe9', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444239', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('deed9902da5247019f515210889891ed', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a433cd3100d5', '202,103', 3);
INSERT INTO `messagesetting` VALUES ('df6ae20261db499399dd8fb18242e218', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C068924639924123480EBEB52D6E002E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('e052d90528f44e6b9e16f7f60d80acc9', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, 'C0689QWERTY24123480EBEB52D6E002E', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('e13abf426ab94ff580f3c496ea48616b', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444241', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('e160175d400d4740a462054b6df4a136', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF436598', '', 6);
INSERT INTO `messagesetting` VALUES ('e235eee75aef47d69bad3dea04f1c1cd', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '4028347027a51b8f0127b336b2a30003', '', 6);
INSERT INTO `messagesetting` VALUES ('e2f275629e1d471d85175ebbdf2b555b', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 1, '6D7E1C7AFAFB43E986670A81CF444253', '323', 1);
INSERT INTO `messagesetting` VALUES ('e738dcb8a9964bfa9941cd80b8dec0b3', '咖啡吧', '口渴了', 'https://qiniu.powerlong.com/2019-12-03/f03da15cd297480ba7497f0d7a87c580.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', 'Y00219000126', 7);
INSERT INTO `messagesetting` VALUES ('e738dcb8a9964bfa994acd80b8dec0b3', '云纸', '没纸了', 'https://qiniu.powerlong.com/2019-11-25/248987e71fcd4891ba43cb2b38f5b67d.png', 1, '6D7E1C7AFAFB43E986670A81CF444235', '', 1);
INSERT INTO `messagesetting` VALUES ('e887a0ee34694d60bbe3c25fe96f11bc', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '402834702db81ec3012dbca135f20c6a', '', 6);
INSERT INTO `messagesetting` VALUES ('eb3febfd881f43efad5ccaf66e4be9a5', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '40282b8927a42dff0127a432c16e0049', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('ee7fc4fdfe30436dade34986636965e4', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444251', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('eef621d4f3464e7083b504ff4ac7054a', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 1, '6D7E1C7AFAFB43E986670A81CF444233', '', 6);
INSERT INTO `messagesetting` VALUES ('eef86cfd80094f20b0494599c67d3321', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '4028347027a51b8f0127b336b2a30003', '2,3', 2);
INSERT INTO `messagesetting` VALUES ('ef497f90006f43bfbd52400bb103d307', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '6D7E1C7AFAFB43E986670A81CF444260', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('ef50fff46f394e69aaa9aec7f05f0184', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, '4028347027a51b8f0127b336b2a3000d', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('f0aa5be8f9fc4f9e8204bcdbc64f2719', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '6D7E1C7AFAFB43E986670A81CF444257', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('f0ebeb37b81b4320880ea02e44c1153b', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '6D7E1C7AFAFB43E986670A81CF444250', '', 6);
INSERT INTO `messagesetting` VALUES ('f12d4572b9a74a6abfe44f82c4ee58b4', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '1C7F6A3394524EA4ADFEBACFB92E570D', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('f1ae3a0e57b24ef2b17586dd49df757f', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a435d5c30126', '', 6);
INSERT INTO `messagesetting` VALUES ('f336e5ecb71a44539baca4a4826e3f42', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, 'C060624639924CDEA80EBEB52D6E002B', '323', 1);
INSERT INTO `messagesetting` VALUES ('f4d891912ffa4a2c9b651b0feb9f1387', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, 'C0689QWERTY24O89045EB123456E002E', '', 6);
INSERT INTO `messagesetting` VALUES ('f4e020fe23e24eb69602492e754d8b25', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702db81ec3012dbca135f20c6a', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('f6acb7f175c54daabd6021a7e592bdf2', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444240', '323', 1);
INSERT INTO `messagesetting` VALUES ('f87d7d63579847eaa6ebafde7fdade20', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '402834702b486353012b4ce115170629', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('fa603c799ebd49c5acbedcc67c366399', '来电', '来电', 'https://qiniu.powerlong.com/2019-11-26/450ce098c7694018b7ebd0e21ce3ed79.png', 2, '542gw502d453f27012d4ef49eb58o07D', '2,3', 3);
INSERT INTO `messagesetting` VALUES ('fa708794f775481d9841c4a17d40a0cb', '云纸', '云纸', 'https://qiniu.powerlong.com/2019-11-26/c49765b64c514102843d29d041bdd4f6.png', 2, '6D7E1C7AFAFB43E986670A81CF444345', '323', 1);
INSERT INTO `messagesetting` VALUES ('fb0acf21f4df42a09b358239385dfda2', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '40282b8927a42dff0127a432c16e0049', '', 6);
INSERT INTO `messagesetting` VALUES ('fbb72e3ba0f94f18a8ecbcf8f4a61f1d', '黄小鹿', '黄小鹿', 'https://qiniu.powerlong.com/2019-11-26/eab9c2696acc4e1f8296ba3c1b771c7d.png', 2, '4028347027a51b8f0127b336b2a30005', '', 6);
INSERT INTO `messagesetting` VALUES ('fc0ef58bb72c450babdec34f1c95b024', '熊猫遛娃', '熊猫遛娃', 'https://qiniu.powerlong.com/2019-11-26/6e1d8ee02022431fbf2e7ce6fbc9b50f.png', 2, 'C0689QWERTY24O89045EB123456E002E', '2,4,6', 4);
INSERT INTO `messagesetting` VALUES ('fe1daf444eb144fc8f21f7bbe6a5b80a', '乐摩吧', '乐摩吧', 'https://qiniu.powerlong.com/2019-11-26/86223fdc1a734203928a58934ea0b1f8.png', 2, '402834702ccfd13c012ce80cb01222f6', '2,3', 2);


/*线上第三方密钥*/
INSERT INTO `table_key_pair` VALUES ('PMS', 'BvtTqKNncCVQP345fcCL09bRbuKIz99WGhfM', 'pXo2dj6nKfCZSc6lzAZTnEjwNWO4id3wwZTD');
INSERT INTO `table_key_pair` VALUES ('BL', 'JodslJiXcdp0vMFvflmI5FRbZlwsQwMJHg1N', 'BSyAs3zPnENyKZPgUAGcOrSaOx83aRbnaN2a');
INSERT INTO `table_key_pair` VALUES ('XMLW', 'FCwCVFU8MifuLyyqjeyklGKlUxb4NRMqeASi', 'OPrl2Xw5kHSERQDvIQMB6VImB31zy8IwfRkW');
INSERT INTO `table_key_pair` VALUES ('GXYZ', 'j184eg53S5kA95atQH6qzALc5hZYqFFKLarX', 'iytCdr24DOPof6d2a7UCeZYD8JcqlNlulCwj');
INSERT INTO `table_key_pair` VALUES ('GXLD', 'QMvTz4cPmGl1yGlELfYezDSLTNKw3CMRpT8u', 'jEQqIiUlSF8swKwCsZxuINuOZDE8XATJkwT0');
INSERT INTO `table_key_pair` VALUES ('GLMB', 'QAsqIIiGSmvVQYaSwHkx1aaZx8UUG20fDPsZ', 't3gslT5rPL9gX3IbF8NZiBHCzJMPKAlIdDNM');
INSERT INTO `table_key_pair` VALUES ('GHXL', 'KCClniO39t5msOV63vrb5aQ3ZGkQZLIbgjP1', 'dI6nqdkIUN1IMR9myZhnrxJVBkYvFjiWSaOe');
/*第三方跳转url*/
INSERT INTO `goThirdUrl` VALUES ('2', 'pages/bl-login/bl-login', 'wxadc480e27684767a', 'powerlong', 2, NULL);
INSERT INTO `goThirdUrl` VALUES ('4', ' pages/index/index', 'wxc71a6783826d6c9f', 'powerlong', 4, NULL);
INSERT INTO `goThirdUrl` VALUES ('3', '/pages/transferStation/transferStation', 'wxb0044c1f5803b55a', 'powerlong', 3, NULL);
INSERT INTO `goThirdUrl` VALUES ('6', 'pages/activity/baoLong/index', 'wx1bb77c53f3948134', 'powerlong', 6, NULL);