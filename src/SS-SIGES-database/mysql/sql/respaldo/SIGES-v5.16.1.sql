ALTER TABLE notificacion ADD COLUMN not_asunto varchar(255);
UPDATE notificacion set not_asunto = 'Notificación';
