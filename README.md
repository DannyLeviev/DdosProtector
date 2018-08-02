# DdosProtector

This is a web server protector. 
It will block multiple HTTP Client's requests if there were 5 previous requests in the last 5 seconds.
Design and implemented as thread safe, concurrent enabled web service.

****
## Used Frameworks/Libraries:
* Spring Boot. 
* Guava Cache.   
* Logback.  
* Hamcrest-junit.  
 
 
****
## Installing and running:
All you need is JRE to run DdosProtector.JAR !
You don't need any servlet containers, since Spring Boot Web App bundles in one.

Steps:
1. Clone this project to your server. 
2. Open cmd, navigate to the project folder.
3. Run: mvn clean install
4. Wait till Maven completes the build.
5. Run the DdosProtector web App by typing: java -jar target/<jar_name>.jar  

****
## Exposed web APIs:
After deploying DdosProtector web service available for all HTTP methods on -  
http://localhost:8080/?clientID= 

****
## Pending technical/business BLIs:
0. ~~Design.~~  (Done - Pushed)  
1. ~~Project skeleton.~~  (Done - Pushed)  
2. ~~Add blank Packages for Controller, Service, Repository (redundant- no DB, only in-memory), Model.~~  (Done - Pushed)  
3. ~~Implement REST Controller responses.~~  (Done - Pushed) 
4. ~~Implement Unit for REST Controller responses.~~   (Done - Pushed)  
5. ~~Implement service (non-thread safe).~~   (Done - Pushed)  
6. ~~Implement thread safe version of service by using Guava Cache.~~   (Done - Pushed)    
8. ~~Basic Unit Test.~~  (Done - Pushed)    
9. ~~Basic Integration Tests.~~    (Done - Pushed)   
