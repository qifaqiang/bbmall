
/***自动取消订单存储过程*****/

BEGIN
	DECLARE tid INT;
	DECLARE done INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	DECLARE b INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	DECLARE c INT DEFAULT 0;/*是否达到记录的末尾控制变量*/
	
	DECLARE tprod_id INT;DECLARE tprod_count INT;DECLARE tprodspec_id INT;DECLARE tprodtype INT;DECLARE tprodisspec INT;DECLARE tpackprod_id INT;
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

								SELECT type,prod_basic_id,IFNULL(is_specification,0) into tprodtype,tproduct_basic_id,tprodisspec FROM product where id=tprod_id;
								
								
								IF tprodtype = 1  /*礼盒商品*/
								THEN
										begin 
													DECLARE cur_3 CURSOR FOR SELECT pp.specification_info_id,pp.product_id,pp.type
														FROM product_package pp WHERE pp. prod_id = tprod_id;

													OPEN cur_3;
														three_loop:LOOP
														FETCH cur_3 INTO tprodspec_id,tpackprod_id,tprodisspec;

															 IF done = 1 THEN
																	LEAVE three_loop;									 
															 end IF;
																
															IF tprodisspec = 0 THEN   /**没有开启规格**/
																	IF tprod_count>0 THEN	
																			UPDATE product_specification_stock SET inventorycount=(inventorycount + (tprod_count*1))
																			WHERE type = 0 AND company_id = tcompany_id AND product_id = tpackprod_id;
																	END IF;	
															ELSEIF tprodisspec = 1	THEN	/*该商品开启了规格*/
																	IF tprod_count>0 THEN	
																			UPDATE product_specification_stock SET inventorycount=(inventorycount + (tprod_count*1))
																			WHERE type = 1 AND company_id = tcompany_id AND product_id = tpackprod_id AND specification_info_id = tprodspec_id;
																	END IF;
															END IF;	

														end LOOP three_loop;
													CLOSE cur_3;
											end;				
								 
								ELSEIF tprodtype = 0  /*普通商品*/
								THEN 
											IF tprodspec_id = 0 THEN   /**没有开启规格**/
													IF tprod_count>0 THEN	
															UPDATE product SET inventorynumber=(inventorynumber + (tprod_count*1))
															WHERE id = tprod_id;
													END IF;	
											ELSEIF tprodspec_id != 0	THEN	/*该商品开启了规格*/
													IF tprod_count>0 THEN	
															UPDATE product_specification_info SET inventorynumber=(inventorynumber + (tprod_count*1))
															WHERE del_flag = '0' AND product_id = tprod_id AND id = tprodspec_id;
													END if;
											END IF;	
											 
							  END IF;

							end LOOP inner_loop;
						CLOSE cur_2;
					end;					
					update orders set `status`=100,cancel_time = NOW() where id=tid;
					insert into orders_log (order_id,operator,order_status,changed_status,remark,log_time) VALUES (tid,'0','11','100','系统自动取消',now());
				
			/*取下一条记录*/
			SET done=0;
	END LOOP out_loop;
	CLOSE cur_1;

END