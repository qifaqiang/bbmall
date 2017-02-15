#!/bin/bash
 
#优惠券到期自动失效
#作者：亚洲
#时间：2015年11月22日16:23:53

iHOST=127.0.0.1
USER=mysql
PASSWD=hope2015
DATABASE=xyd
FILE=/mnt/xyd_log/auto_clear_sms_result.txt
d=$(date +%F" "%T);

DATA=$(mysql -D "${DATABASE}" --default-character-set=utf8 -e "call auto_clear_sms(@a);select @a")
#DATA=$(mysql -u "${USER}" -p"${PASSWD}" -D "${DATABASE}" --default-character-set=utf8 -e "call auto_coupons_overdue(@a);select @a")
echo $d '=result=' $DATA
exit

