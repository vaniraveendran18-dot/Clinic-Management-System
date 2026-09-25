# Clinic-Management-System
clinic management system group project
## System Overview

The Clinic Management System is a Java-based application designed to manage basic clinic activities. It manages information about patients, doctors, administrators, appointments and treatments. The system provides an organised way to store and manage clinic information.

The system uses separate classes for different responsibilities. Patient, Doctor and Administrator are specialised classes based on the Person class. The Appointment class manages appointment details, while the Treatment class records treatment information related to appointments. The Clinic class manages the collections of patients, doctors, appointments and administrators.

## Functional Requirements

1. The system must allow patient information to be recorded and managed.
2. The system must allow doctor information to be recorded and managed.
3. The system must maintain administrator information.
4. The system must allow appointments to be recorded with patient, doctor, date, time and status.
5. The system must allow treatment information to be recorded for appointments.
6. The system must manage clinic information and collections of patients, doctors, appointments and administrators.
7. The system must use private attributes with constructors, getters and setters.
8. The system must use inheritance between Person and Patient, Doctor and Administrator.
## Design Rules and Principles

### High Cohesion and Low Coupling

High cohesion means that each class has one clear and focused responsibility. In the Clinic Management System, each class manages information related to its own purpose. For example, the Patient class manages patient information, while the Appointment class manages appointment details. The Treatment class is responsible for treatment information. This makes the system easier to understand and maintain.

Low coupling means reducing unnecessary dependencies between classes. In the application, the Clinic class manages collections of patients, doctors, appointments and administrators, while the individual classes manage their own information. This helps make the system easier to modify and maintain.

### SOLID Principles

Single Responsibility Principle (SRP): Each class has a specific responsibility. For example, the Treatment class manages treatment information rather than patient registration.

Open/Closed Principle (OCP): The system is organised so that additional functionality can be added without unnecessarily changing the responsibilities of existing classes.

Liskov Substitution Principle (LSP): Patient, Doctor and Administrator are specialised classes that inherit from the Person class. They can use the common information provided by Person while having their own specific information.

Interface Segregation Principle (ISP): If interfaces are used in the system, they should contain only relevant operations so that classes are not required to depend on unnecessary methods.

Dependency Inversion Principle (DIP): The system should avoid unnecessary dependency on specific implementation details. This allows parts of the application to be changed without creating unnecessary changes in other parts.

### Model-View-Controller (MVC)

The application follows the MVC approach by separating different responsibilities. The Model contains classes such as Patient, Doctor, Appointment, Treatment and Clinic. The View represents the Java Swing screens used by users. The Controller handles user actions, input validation, navigation and communication between the interface and the model. This separation helps organise the application and makes it easier to maintain and test.
