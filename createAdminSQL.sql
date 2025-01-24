INSERT INTO public.users(
	dateofbirth, email, password, username)
	VALUES (1990, 'admin@mail.ru', '1234', 'admin');

INSERT INTO public.roles("role")
	VALUES('ROLE_ADMIN');

INSERT INTO public.roles("role")
	VALUES('ROLE_USER');

INSERT INTO public.users_roles(
	users_id, roles_id)
	VALUES (1, 1);

INSERT INTO public.users_roles(
	users_id, roles_id)
	VALUES (1, 2);