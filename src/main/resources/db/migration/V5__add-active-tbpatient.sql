alter table tb_patient add active tinyint;
update tb_patient set active = 1;