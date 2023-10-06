# Spring Boot Rest Student CRUD Application
## Prerequisite
#### SQL server installed
* Open SSMS (Microsoft SQL Server Management Studio)
* logged in as sysadmin
* Create database CHANGE_DATA_CAPTURE
* Create User
  * User Name - cdc_user (default database CHANGE_DATA_CAPTURE)
  * Password - cdc@12345
* Create schema cdc_schema (cdc_user as schema owner)
* Create table Student
~~~
spring:
  datasource:
    password: cdc@12345
    url: jdbc:sqlserver://localhost;DatabaseName=CHANGE_DATA_CAPTURE;trustServerCertificate=true
    username: cdc_user
    
CREATE TABLE CHANGE_DATA_CAPTURE.cdc_schema.TBL_STUDENT (
	ID bigint IDENTITY(1,1) NOT NULL,
	FIRST_NAME nvarchar(255),
	LAST_NAME nvarchar(255),
	MIDDLE_NAME nvarchar(255),
	COURSE nvarchar(255)
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
[GET] http://localhost:1000/source/api/v1/student/1
[GET] http://localhost:1000/source/api/v1/student
[PUT] http://localhost:1000/source/api/v1/student/1
{
    "firstName": "Shaikh",
    "lastName": "Tousif",
    "middleName": "Iqbal",
    "course": "CS"
}
[DELETE] http://localhost:1000/source/api/v1/student/1
~~~