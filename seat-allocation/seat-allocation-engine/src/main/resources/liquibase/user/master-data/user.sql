INSERT INTO user (xid,
                  user_id, first_name, middle_name, last_name, user_name,
                  password, email, contact_number,
                  address,
                  gender,
                  image_url, user_type, managers_eid, is_active)
VALUES (UUID(),
        1457, "kavindu", "thenuka", "mandawela", "kThenuka@Nimbus",
        "ghvuihgizklb514564f6", "kaavinu@gmail.com", "+94776219845",
        "No11,Eliot road,Matara", 'M', "https://dms.uom.lk/s/yyZXDdWXNkHnr4r/preview", 2, 145, 1),

       (UUID(),
        1444, "Ravindu", "Udayanga", "Mahanama", "RUdayanga@Nimbus",
        "ravindu123@", "Ravindu@gmail.com", "+94771223455",
        "No54,Galle road,Matara", 'M', "https://dms.uom.lk/s/n7ST4JG4eBbe8cy/preview", 1, 145, 1);



