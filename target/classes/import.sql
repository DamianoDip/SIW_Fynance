insert into ticker (id, attuale , max,  min, ticker_name) values (nextval('ticker_seq') , 150 , 350000 , 50 , 'Tsla' );
insert into ticker (id, attuale , max,  min, ticker_name) values (nextval('ticker_seq') , 150 , 500000 , 50 , 'Appl' );
insert into ticker (id, attuale , max,  min, ticker_name) values (nextval('ticker_seq') , 150 , 300000 , 50 , 'Mcrsft' );
insert into ticker (id, attuale , max,  min, ticker_name) values (nextval('ticker_seq') , 150 , 15000 , 50 , 'Pftz' );

-- insert into azienda (id , azioni_emesse , capitalizzazione , name , volume  ) values (nextval('azienda_seq') , 20000 , 8897 , 'Tesla', 200 ) ;  
-- insert into azienda (id , azioni_emesse , capitalizzazione , name , volume  ) values (nextval('azienda_seq') , 20000 , 1232 , 'Apple', 500 ) ;
-- insert into azienda (id , azioni_emesse , capitalizzazione , name , volume  ) values (nextval('azienda_seq') , 20000 , 4721 , 'Microsoft', 6700 ) ;
-- insert into azienda (id , azioni_emesse , capitalizzazione , name , volume  ) values (nextval('azienda_seq') , 20000 , 5012 , 'Pfitzer', 8900 ) ;

insert into portafoglio (id , name  ) values (nextval('ticker_seq') , 'Portafoglio di Damiano') ;
insert into portafoglio (id , name  ) values (nextval('ticker_seq') , 'Portafoglio tech') ;
insert into portafoglio (id , name  ) values (nextval('ticker_seq') , 'lista desideri') ;