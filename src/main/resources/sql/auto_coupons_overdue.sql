
/*****�Զ���������Ż�ȯ�洢����******/

BEGIN
	UPDATE sys_coupons_record set state=3 where state=1 and NOW()>end_time;
  set cnt = row_count();
END