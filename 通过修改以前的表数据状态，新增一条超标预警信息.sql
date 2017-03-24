-- 修改要恢复的超标预警记录
UPDATE hw_monitor_case m
SET m.`status` = '0'
WHERE
	m.id IN (
		'3c942d9770b24f648158bf1c295ef638'
	);

-- 查出来超标预警对应的企业id和调度出去的执法管理数据id
SELECT
	m.enterprise_id,
	m.dispatch_id
FROM
	hw_monitor_case m
WHERE
	m.id = '3c942d9770b24f648158bf1c295ef638';

-- 修改对应的企业污染源状态
UPDATE hw_dshbcbp_enterprise e
SET e.pollutant_status = '1'
WHERE
	e.id = 'cdd32b01623147a8b11c5e38f2ae2c06';

-- 删掉对应的已调度出去的执法管理数据（不删应该也可以）
DELETE
FROM
	hw_dispatch_task
WHERE
	id = '4e1f54a4c9d44b0e8aba6f708338babd';

