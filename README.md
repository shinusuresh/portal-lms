# Introduction for Leave Management System

## Steps to do to configure the application

```
  mvn clean package
```

  Deploy __target/portal-lms.jar__ to jetty or tomcat

4. Hit application to see if login page is appearing - http://localhost:8080/portal-lms/


# Sample Login data

Admin Role
admin@localhost.com/admin

Manager Role
manager@localhost.com/manager

User Role
user@localhost.com/user

# Technology Stack

| Framework | Version |
| -------- | -------- |
| Spring   | 3.1.0.RELEASE  |
| Struts | 1.2.9 |
| Hibernate | 3.6.10.Final |
| Thymleaf | 2.1.4.RELEASE |
| EHCache | 2.4.3 |
| JODA Time | 2.4 |
| Metrics | 3.0.2 |
| PDF Box | 1.8.7 |
| Apache POI | 3.10.1 |

# Application Architecture
![Portal Architecture](docs/portal-architecture.png)

# Reference - Sequence diagram for add new user (Struts Action controller flow)
![Add new user sequence diagram](docs/add-new-user-sequence.png)

# Reference - Sequence diagram for adding new skill (Spring Controller Ajax flow)
![Add skill sequence diagram](docs/add-new-skill-ajax-sequence.png)
