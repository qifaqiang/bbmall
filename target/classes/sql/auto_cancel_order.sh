#!/bin/bash
 
#自动取消订单
#作者：亚洲
#时间：2015年11月22日16:23:53

iHOST=127.0.0.1
USER=mysql
PASSWD=hope2015
DATABASE=xyd
FILE=/mnt/xyd_log/auto_cancel_order_result.txt
d=$(date +%F" "%T);

DATA=$(mysql -D "${DATABASE}" --default-character-set=utf8 -e "call auto_cancel_order();")
#DATA=$(mysql -u "${USER}" -p"${PASSWD}" -D "${DATABASE}" --default-character-set=utf8 -e "call auto_coupons_overdue(@a);select @a")
echo $d '=result=' $DATA
exit

