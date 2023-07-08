# TDDSpringProject
To run my program you simply want to press play on the main method under the "TddSpringProjectApplication" class. 
While in this class I have saved some now commented out code that can be used to verify that the details are being applied as they should.
To fully test the CRUD methods you can head over to the test package where you will find a class called "OrderEntityRepoTest"
this class will have a method test for creating, reading, updating, and deleting something within the database. They are all named
very well to help ensure you understand which operation of the CRUD function is being tested. In this same package you will find the
"HTTPTests" Mockito test to help check the HTTP connects and while running each method it will also print out the order details within that method to show
that something was created, saved, and updated. And there is a true or false boolean to help check that something was deleted.
