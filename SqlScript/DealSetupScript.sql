create table deal (
       deal_id integer not null auto_increment,
        client_contact varchar(255),
        contract_id integer,
        deal_name varchar(255),
        product_id integer,
        status varchar(255),
        deal_ingestion_id integer,
        primary key (deal_id)
    );
    
    create table deal_ingestion (
       deal_ingestion_id integer not null auto_increment,
        filelocation varchar(255),
        file_type varchar(255),
        source varchar(255),
        source_data varchar(255),
        status varchar(255),
        primary key (deal_ingestion_id)
    );  
    
    create table loan (
       loan_id integer not null auto_increment,
        loan_data varchar(255),
        deal_ingestion_id integer,
        primary key (loan_id)
    ); 
    
    create table loan_raw_data (
       loan_id integer not null auto_increment,
        loan_data varchar(255),
        test_data varchar(255),
        deal_id integer,
        primary key (loan_id)
    ); 
    
    alter table deal 
       add constraint FK5tubbltsdbgiqeiifey89npmf 
       foreign key (deal_ingestion_id) 
       references deal_ingestion (deal_ingestion_id);
 
    
    alter table loan 
       add constraint FK5v54k4374qas8yt8m5hu6rsk2 
       foreign key (deal_ingestion_id) 
       references deal_ingestion (deal_ingestion_id);

    
    alter table loan_raw_data 
       add constraint FKr4qxeh2l8usvx2mctjvnx45uv 
       foreign key (deal_id) 
       references deal (deal_id);