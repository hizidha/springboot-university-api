-- INSERT INTO student table
INSERT INTO student (registration_number, name, email, phone_number, created_at, updated_at) VALUES
                                                                                                 ('1234567890123', 'Alice Johnson', '1234567890123@students.bjiruniversity.ac.id', '012345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('9876543210123', 'Bob Smith', '9876543210123@students.bjiruniversity.ac.id', '0987654321012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('1122334455667', 'Charlie Brown', '1122334455667@students.bjiruniversity.ac.id', '014155552671', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('2233445566778', 'David Williams', '2233445566778@students.bjiruniversity.ac.id', '012345678912', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('3344556677889', 'Eva Green', '3344556677889@students.bjiruniversity.ac.id', '0987654321012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('4455667788990', 'Frank Ocean', '4455667788990@students.bjiruniversity.ac.id', '0555123456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('5566778899001', 'Grace Lee', '5566778899001@students.bjiruniversity.ac.id', '0147258369021', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('6677889900112', 'Henry Ford', '6677889900112@students.bjiruniversity.ac.id', '016475556667', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('7788990011223', 'Ivy White', '7788990011223@students.bjiruniversity.ac.id', '0012345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                 ('8899001122334', 'Jack Black', '8899001122334@students.bjiruniversity.ac.id', '0198765432109', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- INSERT INTO teacher table
INSERT INTO teacher (national_teacher_number, name, work_unit, email, phone_number, created_at, updated_at) VALUES
                                                                                                                ('123456789012345', 'Alice Johnson', 'Mathematics Department', 'alice@lecture.bjiruniversity.ac.id', '012345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('987654321098765', 'Bob Smith', 'Physics Laboratory', 'bobsmith@lecture.bjiruniversity.ac.id', '0987654321012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('112233445566778', 'Charlie Brown', 'Chemistry Lab', 'charlie@lecture.bjiruniversity.ac.id', '014155552671', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('223344556677889', 'David Williams', 'Biology Unit', 'david@lecture.bjiruniversity.ac.id', '012345678912', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('334455667788990', 'Eva Green', 'Computer Science', 'eva@lecture.bjiruniversity.ac.id', '0987654321012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('445566778899001', 'Frank Ocean', 'History Section', 'frank@lecture.bjiruniversity.ac.id', '0555123456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('556677889900112', 'Grace Lee', 'Literature Club', 'grace@lecture.bjiruniversity.ac.id', '0147258369021', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('667788990011223', 'Henry Ford', 'Engineering Dept', 'henry@lecture.bjiruniversity.ac.id', '016475556667', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('778899001122334', 'Ivy White', 'Philosophy', 'ivy@lecture.bjiruniversity.ac.id', '0012345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('889900112233445', 'Jack Black', 'Art Studies', 'jack@lecture.bjiruniversity.ac.id', '0198765432109', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- INSERT INTO course table
INSERT INTO course (name, credit, created_at, updated_at) VALUES
                                                              ('Introduction to Programming', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Calculus I', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Basic Physics', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('English', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Physics I', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Electronic Sensing', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Statistics', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Data Structures', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Internet of Things', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                              ('Research Methodology', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
