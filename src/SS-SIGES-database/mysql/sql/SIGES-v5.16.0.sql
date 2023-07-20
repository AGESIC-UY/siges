ALTER TABLE roles_interesados ADD COLUMN rolint_habilitado tinyint(3);
UPDATE roles_interesados SET rolint_habilitado = 1;

ALTER TABLE interesados ADD COLUMN int_tipo varchar(15);
UPDATE interesados SET int_tipo = 'EXTERNO';

ALTER TABLE interesados ADD COLUMN int_usuario_fk int(11);
ALTER TABLE interesados ADD CONSTRAINT fk_int_usuario FOREIGN KEY (int_usuario_fk) REFERENCES ss_usuario (usu_id);

