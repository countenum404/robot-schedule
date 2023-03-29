insert into study_group (id, name) values
(1, 'ПИ-Б19'),
(2, 'ПИ-Б20'),
(3, 'ПИ-Б21');

insert into study_org_users(id, firstname, lastname, login, password, role, surname, study_group_id) values
(1, 'Denis', 'Shabashov', 'shabashov', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Igorevich', 1),
(2, 'Lev', 'Tolstoy', 'ltolstoy', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Nikolayevich', 2),
(3, 'Petr', 'Chaykovskiy', 'tchaikovsky', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Ilyich', 3),
(4, 'Mikhail', 'Vasilievich', 'mlomonosov', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'TEACHER', 'Lomonosov', NULL);

insert into subject(id, name) values
(1, 'Высшая Математика'),
(2, 'Системный анализ'),
(3, 'Информационные системы'),
(4, 'Разработка интерфейсов');

insert into schedule_record(id) values (1),(2),(3);

insert into schedule_record_study_group(schedule_record_id, study_group_id) values
(1, 1),
(2, 2),
(3, 3);

insert into schedule_record_subject(schedule_record_id, subject_id) values
(1, 1),
(2, 1),
(3, 1);

insert into schedule_record_teacher(schedule_record_id, teacher_id) values
(1, 4),
(2, 4),
(3, 4);
