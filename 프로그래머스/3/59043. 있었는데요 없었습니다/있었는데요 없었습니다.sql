-- 코드를 입력하세요
SELECT i.animal_id, i.name
from ANIMAL_INS as i join ANIMAL_OUTS as o
on i.ANIMAL_ID = o.ANIMAL_ID
where i.DATETIME > o.DATETIME
order by i.DATETIME;