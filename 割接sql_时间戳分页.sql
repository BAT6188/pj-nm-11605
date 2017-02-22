-- 增加字段
alter table hw_dispatch_task add `mobile_timestamp` timestamp not null default current_timestamp;
alter table T_MONITOR_REPORT add `mobile_timestamp` timestamp not null default current_timestamp;
alter table T_FEEDBACK add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_ENTERPRISE add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_CONTACTS add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_MANUAL add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_MEETING_NOTICE add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_PUB_INFO add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_PUB_INFO_REL_TABLE add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_AIR_EQUIPMENTHISTORY add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_DUST_PORT_HISTORY add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_FUMES_PORT_HISTORY add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_NOISE_PORT_HISTORY add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_DSHBCBP_TRANSPORT_EFFICIENT add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_SITE_MONITORING add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_MESSAGE add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_MESSAGE_TRACE add `mobile_timestamp` timestamp not null default current_timestamp;

-- 新增2017-02-14
alter table HW_BUILD_PROJECT add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_ENTERPRISE_PLAN add `mobile_timestamp` timestamp not null default current_timestamp;
alter table HW_SELF_CHECK_REPORT add `mobile_timestamp` timestamp not null default current_timestamp;


-- 建表seq
CREATE TABLE `seq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `link_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;


-- 割接数据
update hw_dispatch_task t set t.mobile_timestamp=t.update_time;

insert into seq(link_id) select id from T_MONITOR_REPORT;
update T_MONITOR_REPORT t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from T_FEEDBACK;
update T_FEEDBACK t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_ENTERPRISE;
update HW_DSHBCBP_ENTERPRISE t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_CONTACTS;
update HW_CONTACTS t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_MANUAL;
update HW_MANUAL t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_MEETING_NOTICE;
update HW_MEETING_NOTICE t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_PUB_INFO;
update HW_PUB_INFO t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_PUB_INFO_REL_TABLE;
update HW_DSHBCBP_PUB_INFO_REL_TABLE t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_AIR_EQUIPMENTHISTORY;
update HW_AIR_EQUIPMENTHISTORY t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_DUST_PORT_HISTORY;
update HW_DSHBCBP_DUST_PORT_HISTORY t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_FUMES_PORT_HISTORY;
update HW_DSHBCBP_FUMES_PORT_HISTORY t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_NOISE_PORT_HISTORY;
update HW_DSHBCBP_NOISE_PORT_HISTORY t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_DSHBCBP_TRANSPORT_EFFICIENT;
update HW_DSHBCBP_TRANSPORT_EFFICIENT t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_SITE_MONITORING;
update HW_SITE_MONITORING t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_MESSAGE;
update HW_MESSAGE t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_MESSAGE_TRACE;
update HW_MESSAGE_TRACE t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

-- 新增2017-02-14
insert into seq(link_id) select id from HW_BUILD_PROJECT;
update HW_BUILD_PROJECT t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_ENTERPRISE_PLAN;
update HW_ENTERPRISE_PLAN t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;

insert into seq(link_id) select id from HW_SELF_CHECK_REPORT;
update HW_SELF_CHECK_REPORT t set t.mobile_timestamp=date_add('2016-12-01 00:00:01',interval (select s.id from seq s where s.link_id=t.`id`) second);
delete from seq;


