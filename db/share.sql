
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `share`
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `share_id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`share_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES ('1', 'liu', 'xing');
INSERT INTO `share` VALUES ('2', 'fan', 'zhang');
