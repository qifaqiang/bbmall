
/***自动清理短信存储过程**/

BEGIN
	UPDATE sys_sms_check set count=0 where count>0;
	set cnt = row_count();
END