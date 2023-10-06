# Spring Boot Rest Embedded Debezium Student CRUD Application
### <i>Receive notification if there is any action from source application</i>
## Prerequisite
Start Spring Boot Rest Student CRUD Application
#### PostgreSQL & pgAdmin/DBeaver/IntelliJ (SQL client software) installed
* Open pgAdmin
* logged in as sysadmin
* Create database CHANGE_DATA_CAPTURE
* User - postgres
* Password - nyk@7788
* Create schema cdc_schema
* Create table Student
#### Enable CDC on SQL Server
````
* Open SSMS (Microsoft SQL Server Management Studio)
* logged in as sysadmin
* Start SQL Server agent
* Enable CDC on SQL Server

Step 1 - Enable for Database
  
    USE <databasename>  
    GO  
    EXEC sys.sp_cdc_enable_db  
    GO
    
    verify - if enabled
    select name, database_id, is_cdc_enabled from  sys.databases
    
    if success
    
    System will create cdc user and schema and following system table will 
    be created under <databasename>.Table.System Table
    
    cdc.lsn_time_mappping (Captute transaction time)
    cdc.ddl_history
    cdc.captured_columns
    cdc.change_tables
    cdc.index_columns
    
Step 2 - Enable for Table
  
     USE <databasename>
     GO
     EXEC sys.sp_cdc_enable_table
        @source_schema = ‘<schema_name>’,
        @source_name = ‘<table_name>’,
        @role_name = null,
        @supports_net_changes = 0;
        
     if success
     System will create table [Database].[cdc].[schema_Table_CT] under <databasename>.Table.System Table
     
     All the change log should go to above table
\
````
#### Troubleshooting while enabling CDC on SQL server
~~~
spring:
  datasource:
    password: nyk@7788
    url: jdbc:postgresql://localhost:5432/CHANGE_DATA_CAPTURE
    username: postgres
    
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
Create/Modify record from source application 
Changes should be reflect at target application
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
