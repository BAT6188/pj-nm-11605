UPDATE hw_monitor_case m
SET m.`status` = '0'
WHERE
	m.id IN (
		'f2d7d789d4ac4461951c56001fa205f7'
	);

-- cdd32b01623147a8b11c5e38f2ae2c06
UPDATE hw_dshbcbp_enterprise e
SET e.pollutant_status = '1'
WHERE
	e.id = 'cdd32b01623147a8b11c5e38f2ae2c06';