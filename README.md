# A University Model—Spring Security

The goal of this task is to give you some practice using major Spring Security concepts.

## Description

Your goal is to practice using Spring Security technologies to see how they can be used to restrict access to your application.

For this task, you will use the Role-Based Access Control (RBAC) model, a security mechanism that restricts access to resources and operations within the application based on the roles assigned to users.

It's recommended to reuse code from the Spring REST task.

## Requirements

To complete the task, follow the steps below:

1. Add a database table that will store users. It must have at least three columns: 
   1. username — string, not null, unique
   2. password — string, not null
2.	Add a database table that will store roles. It must have one column:
   1. name — string, not null, unique
3.	Add join-table between roles and users as they have a many-to-many relation.
4.	Populate the role table with three roles: 
   1. ADMIN
   2. PROFESSOR
   3. STUDENT
5.	Configure Spring Security as described below.
6. Add `@RestController` on creating new users functionality.

1. Add a database table that will store users. It must have at least three columns:
   1. username — string, not null, unique
   2. password — string, not null
2. Add a database table that will store roles. It must have one column:
   1. name — string, not null, unique
3. Add join-table between roles and users as they have a many-to-many relation.



### Security Configuration Requirements

You will implement the RBAC model, so the main idea is to restrict access to endpoints based on roles, where:
1. ADMIN — will be a superuser who can do anything in the system
2. PROFESSOR — will be able to list resources and update students
3. STUDENT — will have read-only access to all resources

Also, you must not store passwords as plain text in your database, so you'll need to provide `PasswordEncoder` implementation in your security configuration and use it when you persist users in your database.

Your security system must be dynamic and consistent. This means that if admin added a new user, this user can perform operations with your system immediately, even on another instance of your application. If a user is deleted, they won't be able to access your system using their credentials.

Use basic access authorization  for simplicity. Basic access authorization is a mechanism where user credentials are base64-encoded and sent in HTTP headers to verify user identity, which typically requires HTTPS to ensure security.

### Endpoints Restrictions

* All admin endpoints (for manipulating users) must be accessible only by users with the ADMIN role.
* All read endpoints for students must be accessible by any authenticated user.
* Endpoints for creating and deleting users must be accessible only to users with the ADMIN role.
* The endpoint for updating users must be accessible only for users with the ADMIN and/or PROFESSOR role.
* All read endpoints for courses must be accessible to any user (even anonymous users).
* The endpoints for creating, updating, and deleting a course must be accessible only to users with the ADMIN role.