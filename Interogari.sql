drop table game;
drop table move;
drop table player;

drop sequence id_play;
drop sequence id_game;

create table player(
    id integer not null,
    name varchar(200) not null,
    CONSTRAINT player_pk PRIMARY KEY (id)
);


create table game(
    id integer not null,
    player1_id integer not null,
    player2_id integer not null,
    CONSTRAINT game_pk PRIMARY KEY (id),
	CONSTRAINT 
     fk_player1 FOREIGN KEY (player1_id) 
           REFERENCES player(id) ON DELETE CASCADE,
	CONSTRAINT 
     fk_player2 FOREIGN KEY (player2_id) 
           REFERENCES player(id) ON DELETE CASCADE	   
);

create table move (
	id integer not null,
	player_id integer not null,
	line integer not null,
	col integer not null,
	CONSTRAINT 
     fk_move FOREIGN KEY (player_id) 
           REFERENCES player(id) ON DELETE CASCADE
);
	
	
CREATE SEQUENCE id_play
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE id_game
START WITH 1
INCREMENT BY 1;

commit;








