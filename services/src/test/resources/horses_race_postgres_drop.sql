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
DROP TABLE public.racecourse;
DROP TABLE public.horse;
DROP TABLE public.command;
DROP TABLE public.account;
DROP TABLE public.logging;