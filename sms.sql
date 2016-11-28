CREATE TABLE `api_mt_sms` (
  `AUTO_SN` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SM_ID` varchar(8) NOT NULL COMMENT '短信ID',
  `SRC_ID` varchar(8) NOT NULL,
  `MOBILES` varchar(22) NOT NULL COMMENT '用英文逗号或中文逗号隔开的手机号码集 ',
  `CONTENT` text NOT NULL,
  `IS_WAP` varchar(1) NOT NULL,
  `URL` varchar(110) NOT NULL DEFAULT '',
  `SEND_TIME` datetime DEFAULT NULL COMMENT '短信定时发送时间，格式为：yyyy-MM-dd  HH:mm:ss，为 null时立即发送。 ',
  `SM_TYPE` tinyint(1) NOT NULL DEFAULT '0',
  `MSG_FMT` int(11) NOT NULL,
  `TP_PID` tinyint(1) NOT NULL,
  `TP_UDHI` tinyint(1) NOT NULL,
  `FEE_TERMINAL_ID` varchar(10) DEFAULT NULL,
  `FEE_TYPE` varchar(10) DEFAULT NULL,
  `FEE_CODE` varchar(10) DEFAULT NULL,
  `FEE_USER_TYPE` int(11) NOT NULL,
  PRIMARY KEY (`AUTO_SN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发送队列表';

CREATE TABLE `api_rpt_sms` (
  `AUTO_SN` bigint(20) NOT NULL,
  `SM_ID` varchar(33) NOT NULL,
  `MOBILE` varchar(66) NOT NULL,
  `RPT_CODE` varchar(66) NOT NULL,
  `RPT_DESC` varchar(66) NOT NULL,
  `RPT_TIME` varchar(66) NOT NULL,
  PRIMARY KEY (`AUTO_SN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回执队列表';
