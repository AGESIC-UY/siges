ALTER TABLE areas_tags ADD COLUMN areatag_habilitada tinyint(1);
UPDATE areas_tags SET areatag_habilitada = 1;

ALTER TABLE organismos ADD COLUMN org_activo_siges_ind TINYint(1) DEFAULT 0;
