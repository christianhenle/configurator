DROP TABLE USERS 			IF EXISTS;
DROP TABLE ROLES 			IF EXISTS;
DROP TABLE PERMISSIONS		IF EXISTS;
DROP TABLE USER_ROLES		IF EXISTS;
DROP TABLE ROLE_PERMISSIONS	IF EXISTS;


CREATE TABLE USERS(
    ID INTEGER NOT NULL IDENTITY PRIMARY KEY,
    VERSION TIMESTAMP,
    ENABLED BOOLEAN,
    PASSWORD VARCHAR(150) NOT NULL,
    USERNAME VARCHAR(50) NOT NULL
);

CREATE TABLE ROLES(
    ID INTEGER NOT NULL IDENTITY PRIMARY KEY,
    VERSION TIMESTAMP,
    ROLENAME VARCHAR(50) NOT NULL
);

CREATE TABLE PERMISSIONS(
    ID INTEGER NOT NULL IDENTITY PRIMARY KEY,
    VERSION TIMESTAMP,
    PERMISSIONNAME VARCHAR(50) NOT NULL
);

CREATE TABLE USER_ROLES(
    ROLE_ID INTEGER NOT NULL,
    USER_ID INTEGER NOT NULL
);

CREATE TABLE ROLE_PERMISSIONS(
    PERMISSION_ID INTEGER NOT NULL,
    ROLE_ID INTEGER NOT NULL
);

             