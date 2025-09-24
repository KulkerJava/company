SELECT name, hiredate, role, dept FROM workers
INNER JOIN roles ON workers.role_id = roles.id
INNER JOIN depths ON workers.dept_id = depths.id;

