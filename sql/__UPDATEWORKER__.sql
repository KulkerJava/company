UPDATE workers
SET name = ?, hiredate = ?, role_id = ( SELECT id FROM roles WHERE role = ? ), dept_id = ( SELECT id FROM depths WHERE dept = ? )
WHERE name = ?;

