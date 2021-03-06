-- ALTER TABLE public.client DROP CONSTRAINT client_fk0;
-- DROP TABLE public.client;
ALTER TABLE public.bet DROP CONSTRAINT bet_fk0;
ALTER TABLE public.bet DROP CONSTRAINT bet_fk1;
ALTER TABLE public.race_detail DROP CONSTRAINT race_detail_fk0;
ALTER TABLE public.race_detail DROP CONSTRAINT race_detail_fk1;
ALTER TABLE public.race_detail DROP CONSTRAINT race_detail_fk2;
ALTER TABLE public.race_card DROP CONSTRAINT race_card_fk0;
ALTER TABLE public.event DROP CONSTRAINT event_fk0;
DROP TABLE public.bet;
DROP TABLE public.race_detail;
DROP TABLE public.race_card;
DROP TABLE public.event;
-- DROP TABLE public.logging;
DROP TABLE public.racecourse;
DROP TABLE public.horse;
DROP TABLE public.command;
DROP TABLE public.account;

CREATE TABLE "race_card" (
  "id" serial NOT NULL,
  "date_start" timestamptz NOT NULL,
  "date_finish" timestamptz,
  "race_type" character varying(128) NOT NULL,
  "racecourse_id" bigint NOT NULL,
  CONSTRAINT race_card_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "horse" (
  "id" serial NOT NULL,
  "nick_name" character varying(18) NOT NULL,
  "age" int NOT NULL,
  "equipment_weight" int NOT NULL,
  "form_ru" character varying(256),
  "form_en" character varying(256),
  "owner" character varying(256) NOT NULL,
  CONSTRAINT horse_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "command" (
  "id" serial NOT NULL,
  "trainer" character varying(256) NOT NULL,
  "jockey" character varying(256) NOT NULL,
  "url_image_color" character varying(1024) NOT NULL,
  CONSTRAINT command_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "race_detail" (
  "id" serial NOT NULL,
  "race_card_id" bigint NOT NULL,
  "horse_id" bigint NOT NULL,
  "command_id" bigint NOT NULL,
  "number_start_box" int NOT NULL,
  "horse_result" int,
  CONSTRAINT race_detail_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "event" (
  "id" serial NOT NULL,
  "race_detail_id" bigint NOT NULL,
  "event_type" character varying(256) NOT NULL,
  "date_register" timestamptz NOT NULL,
  "coefficient_event" double PRECISION NOT NULL DEFAULT '0',
  "bookmaker" character varying(256) NOT NULL,
  "result_event" character varying(64) NOT NULL,
  CONSTRAINT event_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "bet" (
  "id" serial NOT NULL,
  "date_bet" timestamptz NOT NULL,
  "event_id" bigint NOT NULL,
  "account_id" bigint NOT NULL,
  "sum" double PRECISION NOT NULL,
  "coefficient_bet" double PRECISION NOT NULL,
  "status_bet" character varying(64) NOT NULL,
  "calculate" double PRECISION,
  CONSTRAINT bet_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);


--
-- CREATE TABLE "client" (
--   "id" BIGINT NOT NULL,
--   "first_name" character varying(256) NOT NULL,
--   "last_name" character varying(256) NOT NULL,
--   "date_birth" DATE NOT NULL,
--   "address" character varying(512) NOT NULL,
--   CONSTRAINT client_pk PRIMARY KEY ("id")
-- ) WITH (
-- OIDS=FALSE
-- );



CREATE TABLE "account" (
  "id" SERIAL NOT NULL,
  "login" character varying NOT NULL,
  "password" character varying NOT NULL,
  "data_register_account" timestamptz NOT NULL,
  "status" VARCHAR(16) NOT NULL,
  "balance" double PRECISION NOT NULL,
  "email" character varying(256) NOT NULL,
  "first_name" character varying(256) NOT NULL,
  "last_name" character varying(256) NOT NULL,
  "date_birth" DATE NOT NULL,
  "address" character varying(512) NOT NULL,
  "is_delete" BOOLEAN NOT NULL,
  CONSTRAINT account_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);

--
-- CREATE TABLE "account" (
--   "id" SERIAL NOT NULL,
--   "login" character varying NOT NULL,
--   "password" character varying NOT NULL,
--   "security_level_id" int NOT NULL,
--   "balance" double PRECISION NOT NULL,
--   "email" character varying(256) NOT NULL,
--   CONSTRAINT account_pk PRIMARY KEY ("id")
-- ) WITH (
-- OIDS=FALSE
-- );


--
-- CREATE TABLE "security_level" (
--   "id" serial NOT NULL,
--   "status" character varying(64) NOT NULL,
--   CONSTRAINT security_level_pk PRIMARY KEY ("id")
-- ) WITH (
-- OIDS=FALSE
-- );



CREATE TABLE "racecourse" (
  "id" serial NOT NULL,
  "name" character varying(256) NOT NULL,
  "country" character varying(256) NOT NULL,
  CONSTRAINT racecourse_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);
--
-- CREATE TABLE "logging" (
--   ID SERIAL NOT NULL,
--   EVENT_DATE TIMESTAMP DEFAULT NULL,
--   LEVEL varchar DEFAULT NULL,
--   LOGGER varchar DEFAULT NULL,
--   MSG varchar DEFAULT NULL,
--   THROWABLE varchar DEFAULT NULL,
--   PRIMARY KEY (ID)
-- );
--


ALTER TABLE "race_card" ADD CONSTRAINT "race_card_fk0" FOREIGN KEY ("racecourse_id") REFERENCES "racecourse"("id");

-- ALTER TABLE "horse" ADD CONSTRAINT "horse_fk0" FOREIGN KEY ("command_id") REFERENCES "command"("id");
-- ALTER TABLE "command" ADD CONSTRAINT "command_fk0" FOREIGN KEY ("id") REFERENCES "horse"("id");

ALTER TABLE "race_detail" ADD CONSTRAINT "race_detail_fk0" FOREIGN KEY ("race_card_id") REFERENCES "race_card"("id");
ALTER TABLE "race_detail" ADD CONSTRAINT "race_detail_fk1" FOREIGN KEY ("horse_id") REFERENCES "horse"("id");
ALTER TABLE "race_detail" ADD CONSTRAINT "race_detail_fk2" FOREIGN KEY ("command_id") REFERENCES "command"("id");

ALTER TABLE "event" ADD CONSTRAINT "event_fk0" FOREIGN KEY ("race_detail_id") REFERENCES "race_detail"("id");

ALTER TABLE "bet" ADD CONSTRAINT "bet_fk0" FOREIGN KEY ("event_id") REFERENCES "event"("id");
ALTER TABLE "bet" ADD CONSTRAINT "bet_fk1" FOREIGN KEY ("account_id") REFERENCES "account"("id");


-- ALTER TABLE "client" ADD CONSTRAINT "client_fk0" FOREIGN KEY ("id") REFERENCES "account"("id");
-- ALTER TABLE "account" ADD CONSTRAINT "account_fk1" FOREIGN KEY ("security_level_id") REFERENCES "security_level"("id");

ALTER TABLE "bet" ADD UNIQUE ( event_id , account_id );
ALTER TABLE "command" ADD UNIQUE ( trainer,jockey,url_image_color );
ALTER TABLE "race_detail" ADD UNIQUE ( race_card_id , horse_id );
ALTER TABLE "race_detail" ADD UNIQUE ( race_card_id , number_start_box );
ALTER TABLE "race_detail" ADD UNIQUE ( race_card_id , command_id );
ALTER TABLE "event" ADD UNIQUE ( race_detail_id , event_type );

CREATE UNIQUE INDEX account_login_uindex ON account (login);
-- CREATE UNIQUE INDEX security_level_status_uindex ON security_level (status);
CREATE UNIQUE INDEX horse_nick_name_uindex ON public.horse (nick_name);
CREATE UNIQUE INDEX racecourse_name_uindex ON public.racecourse (name);



INSERT INTO account (login,password, data_register_account,status,balance,email,first_name,last_name,date_birth,address,is_delete)
VALUES ('log','cGFz','10.12.2016','ROLE_ADMIN',100.0,'a@r.ru','first1','last1','10.12.2016','address1',false);
INSERT INTO account (login,password, data_register_account,status,balance,email,first_name,last_name,date_birth,address,is_delete)
VALUES ('log2','cGFzMg==','10.03.2016','ROLE_USER',50.0,'a@r1.ru','first2','last2','10.12.2016','address2',false);
INSERT INTO account (login,password, data_register_account,status,balance,email,first_name,last_name,date_birth,address,is_delete)
VALUES ('log3','cGFzMw==','10.02.2016','ROLE_USER',30.0,'a@r2.ru','first3','last3','10.12.2016','address2',false);

-- INSERT INTO "client" (id,first_name, last_name,date_birth,address) VALUES (1,'Fedor','Gvin','10.12.2016','les');

INSERT INTO command (trainer,jockey,url_image_color) VALUES ('jon','uri','http1');
INSERT INTO command (trainer,jockey,url_image_color) VALUES ('igi','mor','http2');
INSERT INTO command (trainer,jockey,url_image_color) VALUES ('петя','коля','http3');
INSERT INTO command (trainer,jockey,url_image_color) VALUES ('петя1','коля1','http4');

INSERT INTO horse (nick_name,age,equipment_weight,form_ru,form_en,owner)
VALUES ('faster1',3,60,'черный','black','henk1');
INSERT INTO horse (nick_name,age,equipment_weight,form_ru,form_en,owner)
VALUES ('faster2',2,61,'белый','white','henk2');
INSERT INTO horse (nick_name,age,equipment_weight,form_ru,form_en,owner)
VALUES ('faster3',2,62,'коричневый','brown','henk3');
INSERT INTO horse (nick_name,age,equipment_weight,form_ru,form_en,owner)
VALUES ('faster4',2,62,'серый','gray','henk4');

INSERT INTO racecourse (name, country) VALUES ('germ','g');
INSERT INTO racecourse (name, country) VALUES ('germ1','g');

INSERT INTO race_card (date_start,date_finish,race_type,racecourse_id) VALUES ('2016.12.18 11:00',NULL ,'1000',1);
INSERT INTO race_card (date_start,date_finish,race_type,racecourse_id) VALUES ('2016.12.18 11:10',NULL ,'1500',1);
INSERT INTO race_card (date_start,date_finish,race_type,racecourse_id) VALUES ('2016.12.18 11:10',NULL ,'1500',1);
INSERT INTO race_card (date_start,date_finish,race_type,racecourse_id) VALUES ('2016.11.06 23:10',NULL ,'1500',1);
INSERT INTO race_card (date_start,date_finish,race_type,racecourse_id) VALUES ('2016.11.30 12:10',NULL ,'1500',2);

INSERT INTO race_detail (race_card_id,horse_id, command_id,number_start_box,horse_result) VALUES (1,1,1,1,NULL );
INSERT INTO race_detail (race_card_id,horse_id, command_id,number_start_box,horse_result) VALUES (2,2,2,2,NULL);
-- INSERT INTO race_detail (race_card_id,horse_id, command_id,number_start_box,horse_result) VALUES (1,3,3,3,NULL);

INSERT INTO event (race_detail_id,event_type,date_register,coefficient_event, result_event,bookmaker)
VALUES (1,'WIN','2016.11.25 04:12',2.6,'UNKNOWN','dred');
INSERT INTO event (race_detail_id,event_type,date_register,coefficient_event,result_event,bookmaker)
VALUES (2,'PLACE2','2016.11.25 04:12',1.6,'UNKNOWN','dred');
INSERT INTO event (race_detail_id,event_type,date_register,coefficient_event,result_event,bookmaker)
VALUES (2,'WIN','2016.11.25 04:04',1.5,'UNKNOWN','dred');
INSERT INTO event (race_detail_id,event_type,date_register,coefficient_event,result_event,bookmaker)
VALUES (1,'PLACE2','2016.11.16 04:50',0.6,'UNKNOWN','dred');

INSERT INTO bet (date_bet,event_id,account_id,sum,coefficient_bet,status_bet,calculate) VALUES ('2016.11.25 20:12',1,1,10,1,'ACTIVE',0);
INSERT INTO bet (date_bet,event_id,account_id,sum,coefficient_bet,status_bet,calculate) VALUES ('2016.11.25 20:12',2,1,20,1,'ACTIVE',0);
INSERT INTO bet (date_bet,event_id,account_id,sum,coefficient_bet,status_bet,calculate) VALUES ('2016.11.08 10:12',3,2,5,2.0,'CLOSED',10);
