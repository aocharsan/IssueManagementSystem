o Setup instructions
 Java 21
 Springboot 3.5.7
 Validations 
 JPA for data persistance to relational MySQL DB
 Basic Auth Spring Security handling to store login assignee name
 
o API endpoints list
issue-management
1) create-issue --->POST: http://localhost:8080/growvenus/api/create-issue
   input: request body
    {
    "title": "github-merge-related",
    "description": "There is issue while merging feature branch with master branch where it not getting resolve yet."
    }

 2) fetch-issue-by-title ---->GET: http://localhost:8080/growvenus/api/get-issue?title=github-merge-related
  
 3) fetch-all-issues ---->GET: http://localhost:8080/growvenus/api/get-all-issue
    
 4) update-issueOrstatusOrPriority ----> PATCH: http://localhost:8080/growvenus/api/update-issue/{id}
    input: request body
    {
    "title": "jenkins-build-related",
    "description": "There is issue while build a application where it not getting resolve yet due to bug.",
    "status":"RESOLVED",
    "priority":"HIGH"
    }
    
5) filter-issue ----> GET: http://localhost:8080/growvenus/api/get-filtered-issue?priority=HIGH&status=RESOLVED

user-management

6) login-user ----> POST: http://localhost:8080/user-service/api/auth-user
   input: request body 
   {
    "userName": "yash_rathod@123",
    "password": "Duplex@1998"
   }
   
7) register-user ----> POST: http://localhost:8080/user-service/api/register
   input: request body --> default role: USER
   {
    "userName": "yash_rathod@123",
    "password": "Duplex@1998"
   }
   
8) change-user-role ---->PUT: http://localhost:8080/user-service/api/2/role  
 input: request body 
   {
    "roles":"USER"
   }

    
