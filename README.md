# Dance School RESTful API
### Java Spring Boot, JPA, Hibernete, SQL, Firebase Authentication, Mavern, Lombok

A coding challenge developed in 2 days as the java version of an existing ASP.NET Core Web API developed by me.<br />
The <a href="https://github.com/Zoe-0925/dance-school">ASP.NET Core Version and live demo </a> are here. <br /><br />

The Java Spring Boot back end of a web application designed for a small business that runs a dance school. <br />
It includes an admin web portal for the business owner to view a dashboard and conduct CRUD operations.<br />
It also includes a student protal for students to book classes and subscribe to memberships. <br />
The users roles include the dance school owner (admin) and students. <br />
 <br /> <br />

## Table of Contents
1. [ API Documentation (Swagger) ](#API)
2. [ Feature Highlights ](#Feature)
3. [ Maintainability ](#Maintainability)
4. [ Security ](#Security)
5. [ Scalability ](#Scalability)
6. [ Tech Stack ](#Tech)
7. [ Design Patterns ](#Design)
8. [ How to run the program locally ](#Run)

<a name="API"></a>
## API Documentation (Swagger)

<a name="Demo"></a> 
## Admin Demo: https://danceschoool.herokuapp.com/admin   (No sign up required)
## Student Demo: https://danceschoool.herokuapp.com/student      (No sign up required)
![Demo Photo](https://github.com/Zoe-0925/DanceSchool/blob/master/danceschool/Client/public/Demo.png)
![Demo Photo](https://github.com/Zoe-0925/DanceSchool/blob/master/danceschool/Client/public/Demo-2.png)

<a name="Feature"></a>
## Feature Highlights
- An interactive admin dashboard with visualisations for data analytics
- Display data such as class information, instructors and memberships
- Allow the admin user full access on CURD operations.
- Allow the student role to view public data such as courses, membrships
- Allow the student role to book dance classes and subscribe to memberships

## Development Highlights
<a name="Maintainability"></a>
### Maintainability
| Technology | Description |
| ----------- | ----------- |
| Domain-Driven Design | Separated SQL queries from business logics through the repository pattern to encapsulate implementation details |
| Swagger API Documentation | Documented the API and multiple error response codes |
| OOP and DI | Abstracted duplicated codes into middlewares |
| Lombok | Reduced redundant codes in object oriented programming |

<a name="Security"></a>
### Security and Robustness
| Technology | Description |
| ----------- | ----------- |
| Firebase Auth | Authentication and role-based authorization implemented with middlewares to filter unauthenticated requests |
| Error handlings and Validations | Customised error response codes for business logics and model validations for data integrity |
| SQL Stored Procedures | Prevent SQL injection |

<a name="Scalability"></a>
### Scalability and Performance
| Technology | Description |
| ----------- | ----------- |
| In-memory Caching | The Java Spring Boot Caching is utilised to boost query performance |
| Pagination | Fasten the loading time even with a huge amount of data |
| Advanced SQL | Restrict necessary data fields returned from the server. Map "1-to-many" relationships. Minimise database oprations with batch queries. |

<a name="Tech"></a>
## Tech Stack:
Back End: Java Spring Boot, JPA, Hibernete, SQL, Firebase Authentication, Mavern, Lombok <br />

<a name="Design"></a>
## Design Patterns:
- Spring MVC
- Repository
- Dependency Injection
- Autowired Singletons
- Progressive Web Application with Spring Boot Caching

<a name="Run"></a>
## How to run the program locally:
First, create a firebase project to acquire your firebase authentication API credentials from  <a href="https://firebase.google.com/">Firebase Console</a>.<br />
Then, set up your local SQL server and Redis to achieve connection strings.<br />
Alternatively, you can set up Azure SQL and Azure Redis Cache via <a href="https://azure.microsoft.com/en-au/features/azure-portal/">Azure Portal</a>.<br />
<br />
Coming soon.<br />
