## Advanced ORM with Spring Boot 

This repository contains a Spring Boot exercise that demonstrates advanced
Object-Relational Mapping (ORM) techniques using Spring Data JPA and Hibernate. 
Specifically, it focuses on implementing relationships between entities.

## Setting Up The Database

The first step in working with this repository and application is to set up the
database. Open your mysql workbench and create a new schema called `orm-exercise`.
You do not need to create any tables, or set anything up in the database as
Spring Boot JPA will do that for you.

## Updating the `application.properties` file

Next, we need to update the `application.properties` file to configure the
database connection. Open the `src/main/resources/application.properties` file
and modify the following lines to reflect your username and password for the
MySQL server:

```
spring.datasource.username=mikel
spring.datasource.password=z9y6x3
```

You should also review the other properties in the application.properties file
to ensure they are set correctly for your environment. The
`spring.datasource.url` property should point to the `orm-exercise` schema you
created earlier, and the `spring.jpa.hibernate.ddl-auto` property should be set
to update to allow JPA to create and update the database schema as needed test.

## Reviewing the Application Code

The application in this repository is a simple JPA/Spring Boot application that
employs concepts that you have learned in previous exercises. It includes a
single model class, `Contact`, which represents a contact entity in the database.
This class currently has three fields: `id`, `name`, and `email`.  It also 
contains a repository interface, `ContactRepository`, which extends the
`JpaRepository` interface to provide basic CRUD operations for the `Contact`
entity.  Finally, there is a controller class, `ContactController`, which exposes
RESTful endpoints for managing contacts.

Review this code to familiarize yourself with how the application is structured
and to be sure that you understand how the existing code works.

## Launching the Application and Interacting with the API

Once you have set up the database and updated the application.properties file,
you can launch the application. You can do this by running the
`OrmExerciseApplication` class from your IDE.

To interact with and test the API, you will use Postman and MySql Workbench.
You should be familiar with how to use these tools from previous exercises.

Once the application launches successfully, you can interact with the API using
Postman.  You should perform the following operations using Postman:

1. Create a new contact by sending a POST request to
   `http://localhost:8080/contacts` with a JSON body containing the `name` and
   `email` fields.

2. Retrieve all contacts by sending a GET request to
    `http://localhost:8080/contacts`.

3. Update an existing contact by sending a PUT request to
   `http://localhost:8080/contacts/{id}` with a JSON body containing the updated
   `name` and `email` fields.

4. Delete a contact by sending a DELETE request to
    `http://localhost:8080/contacts/{id}`.

As you are interacting with the API using Postman, you should verify that the
operations are working correctly by viewing the results in the MySQL Workbench
by querying the `contacts` table in the `orm-exercise` schema.

Be sure that you are comfortable with performing all of these testing and
database verification steps before proceeding to the next section, as you will
need to repeat them after making code changes to test the new functionality.

## Adding Addresses to Contacts

The main objective of this exercise is to extend the existing application to
support addresses for contacts.  Each contact can have multiple addresses, and each
address is associated with a single contact.  This is a one-to-many relationship
between the `Contact` and `Address` entities.

### Creating the Address Entity and Establishing Relationships

You will need to create a new `Address` entity
class that has the following fields: `id`, `street`, `city`, `state`, and
`zipCode` and `contact` (of type `Contact`.)  The `id` field should be annotated
as the primary key and set to auto-generate its value.

The `contact` field should be annotated to establish a many-to-one relationship
with the `Contact` entity.  It is **very** important that you also annotate 
this field with `@JsonIgnore` to prevent infinite recursion during JSON 
serialization.

### Updating the Contact Entity

Next, you will need to update the `Contact` entity class to include a new property
call `addresses` that is a `List` of `Address` entities.  Add getter and setter
methods for this property.  You will need to annotate this property to establish a
one-to-many relationship with the `Address` entity.  Be sure to set the cascade type to
`CascadeType.ALL` to ensure that when a contact is saved, its associated addresses 
are also saved.

### Verifying the new Entity and Relationship

At this point, you should verify that the new `Address` entity and the relationship
between `Contact` and `Address` are set up correctly. 

1. Clear the database by dropping the `orm-exercise` schema and recreating it.
2. Relaunch the application to allow JPA to create the necessary tables in the
   database.
3. Use Postman to create a new contact with multiple addresses by sending a POST
   request to `http://localhost:8080/contacts` with a JSON body that includes
   an array of addresses.  For example:

   ```json
    {
        "name": "John Doe",
        "email": "jdoe@example.com",
        "addresses": [
            {
                "street": "123 Main St",
                "city": "Anytown",
                "state": "CA",
                "zipCode": "12345"
            },
            {
                "street": "456 Oak St",
                "city": "Othertown",
                "state": "TX",
                "zipCode": "67890"
            }
        ]
    }
   ```
4. Verify in MySQL Workbench that the `contacts` and `addresses` tables have been
   created correctly and that the addresses are associated with the correct
   contact.
5. Retrieve the contact using a GET request to
   `http://localhost:8080/contacts/{id}` and verify that the addresses are
   included in the response.

## BONUS: Adding New Endpoints for Address Management

Next, you will add two new endpoints to the `ContactController` class to manage
addresses for contacts:

1. A POST endpoint at `/contacts/{contactId}/addresses` to add a new address
   for a specific contact.
2. A DELETE endpoint at `/contacts/{contactId}/addresses/{addressId}` to
   delete an address for a specific contact.

For each of these, your controller method should first retrieve the contact by
its ID, then find the address by its ID (for deletion), add or remove the
address from the contact's list of addresses, and finally save the updated
contact entity back to the database.

### Testing the New Functionality

1. Launch the application again after making the necessary code changes.
2. Test the new functionality using Postman to ensure that you can add,
   and delete addresses for a contact using the new endpoints.

## Conclusion

By completing this exercise, you will have gained hands-on experience with
advanced ORM techniques using Spring Data JPA and Hibernate, including
establishing one-to-many relationships between entities and managing related
data through RESTful endpoints. This knowledge will be valuable for building
more complex applications that require robust data management capabilities.

