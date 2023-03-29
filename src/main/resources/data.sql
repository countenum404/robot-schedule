insert into study_group (id, name) values
(1, 'ПИ-Б19'),
(2, 'ПИ-Б20'),
(3, 'ПИ-Б21');

insert into study_org_users(id, firstname, lastname, login, password, role, surname, study_group_id) values
(1, 'Denis', 'Shabashov', 'shabashov', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Igorevich', 1),
(2, 'Lev', 'Tolstoy', 'ltolstoy', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Nikolayevich', 2),
(3, 'Petr', 'Chaykovskiy', 'tchaikovsky', '$2a$10$TLyfL1qOBn2cf72HkCGCZea6Dq4Ffw0Iq2noNp2PPu.htKtFyfqFi', 'STUDENT', 'Ilyich', 3);