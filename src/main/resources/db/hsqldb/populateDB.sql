INSERT INTO users VALUES (1,'Christian','Henle','chris','$2a$10$WVPq/GcSLNcKECb.wIfGeuFD0etGZpkgh6cgxdzmdzlBGdgp7Jzh2','christian@test.de','Test GmbH','male','Test Town','asdfstreet 1','33333','016011111111');
INSERT INTO users VALUES (2,'Oliver','Roevekamp','oliver1','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de','asdf GmbH','male','asdftown','asdfstreet 1','33333','016011111111');
INSERT INTO users VALUES (3,'Oliver','Roevekamp','oliver2','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de','asdf GmbH','male','asdftown','asdfstreet 1','33333','016011111111');
INSERT INTO users VALUES (4,'test','test','test','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de','asdf GmbH','male','asdftown','asdfstreet 1','33333','016011111111');
INSERT INTO authority VALUES (1,'ROLE_ADMIN');
INSERT INTO authority VALUES (3,'ROLE_CUSTOMER');
INSERT INTO user_authority VALUES (1,1);
INSERT INTO user_authority VALUES (2,1);
INSERT INTO user_authority VALUES (3,3);
INSERT INTO user_authority VALUES (4,3);
INSERT INTO events VALUES (1, 'TestEvent', '2010-09-07');
INSERT INTO locations VALUES (1, 'TestLocation', 1);
