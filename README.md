# library-management-system

![image](https://github.com/henryadamsgit/library-management-system/assets/119608914/9315bc2a-d3e2-4fb5-a397-3a9a5ca8acea)



Library Management System
This project is a library management system built using Java. The system allows users to see what books are available, loan books, and see what books they have loaned. It also allows library admins to run reports on the books that are currently out on loan.


Goals
The goals of this project are to:

Build a functioning library system that allows users to see what books are available and loan books.
Implement a loan system that prevents users from loaning books that are already out on loan.
Create reports that show all the books currently out on loan and the amount of times a particular book has been loaned out.
Process
The process of achieving these goals will involve the following steps:

Parse the CSV data into JSON format and write the data to a JSON file.
Create classes for the books and users in the library system.
Implement the loan system.
Create reports that show all the books currently out on loan and the amount of times a particular book has been loaned out.

All of the functionality is achieved using classes and objects with their own specific methods.

Additonal features:
- Users can sign up and login and with their data stored in a user.json file that my program create using the JsonFileHandler class.
- Users can view all of the books, view them by category, and all the books unavailable.
- Users can loan and return book by updating the books.json file, that keep track of availability and how many times each book has been loaned on.
- Based on the data in the two json files, the admin can run a report to get detailed information of their library.
