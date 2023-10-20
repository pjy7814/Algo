-- 코드를 입력하세요
SELECT j.FLAVOR
from JULY j join FIRST_HALF f
on j.FLAVOR = f.FLAVOR
group by j.FLAVOR
order by SUM(j.TOTAL_ORDER + f.TOTAL_ORDER) desc
limit 3;