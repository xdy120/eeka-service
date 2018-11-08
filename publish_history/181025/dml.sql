UPDATE oms_return_notice_order_detail a,
oms_return_order b
SET a.return_order_code = b.return_order_code
WHERE
    a.return_order_id = b.return_order_id;
-- 初始化过账状态
UPDATE oms_dispatch_order
SET post_status = CASE out_status WHEN 1 THEN 1 ELSE 2 END;
UPDATE oms_vip_dispatch
SET post_status = case out_status when 1 then 1 else 2 end;
UPDATE oms_vip_return_notice
SET post_status = case in_status when 1 then 1 else 2 end;
UPDATE oms_return_order main,oms_return_notice_order_detail notice_detail,oms_return_notice_order notice
SET main.post_status = case notice.in_status when 1 then 1 else 2 end
WHERE main.return_order_id=notice_detail.return_order_id AND notice.return_notice_order_id=notice_detail.return_notice_order_id;
UPDATE oms_return_notice_order
SET post_status = CASE in_status WHEN 1 THEN 1 ELSE 2 END;
-- 处理通知单的通知数量大于入库数量
UPDATE oms_return_notice_order_detail
SET in_quantity =
CASE notice_quantity < ( in_quantity + in_substandard_quantity )
	WHEN TRUE THEN
	notice_quantity ELSE in_quantity END;
-- 更新退换货单 通知状态 主单和明细不一致
UPDATE oms_return_order main,(
SELECT
return_order_id,
	CASE COUNT(*)=SUM(is_noticed)
		WHEN TRUE THEN
		3
	ELSE
		if(SUM(is_noticed)=0,1,2)
	END AS	notice
FROM oms_return_order_detail GROUP BY return_order_id
) detail
SET main.notice_status=detail.notice
WHERE main.return_order_id=detail.return_order_id;
