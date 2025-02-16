# UFC Fighter Tracker

---

Design Document

Steven North,
Dikshya Thapa

## Introduction
Our application allows users to keep track of their favorite UFC fighters, as well as find information on the up and coming prospects. They will be able to find fighters by name or weight class, and find pertinent information like wins, losses, and fighting styles. The data will be sourced from our master database containing the active fighter roster.  

Users can generate a list of fighters given a weight class, or will be able to find single fighters by name. This generated report will allow them to follow along with their favorites or get insight on names they are not yet familiar with.
Additionally, as new fighters make their debut, users will be able to add them to the system, given their name, weight, and pre-ufc record.  

Users will have the option to interact with our application through a simple UI or by using a set of RESTful service endpoints to exchange data.

## Storyboard

[UFC Figther Tracker Storyboard](https://www.canva.com/design/DAGfNkkTBe0/8G6moc_ZUOAeYTGyeVHSAg/edit)

![Image](https://github.com/user-attachments/assets/655dab29-77cd-410a-bfd5-dcb63354419d)

## Functional Requirements

### Requirement 1: Track Fighter Performance

#### Scenario  

A user wants to see how a Fighter’s performance has changed over the last year

#### Dependencies

Fighter data is available and accessible

#### Examples
1.1 

**Given** the data is available

**When** the user looks at "Kamaru Usman’s" profile  

**Then** the app shows a details that tracks his wins and losses over the past year

The user sees that Usman won 3 out of 4 fights, with 2 wins by knockout and 1 by decision.

1.2

**Given** some data is missing

**When** the user tries to look at the graph, but some fight results are missing

**Then** the app tells the user that data is missing and asks if they want to update it when more information is available


1.3 

**Given** the data is available

**When** The user tracks Usman’s progress over the last year

**Then** the app shows that Usman’s knockout percentage has increased from 40% to 60%.

### Requirement 2: Track Weight Class Statistics

#### Scenario  

A user wants to search by weight class, so that they can see the weight class information or a list of fighters by rank

#### Dependencies

Weight class and fighter data are available and accessible

#### Examples

2.1

**Given** weight class data is available

**When** a user searches for a weight class with the name “bantamweight”

**Then** the user will get the info for the weight class (min weight, max weight, active fighters, active champ, etc.)

2.2

**Given** weight class data is unavailable/doesn’t exist

**When** a user searches a weight class with invalid name “super heavyweight”

**Then** the user will be notified that the weight class doesn’t exist

2.3

**Given** weight class data and fighter data is available

**When** a user chooses to find fighters by weight class with name “middleweight”

**Then** UFC Stats will return a list of type fighter with the fighters whose weight resides in the weight class, by rank order 
