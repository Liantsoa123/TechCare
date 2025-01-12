create
    or replace  view  v_repair_info as
select r.repair_id          as repair_id,
       l.laptop_id          as laptop_id,
       c.component_id       as new_component_id,
       lc.component_id      as old_component_id,
       tc.type_component_id as type_component_id,
       lt.laptop_type_id    as type_laptop_id,
       lc.quantity as old_quantity,
       rd.quantity as new_quantity
from repair r
         join repair_details rd on r.repair_id = rd.repair_id
         join component c on rd.component_id = c.component_id
         join type_component tc on c.type_component_id = tc.type_component_id
         join laptop l on r.laptop_id = l.laptop_id
         join laptop_component lc on l.laptop_id = lc.laptop_id
         join laptop_type lt on l.laptop_type_id = lt.laptop_type_id