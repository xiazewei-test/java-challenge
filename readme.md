### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

---
Sorry I am really busy on a huge project recently, so I only have several hours to do this challenge after work.  


### What I did
- Add tests with Spock framework  
- Changed syntax in services, controller files, including lombok and validation  
- Added exception and [GlobalExceptionHandler.java](src/main/java/jp/co/axa/apidemo/controllers/GlobalExceptionHandler.java)  
- Added caching logic for database calls  
- Added Java Doc in [EmployeeServiceImpl.java](src/main/java/jp/co/axa/apidemo/services/EmployeeServiceImpl.java) and some other files  
- Fix save, update, delete logic in [EmployeeServiceImpl.java](src/main/java/jp/co/axa/apidemo/services/EmployeeServiceImpl.java)  
- Use log instead of System.print  


### What will I do if I have more time
- Add authentication  
- Manage response with managed response code, e.g., Created, Unauthorized ...  
- Add more tests, including edge test, abnormal test and tests for controller  
- Write more documents and comments  
- Add more validation for request value, e.g., regular expression for salary, name and department  