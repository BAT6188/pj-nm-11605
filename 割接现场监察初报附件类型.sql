SELECT
	count(1)
FROM
	hw_attachment a
WHERE
	a.ATT_TYPE IS NULL
AND a.BUSINESS_ID IN (
	SELECT
		id
	FROM
		hw_site_monitoring
);

UPDATE hw_attachment a
SET a.ATT_TYPE = '1'
WHERE
	a.BUSINESS_ID IN (
		SELECT
			s.id
		FROM
			hw_site_monitoring s
	)
AND a.ATT_TYPE IS NULL;

