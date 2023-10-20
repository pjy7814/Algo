-- 코드를 입력하세요
SELECT p.PRODUCT_CODE, (p.PRICE * SUM(SALES_AMOUNT))as SALES
from PRODUCT p right join OFFLINE_SALE s
on p.PRODUCT_ID = s.PRODUCT_ID
group by p.PRODUCT_ID
order by SALES desc, p.PRODUCT_CODE;