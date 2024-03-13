CREATE or replace VIEW `beneficiary_lists_view` AS
SELECT
    bl.id,
    a.name AS activity_name,
    dio.name AS diocese_name,
    z.name AS zone_name,
    p.name AS parish_name,
    b.household as beneficiary_household,
    b.male AS beneficiary_male,
    b.female AS beneficiary_female,
    d.male AS disability_male,
    d.female AS disability_female,
    v.under18male,
    v.under18female,
    v.over60male,
    v.over60female,
    bl.is_delete
FROM
    beneficiary_lists bl
        INNER JOIN activities a ON a.id = bl.activity_id
        INNER JOIN parishes p ON p.id = bl.parish_id
        INNER JOIN townships t ON t.id = p.township_id
        INNER JOIN zones z ON z.id = t.zone_id
        INNER JOIN dioceses dio ON dio.id = z.diocese_id
        INNER JOIN beneficiaries b ON b.id = bl.beneficiary_id
        INNER JOIN disabilities d ON d.id = bl.disability_id
        INNER JOIN vulnerabilities v ON v.id = bl.vulnerability_id;

