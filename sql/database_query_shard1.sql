CREATE DATABASE base_project_shard1;
USE base_project_shard1;
CREATE TABLE base_table (
                            id int NOT NULL,
                            name varchar(255),
                            active bit(1),
                            usn varchar(255),
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            CONSTRAINT PK_Base PRIMARY KEY (id)
);
CREATE DATABASE base_project_shard2;
USE base_project_shard2;
CREATE TABLE base_table (
                            id int NOT NULL,
                            name varchar(255),
                            active bit(1),
                            usn varchar(255),
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            CONSTRAINT PK_Base PRIMARY KEY (id)
);