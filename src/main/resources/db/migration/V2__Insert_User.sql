insert into public.tb_user (username, email, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
values  ('mbailey', 'mbailey0@gmail.com', 'Maxim Bailey', '1777d416365cfc23b45b2636dea245a78562f46d2a55554f86e41cf289e098dad26a0e658bb4f6fd', true, true, true, true),
        ('sdecourt1', 'sdecourt1@gmail.com', 'Stillman Decourt', '1777d416365cfc23b45b2636dea245a78562f46d2a55554f86e41cf289e098dad26a0e658bb4f6fd', true, true, true, true),
        ('mfulop2', 'mfulop2@gmail.com', 'Marielle Fulop', '1777d416365cfc23b45b2636dea245a78562f46d2a55554f86e41cf289e098dad26a0e658bb4f6fd', true, true, true, true);

insert into public.tb_permission (id_permission, description)
values  (1, 'TEACHER');

insert into public.tb_user_permission (id_permission, id_user)
values  (1, 1),
        (1, 2),
        (1, 3);