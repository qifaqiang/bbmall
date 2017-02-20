
/********�Զ���ɶ��� �洢����********************/
BEGIN
	DECLARE tid INT;
	DECLARE done INT DEFAULT 0;/*�Ƿ�ﵽ��¼��ĩβ���Ʊ���*/
	
	DECLARE tprod_id INT;DECLARE tprod_count INT;DECLARE tprodspec_id INT;DECLARE tprodtype INT;DECLARE tprodisspec INT;DECLARE tpackprod_id INT;
	DECLARE torder_id INT;DECLARE tcompany_id INT;DECLARE tinventorynumber INT;	

	/*��ȡ�����Ѿ���ʱ����δ����Ķ���*/
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
			/*��ȡ��һ����¼*/

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

								SELECT type,IFNULL(is_specification,0) into tprodtype,tprodisspec FROM product where id=tprod_id;
								
								IF tprodtype = 1  /*�����Ʒ*/
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

															IF tprodisspec = 0 THEN   /**û�п������**/
																	IF tprod_count>0 THEN	
																			INSERT INTO product_basic_statistics_all (sales_count,company_id,addtime,prod_id,spec_info_id,type)
																			VALUES (tprod_count,tcompany_id,now(),tpackprod_id,0,0);
																	END IF;	
															ELSEIF tprodisspec = 1	THEN	/*����Ʒ�����˹��*/
																	IF tprod_count>0 THEN	
																			INSERT INTO product_basic_statistics_all (sales_count,company_id,addtime,prod_id,spec_info_id,type)
																			VALUES (tprod_count,tcompany_id,now(),tpackprod_id,tprodspec_id,1);
																	END if;
															END IF;	
															/***��а��������Ʒ������Ӧ������**/
															IF tprod_count>0 THEN													
																	UPDATE product_statistics SET sales_count=sales_count+tprod_count WHERE prod_id=tpackprod_id;							
															END IF; 

														end LOOP three_loop;
													CLOSE cur_3;
											end;	
											/**����а��������Ʒ�����Ժ��ٰѸ���а���¼һ��***/
											IF tprod_count>0 THEN	
													INSERT INTO product_basic_statistics_all (sales_count,company_id,addtime,prod_id,spec_info_id,type)
													VALUES (tprod_count,tcompany_id,now(),tprod_id,0,0);
											END IF;
											
								ELSEIF tprodtype = 0  /*��ͨ��Ʒ*/
								THEN 
																					
											IF tprodspec_id = 0 THEN   /**û�п������**/
													IF tprod_count>0 THEN	
															INSERT INTO product_basic_statistics_all (sales_count,company_id,addtime,prod_id,spec_info_id,type)
															VALUES (tprod_count,tcompany_id,now(),tprod_id,0,0);
													END IF;	
											ELSEIF tprodspec_id != 0	THEN	/*����Ʒ�����˹��*/
													IF tprod_count>0 THEN	
															INSERT INTO product_basic_statistics_all (sales_count,company_id,addtime,prod_id,spec_info_id,type)
															VALUES (tprod_count,tcompany_id,now(),tprod_id,tprodspec_id,1);
													END if;
											END IF;	
											
							  END IF;

								select tprod_id;
								IF tprod_count>0 THEN													
										UPDATE product_statistics SET sales_count=sales_count+tprod_count WHERE prod_id=tprod_id;							
								END IF; 

							end LOOP inner_loop;
						CLOSE cur_2;
					end;					
					update orders set `is_over`=1,over_time=now() where id=tid;		
			
				
			/*ȡ��һ����¼*/
			SET done=0;
	END LOOP out_loop;
	CLOSE cur_1;
END