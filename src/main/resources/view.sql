create or replace VIEW `annual_returns_view` AS
select ar.id,
       p.name                                         as parish_name,
       ar.year,
       ar.total_actual_resident,
       bc.indigenous,
       bc.not_indigenous,
       (bc.indigenous + bc.not_indigenous)            as bc_total,
       nc.non_christian,
       nc.non_catholic_christian,
       (nc.non_christian + nc.non_catholic_christian) as nc_total,
       ar.catechumen,
       ar.chapel,
       ar.visited_place,
       pr.male,
       pr.female,
       c.paid,
       c.voluntary,
       c.home_missioner,
       s.major,
       s.minor,
       rl.male                                        as rl_male,
       rl.female                                      as rl_female,
       b.above8year,
       b.up_to7year,
       m.between_catholic,
       m.with_non_catholic_christian,
       m.with_non_christian,
       d.priest,
       d.religious,
       d.catholic,
       i.seminary,
       i.novitiate,
       i.catechist,
       i.training_for_home_missioner,
       i.boarding_for_poor,
       i.poor,
       i.orphan,
       po.religious_profession,
       po.graduated,
       e.basic,
       e.middle,
       e.high,
       e.university,
       ra.retreats,
       ra.recollections,
       ra.seminar,
       ra.other,
       ca.legion_of_mary,
       ca.st_vincent_de_paul,
       ca.parish_elder,
       ca.youth_group,
       ca.mother_guide,
       ca.other                                       AS ca_other,
       ar.project,
       ar.matrimonial_cases,
       ar.created_date,
       u.username                                     as created_user,
       ar.is_delete


from annual_returns ar
         inner join parishes p on ar.parish_id = p.id
         inner join baptisms b on ar.baptism_id = b.id
         inner join baptize_catholics bc on ar.baptized_catholic_id = bc.id
         inner join catechists c on ar.catechist_id = c.id
         inner join catholic_actions ca on ar.catholic_action_id = ca.id
         inner join deaths d on ar.death_id = d.id
         inner join educations e on ar.education_id = e.id
         inner join institutes i on ar.institute_id = i.id
         inner join marriages m on ar.marriage_id = m.id
         inner join non_catholics nc on ar.non_catholic_id = nc.id
         inner join priests pr on ar.priest_id = pr.id
         inner join priestly_ordinations po on ar.priestly_ordination_id = po.id
         inner join religious_activities ra on ar.religious_activity_id = ra.id
         inner join religious_lives rl on ar.religious_life_id = rl.id
         inner join seminarians s on ar.seminarian_id = s.id
         inner join users u on ar.created_user_id = u.id;

CREATE or replace VIEW `beneficiary_lists_view` AS
SELECT bl.id,
       bl.created_date,
       u.username  as created_user,
       a.name      AS activity_name,
       ardio.name  AS archdiocese_name,
       dio.name    AS diocese_name,
       z.name      AS zone_name,
       p.name      AS parish_name,
       b.household as beneficiary_household,
       b.male      AS beneficiary_male,
       b.female    AS beneficiary_female,
       d.male      AS disability_male,
       d.female    AS disability_female,
       v.under18male,
       v.under18female,
       v.over60male,
       v.over60female,
       bl.is_delete
FROM beneficiary_lists bl
         INNER JOIN users u On bl.user_id = u.id
         INNER JOIN activities a ON a.id = bl.activity_id
         INNER JOIN parishes p ON p.id = bl.parish_id
         INNER JOIN townships t ON t.id = p.township_id
         INNER JOIN zones z ON z.id = t.zone_id
         INNER JOIN dioceses dio ON dio.id = z.diocese_id
         inner join archdioceses ardio ON ardio.id = dio.archdiocese_id
         INNER JOIN beneficiaries b ON b.id = bl.beneficiary_id
         INNER JOIN disabilities d ON d.id = bl.disability_id
         INNER JOIN vulnerabilities v ON v.id = bl.vulnerability_id;



CREATE or replace VIEW `counts_view` AS
SELECT row_number() OVER ()                                                        AS id,
       (SELECT COUNT(*) FROM `spcu-dims`.archdioceses where is_delete = b'0')      AS archdiocese,
       (SELECT COUNT(*) FROM `spcu-dims`.dioceses where is_delete = b'0')          AS diocese,
       (SELECT COUNT(*) FROM `spcu-dims`.zones where is_delete = b'0')             AS zone,
       (SELECT COUNT(*) FROM `spcu-dims`.parishes where is_delete = b'0')          AS parish,
       (SELECT COUNT(*) FROM `spcu-dims`.regions where is_delete = b'0')           AS region,
       (SELECT COUNT(*) FROM `spcu-dims`.districts where is_delete = b'0')         AS district,
       (SELECT COUNT(*) FROM `spcu-dims`.townships where is_delete = b'0')         AS township,
       (SELECT COUNT(*) FROM `spcu-dims`.activities where is_delete = b'0')        AS activity,
       (SELECT COUNT(*) FROM `spcu-dims`.beneficiary_lists where is_delete = b'0') AS beneficiary_list;



