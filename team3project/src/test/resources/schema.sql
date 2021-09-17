create table oldbook (
     ob_no bigint not null,
     ob_author varchar(255),
     ob_bdate varchar(255),
     ob_comment varchar(255),
     ob_comp varchar(255),
     ob_ddate varchar(255),
     ob_donor varchar(255),
     ob_genre varchar(255),
     ob_image varchar(255),
     ob_inter varchar(255),
     ob_name varchar(255),
     ob_price bigint,
     ob_readcnt bigint,
     ob_scount bigint,
     ob_state varchar(255),
     ob_userid varchar(255),
     primary key (ob_no)
);