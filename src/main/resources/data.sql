INSERT INTO user(name, password, type) VALUES
('ddw', '123', 'student'),
('xbp', '123', 'student'),
('cjy', '123', 'student'),
('admin', 'password', 'teacher');

INSERT INTO exam(name, `desc`, price) VALUES
('CET4-2023-spring', 'Nothing special.', '4.98'),
('CET6-2023-fall', 'Nothing special but much more expensive.', '9.98');

INSERT INTO question(`exam_id`, `number`, `is_choice`, `desc`, `std_answer`, `max_score`) VALUES
('1', '1', '1', 'cet4 choice test 1:\nA: \nB: \nC: ', 'A', '10'),
('1', '2', '0', 'cet4 essay test 1:\nYour friend Li Hua ...', 'Example essay: ...', '90'),
('2', '1', '1', 'cet6 choice test 1:\nA: \nB: \nC: ', 'A', '10'),
('2', '2', '1', 'cet6 choice test 2:\nA: \nB: \nC: ', 'A', '10'),
('2', '3', '0', 'cet6 essay test 1:\nYour friend Li Hua ...', 'Example essay: ...', '80');

INSERT INTO sign_up(user_id, exam_id, finish) VALUES
('1', '1', '0'),
('1', '2', '0');

INSERT INTO answer(user_id, question_id, answer, score) VALUES
('1', '1', 'A', NULL),
('1', '2', 'Dear Li Hua, ...', NULL),
('1', '3', 'A', '10'),
('1', '4', 'B', '0'),
('1', '5', '... Yours sincerely ddw', '20');
