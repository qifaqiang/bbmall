--自动判断优惠券是否过期
DROP procedure IF EXISTS auto_coupons_overdue;
create procedure auto_coupons_overdue(OUT cnt INT)
begin	
	UPDATE sys_coupons_record set state=3 where state=1 and NOW()<end_time;
	set cnt = row_count();
end;

CALL auto_coupons_overdue(@a);
SELECT @a;


--触发器  插入商品表的时候，自动插入product_statistics
DROP TRIGGER IF EXISTS t_afterinsert_on_product_toproduct_statistics;
CREATE TRIGGER t_afterinsert_on_product_toproduct_statistics
AFTER INSERT ON product
FOR EACH ROW
BEGIN
     insert into product_statistics(prod_id) values(new.id);
END;

--触发器  插入商品规格表的时候，自动插入product_specification_info_statistics
--DROP TRIGGER IF EXISTS t_afterinsert_on_product_specification_info;
--CREATE TRIGGER t_afterinsert_on_product_specification_info 
--AFTER INSERT ON product_specification_info
--FOR EACH ROW
--BEGIN
--     insert into product_specification_info_statistics(prod_id,spec_id) values(new.product_id,new.id);
--END;


--1分钟后清空sms
DROP procedure IF EXISTS auto_clear_sms;
create procedure auto_clear_sms(OUT cnt INT)
begin	
	UPDATE sys_sms_check set count=0 where count>0;
	set cnt = row_count();
end;

CALL auto_clear_sms(@a);
SELECT @a;



--取消订单
DROP procedure IF EXISTS auto_cancel_order;
create procedure auto_cancel_order()
BEGIN
	DECLARE tid INT;
	DECLARE done INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	DECLARE b INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	DECLARE c INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	
	DECLARE tprod_id INT;DECLARE tprod_count INT;DECLARE tprodspec_id INT;DECLARE tprodtype INT;
	DECLARE torder_id INT;DECLARE tproduct_basic_id INT;DECLARE tcompany_id INT;DECLARE tinventorynumber INT;	

	/*获取所有已经超时并且未付款的订单*/
	DECLARE
		cur_1 CURSOR FOR SELECT	os.id,os.company_id	FROM orders os WHERE os. STATUS = 11
		AND os.addtime < adddate(now(), INTERVAL - (select auto_cancel_time from sys_proportion) HOUR);	

	DECLARE
		CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	OPEN cur_1;
	out_loop: LOOP
		FETCH cur_1 INTO torder_id,tcompany_id;
			
			IF done = 1 THEN
				LEAVE out_loop;
			END IF;
			/*获取第一条记录*/

					begin 
						DECLARE cur_2 CURSOR FOR SELECT os.id,od.prod_id,od.prod_count,od.prod_spec_id
							FROM orders os LEFT JOIN orders_detail od ON os.id = od.order_id
							WHERE os. STATUS = 11 AND os.id=torder_id;

						OPEN cur_2;
							inner_loop:LOOP
							FETCH cur_2 INTO tid,tprod_id,tprod_count,tprodspec_id;

								 IF done = 1 THEN
											 LEAVE inner_loop;
								 end IF;

								SELECT type,prod_basic_id into tprodtype,tproduct_basic_id FROM product where id=tprod_id;
								
								
								IF tprodtype = 1  /*礼盒商品*/
								THEN
										begin 
													DECLARE cur_3 CURSOR FOR SELECT pp.prod_baseic_id,pp.inventorynumber
														FROM product_package pp WHERE pp. prod_id = tprod_id;

													OPEN cur_3;
														three_loop:LOOP
														FETCH cur_3 INTO tproduct_basic_id,tinventorynumber;

															 IF done = 1 THEN
																	LEAVE three_loop;									 
															 end IF;
														IF tinventorynumber*tprod_count>0 THEN
															update company_stock set inventorynumber=inventorynumber+tinventorynumber*tprod_count where company_id=tcompany_id and prod_basic_id=tproduct_basic_id;
														end IF;

														end LOOP three_loop;
													CLOSE cur_3;
											end;				
								 
								ELSEIF tprodtype = 0  /*普通商品*/
								THEN 
											/*获取商品规格所占有的数量*/
											SELECT inventorynumber into tinventorynumber FROM product_specification_info where product_id=tprod_id and id=tprodspec_id;
											-- select tinventorynumber,tprod_count;

											IF tinventorynumber*tprod_count>0 THEN

													update company_stock set inventorynumber=(inventorynumber+(tinventorynumber*tprod_count)) where company_id=tcompany_id and prod_basic_id=tproduct_basic_id;
											END IF;  
							  END IF;

							end LOOP inner_loop;
						CLOSE cur_2;
					end;					
					update orders set `status`=0 where id=tid;
					insert into orders_log (order_id,operator,order_status,changed_status,remark,log_time) VALUES (tid,'0','11','100','系统自动取消',now());
				
			/*取下一条记录*/
			SET done=0;
	END LOOP out_loop;
	CLOSE cur_1;
END;


-- 自动确认收货
DROP procedure IF EXISTS auto_accept_order;
create procedure auto_accept_order(OUT cnt INT)
begin	
	UPDATE orders set STATUS=40,accept_time=now() where STATUS=30	
		AND ship_time < adddate(now(), INTERVAL - (select auto_accept_time from sys_proportion) HOUR);
	set cnt = row_count();
end;


-- 自动确认完成
DROP procedure IF EXISTS auto_over_order;
create procedure auto_over_order()
BEGIN
	DECLARE tid INT;
	DECLARE done INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	
	DECLARE tprod_id INT;DECLARE tprod_count INT;DECLARE tprodspec_id INT;DECLARE tprodtype INT;
	DECLARE torder_id INT;DECLARE tproduct_basic_id INT;DECLARE tcompany_id INT;DECLARE tinventorynumber INT;	

	/*获取所有已经超时并且未付款的订单*/
	DECLARE
		cur_1 CURSOR FOR SELECT	os.id,os.company_id	FROM orders os WHERE os. STATUS = 40 and os.is_over=0
		AND os.accept_time < adddate(now(), INTERVAL - (select auto_over_time from sys_proportion) HOUR);	

	DECLARE
		CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	OPEN cur_1;
	out_loop: LOOP
		FETCH cur_1 INTO torder_id,tcompany_id;
			
			IF done = 1 THEN
				LEAVE out_loop;
			END IF;
			/*获取第一条记录*/

					begin 
						DECLARE cur_2 CURSOR FOR SELECT os.id,od.prod_id,od.prod_count,od.prod_spec_id
							FROM orders os LEFT JOIN orders_detail od ON os.id = od.order_id
							WHERE os. STATUS = 40 and os.is_over=0 AND os.id=torder_id;

						OPEN cur_2;
							inner_loop:LOOP
							FETCH cur_2 INTO tid,tprod_id,tprod_count,tprodspec_id;

								 IF done = 1 THEN
											 LEAVE inner_loop;
								 end IF;

								SELECT type,prod_basic_id into tprodtype,tproduct_basic_id FROM product where id=tprod_id;
								
								IF tprodtype = 1  /*礼盒商品*/
								THEN
										begin 
													DECLARE cur_3 CURSOR FOR SELECT pp.prod_baseic_id,pp.inventorynumber
														FROM product_package pp WHERE pp. prod_id = tprod_id;

													OPEN cur_3;
														three_loop:LOOP
														FETCH cur_3 INTO tproduct_basic_id,tinventorynumber;

															 IF done = 1 THEN
																	LEAVE three_loop;									 
															 end IF;
														IF tinventorynumber*tprod_count>0 THEN
															-- update company_stock set inventorynumber=inventorynumber+tinventorynumber*tprod_count where company_id=tcompany_id and prod_basic_id=tproduct_basic_id;
																 INSERT INTO product_basic_statistics_all (basic_id,sales_count,company_id,addtime,prod_id)
																				VALUES (tproduct_basic_id,tprod_count,tcompany_id,now(),tprod_id);
																 
														end IF;

														end LOOP three_loop;
													CLOSE cur_3;
											end;				
								 
								ELSEIF tprodtype = 0  /*普通商品*/
								THEN 
											/*获取商品规格所占有的数量*/
											SELECT inventorynumber into tinventorynumber FROM product_specification_info where product_id=tprod_id and id=tprodspec_id;
											-- select tinventorynumber,tprod_count;

											IF tinventorynumber*tprod_count>0 THEN
													INSERT INTO product_basic_statistics_all (basic_id,sales_count,company_id,addtime,prod_id)
																				VALUES (tproduct_basic_id,tinventorynumber*tprod_count,tcompany_id,now(),tprod_id);
												
											END IF; 
											
							  END IF;

								
								IF tprod_count>0 THEN													
										UPDATE product_statistics SET sales_count=sales_count+tprod_count WHERE prod_id=tprod_id;							
								END IF; 

							end LOOP inner_loop;
						CLOSE cur_2;
					end;					
					update orders set `is_over`=1,over_time=now() where id=tid;		
			
				
			/*取下一条记录*/
			SET done=0;
	END LOOP out_loop;
	CLOSE cur_1;
END;

