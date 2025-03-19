# University Model — Spring Security

The goal of this task is to give you some practice using major Spring Security concepts.

## Description

Your goal is to practice using Spring Security technologies to see
how they can be used to restrict access to your application.

We will use the [Role-Based Access Control][RBAC] (RBAC for short) model in this task.

It's advised to reuse code from the Spring REST task.

## Requirements

To complete the task, follow the steps below:

1. Add DB table to store users. It must have at least three columns:
   1. username — string, not null, unique.
   2. password — string, not null.
2. Add DB table to store roles. It must have one column:
   1. name — string, not null, unique
3. Add join-table between roles and users as they have a many-to-many relation.
4. Populate the role table with three roles:
   1. ADMIN
   2. PROFESSOR
   3. STUDENT
5. Configure Spring Security (it'll be described below).
6. Add `@RestController` on creating new users.

### Security configuration requirements

You will implement the RBAC model, so the main idea is to restrict access to our endpoints based on roles, where:
1. ADMIN — will be superuser, who can do anything in the system
2. PROFESSOR — will be able to list resources and update students
3. STUDENT — will have read-only access to all resources

Also, you must not store password as plain text in your database, so you'll need to provide `PasswordEncoder`
implementation in your security configuration and use it when you persist users in your database.

Your security system must be dynamic and consistent. It means that if admin added a new user,
then this user can operate with your system immediately, even on another instance of your application.
If a user was deleted, then they can't access your system using their credentials.

Use [basic access authorization][Basic] for simplicity.

### Endpoints restrictions

* All admin endpoints (for manipulating users) must be accessible only by users with the ADMIN role.
* All read endpoints for students must be accessible by any authenticated user.
* The endpoints for creating and deleting user must be accessible only for users with the ADMIN role.
* The endpoint for updating user must be accessible only for users with ADMIN and/or PROFESSOR roles.
* All read endpoints for courses must be accessible for any user (even anonymous).
* The endpoints for creating, updating, and deleting course must be accessible only for users with ADMIN role.

[RBAC]: https://en.wikipedia.org/wiki/Role-based_access_control
[Basic]: https://en.wikipedia.org/wiki/Basic_access_authentication