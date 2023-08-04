create table if not exists Content (
   id serial not null primary key,
   title varchar(255) not null,
   description TEXT,
   status varchar(20) not null,
   content_type varchar(50) not null,
   date_created timestamp not null,
   date_updated timestamp,
   url varchar(255)
);

--insert into Content(title, description, status, content_type, date_created)
--values ('My Spring Data Blog Post', 'A post about spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP);

/*
CREATE TABLE IF NOT EXISTS Content (
    id SERIAL not null primary key,
    title VARCHAR(255) NOT NULL,
    desc text,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(255)
);

INSERT INTO Content(title, desc, status, content_type, date_created)
VALUES ('My Spring Data Blog Post', 'A post about spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP);*/
