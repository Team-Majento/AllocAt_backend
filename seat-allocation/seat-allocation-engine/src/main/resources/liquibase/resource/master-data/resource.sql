INSERT INTO resource (xid,
                      resource_type_id,
                      rate_card_id,
                      building_id,
                      floor,
                      is_available,
                      is_active,
                      maximum_capacity, description, img_Url)
VALUES (UUID(),
        1,
        8417,
        '847N',
        1, 1, 1,
        25, 'sample descriptionA', 'imgUrl_A');