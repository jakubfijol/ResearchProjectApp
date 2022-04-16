# ResearchProjectApp
> This is a backend app which helps to manage research projects.

## Table of Contents
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)


## Technologies Used
- Java 18
- MySQL
- Spring Boot
- Maven
- Lombok


## Features
List the ready features here:
- Add, modify, delete, get patient
- Add, modify, delete, get patient consent
- Add, modify, delete, get project
- Add, modify, delete, get lab test
- Add, modify, delete, get, login, change password user


## Usage
Firstly you must create a SQL database and set its url, username, password and port into `application.prosperites` file (src/main/resources).
Then you must create new schema in SQL named `researchproject` and create tables:
- `consent`
- `labtest`
- `patient`
- `project`
- `user`

After downloading the project, go to its main folder in terminal and type this:

`mvn spring-boot:run`

Now you can open Postman and enter this adress into request URL field:

`http://localhost:8070/`

There are five main instructions to use:

- `consents/`
- `labtests/`
- `patients/`
- `projects/`
- `users/`

For example, sending this GET command returns you all patients from database:
`http://localhost:8070/patients/`

If you want to GET patient with specify id just type its id on the end of URL.

If you want to POST new patient you must send this command
`http://localhost:8070/patients/`
with raw body:
`[{
"firstname": "...",
"lastname": "...",
"pesel": "...",
"phonenumber": "..."
}]`
and with header:
key`Content-Type`
value`application/json`

To edit some patient you must specify his id and use PUT or PATCH.
To DELETE patient type his id on the end of URL.

These commands work with all five SQL tables.
In user table you can change user password with PATCH.
In addition there is only one security option which prevents user from deleting other users.
To delete user you must have admin status added in header.


