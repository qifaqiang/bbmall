
/***�Զ�������Ŵ洢����**/

BEGIN
	UPDATE sys_sms_check set count=0 where count>0;
	set cnt = row_count();
END