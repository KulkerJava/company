INSERT INTO workers( name, hiredate, role_id, dept_id ) VALUES
( ?, ?,
(SELECT id FROM roles WHERE role = ?),
(SELECT id FROM depths WHERE dept = ?));

