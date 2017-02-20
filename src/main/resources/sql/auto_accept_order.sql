
/****自动确认收获存储过程***/




BEGIN
	UPDATE orders set STATUS=40,accept_time=now() where STATUS=30	
		AND ship_time < adddate(now(), INTERVAL - (select auto_accept_time from sys_proportion) HOUR);
	set cnt = row_count();
END