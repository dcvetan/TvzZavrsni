INSERT INTO profile (username, hashed_password, salt)
    VALUES ('admin', '$2a$10$i.6qQwlvBgryEGGUiIBGQumb8IOPujwUzUD4l5o6/RkLAVqg9HlCK', '5eb4e9f3-e845-476f-9c2f-187bb45c664c');

INSERT INTO account (name, amount, type, color, source, profile_id, currency_id)
    VALUES ('Paypal', 0, 'Card', 'primary', 'External', 1, 147);
