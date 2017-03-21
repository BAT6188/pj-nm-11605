UPDATE hw_video_device_2 v2
SET v2.latitude = (
	SELECT
		v1.latitude
	FROM
		hw_video_device v1
	WHERE
		v1.longitude IS NOT NULL
	AND v1.addr = v2.addr
	AND v1.unit = v2.unit
	LIMIT 1
);