# Spring Boot rest Student CRUD API
## Prerequisite
#### SQL server installed
* Open SSMS (Microsoft SQL Server Management Studio)
* logged in as sysadmin
* Create database CHANGE_DATA_CAPTURE
* User - cdc_user (db_owner)
* Password - cdc@12345
* Create schema cdc_schema (cdc_user as schema owner)
* Create table Student
~~~
CREATE TABLE CHANGE_DATA_CAPTURE.cdc_schema.TBL_STUDENT (
	ID bigint IDENTITY(1,1) NOT NULL,
	FIRST_NAME nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	LAST_NAME nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	MIDDLE_NAME nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	COURSE nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
);
~~~
#### REST API
~~~
[POST] http://localhost:1000/source/api/v1/student
{
    "firstName": "Shaikh",
    "lastName": "Tousif",
    "middleName": "Iqbal",
    "course": "CS"
}
~~~
