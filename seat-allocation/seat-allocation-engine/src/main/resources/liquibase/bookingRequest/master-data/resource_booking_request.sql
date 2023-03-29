-- INSERT INTO resource_booking_request(
--     resource_id,
--     requester_user_id,
--     requester_manager_user_id,
--     company_id ,
--     required_date ,
--     start_time,
--     end_time,
--     status  )
-- VALUES (
--            4578,
--            2417,
--            8421,
--            3254,
--            DATE '2015-12-17',
--            '13:30',
--            '15:45',
--           'pending'
--        );

insert into resource_booking_request(resource_id, requester_user_id, requester_manager_user_id, company_id,
                                     required_date, start_time, end_time, status)
values ('1', '1', '4', '1', '2023-01-01', '13:30:00', '13:45:00', 'pending'),
       ('2', '2', '4', '1', '2023-01-10', '14:30:00', '15:45:00', 'pending'),
       ('2', '2', '4', '2', '2023-01-21', '10:30:00', '9:30:00', 'pending'),
       ('3', '3', '4', '3', '2023-02-10', '11:00:00', '11:45:00', 'pending'),
       ('3', '3', '4', '3', '2023-03-01', '15:30:00', '16:50:00', 'pending'),
       ('1', '2', '4', '6', '2023-01-21', '10:30:00', '9:30:00', 'pending'),
       ('5', '3', '4', '6', '2023-02-10', '11:00:00', '11:45:00', 'pending')