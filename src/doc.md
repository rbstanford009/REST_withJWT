# h2 console
### To get it to work:
##### Add this to security section: http.headers().frameOptions().disable();
##### Make sure to open it up permitAll()
###### .antMatchers("/h2-console/**").permitAll()

##### http://localhost:8080/h2-console

# Rules
## User
#### user can be zero or multiple employees.
####User is a concrete person
#####Example: User can be an employee in departments  under one parent.
##Employee
#### Employee must be made from a user and be within a department and typically has a parent
####Employee is the concept of a user fulfilling a role.  A concrete person may fulfill several roles.
#####Root Employee has and is managed by that Employee.
#####Employee is unique only in terms of it's id.  
#####There can be several employees of one user.  Logical reason would be thinking of a user in two employment positions
######There is no limitation (validation).  For example you could have another employee same user, parent and department.
#####Deleting an employee removes the employment of that person in that role and not the person.
######Future may expire employee and have a validation to not allow two active employees have the same user, parent and department.
####Employee assignments
#####The employee (not User) is assigned to a department and parent user (not employee) they report to.
#####There are no parent employees.  Just employees that have parent users and departments.
#####So a parent may not be an Employee.  Be making a parent not an employee you can remove that tree structure from your organization
##Department
####Grouping of employees
####Employees (not users) are assigned to a department
####Department has a name and description
##Organization
#### Is made up of employees
#####The starting point can be thought of as the root employee.
######You could make the root employee from an abstract user.  Therefore, allowing for multiple execs at the top.
#### Has and Organization Name

#Notes
#####There are some convenience endpoints that are not restfull and more entity based.
######However, you still could use only the restfull endpoints to do everything



#API
##User
####Standard Crud Rest resources "\<domain\>/users"
##Department
####Standard Crud Rest resources "\<domain\>/departments"
##Organization
####Standard Crud Rest resources "\<domain\>/organizations"
##Mappings
#### "\<domain\>/mappings"
##### Get "/"
##### Get all employees with each employee providing a list of their direct reports.
##### Get "/details"
##### Get all employees with each employee providing a list of their direct reports (verbose)
##### Get "/{userId}"
##### Get all the direct reports for a user
##### Get chain of direct reports. A user reports to top the president
##### Get "/{userId}/chain"
##Employee
#### "\<domain\>/employees"
#####Get all employees
###### "\"
#####Get employee by id
###### "\{id}"
#####Get employee(s) by query of user id. (Typically one employee)
###### "\query?type=user?id=<userid>"
#####Get employees by query of department id
###### "\query?type=department?id=<deparmentId>"
#####Post (create) employee with existing user and department
###### "/user/{userId}/parent/{parentId}/dept/{deptId}"
#####Post (create) employee and user, parentUser and department.  If user or department do not exist it will create them.  If the parent does not exist it will error
######Convenience method to be able to generate multiple entities at one time.
######Note by making the parent null you generate an employee without a parent.  This would be a root node.
###### "/"
###### RequestBody: EmployeeDto
#####Post (create) employee and user and assign to department and parent.  
######Convenience method to be able to generate multiple entities at one time.
######Note by making the parent null you generate an employee without a parent.  This would be a root node.
###### ""/userName/{username}/firstName/{firstName}/lastName/{lastName}/parentId/{parentId}/departmentId/{deptId}"
#####Put (assign) assign employee (not user) to a user (not employee) to be its parent.
######So this user employed in this role reports to this user.
###### "/assign/{employeeId}/to/{parentId}"
#####Delete (employee) remove employment not the user.
######This user is no longer employed in this role.
######May not remove a root employee
######May not remove an employee that has user that is a parent user.
######This may be an issue when a user is multiple employees
###### "/{id}"
##Reports "/reports"
###Get entity Information
####Users "/users"
####Example: http://localhost:8080/reports/users
####Departments "/departments"
####Employees "/employees"
####Organizations "/organizations"
###Orgchart "/orgchart/query?type="
####Type options:  html, xml
####Example: http://localhost:8080/reports/orgchart/query?type=html
###Line of Authority "user/{user_id}/authority"
###Direct Reports "user/{user_id}/directReport"

#Security
####Will be done later


#Steps
##Initializer
####Everything done except bringing in Security.  Finish up Actuator when I do security