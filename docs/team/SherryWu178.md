---
layout: page
title: Gene Chua's Project Portfolio Page
---

## Project: ConciergeBook

ConciergeBook is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Add the ability to find Booking with different parameters.
    * What it does: allows the user to find booking(s) with specific room ID, person ID, start date, end date and isArchived state.
    * Justification: This feature improves the product significantly because a user need this command to find the booking when he/she only has partial information about the booking, e.g. to find out which room a customer is allocated to within the start date and end date period.
    * Highlights: This enhancement takes multiple parameters, and they can also be optional. The implementation also involves writing multiple predicates and compressing multiple predicates into one in the command level to feed into the filteredList in model. 

* **New Feature**: Add the ability to delete booking.
    * What it does: allows the user to delete the booking permanently with the given booking ID. This is different from archiving as it wipes out the entry from the database entirely.
    * Justification: This feature allows the user to delete an unwanted booking, this is especially useful when the field of an added booking is wrong (e.g. typo), as it does not leave any trace in the database.

* **New Feature**: Implement the Booking class.
    * What it does: The Booking class keeps information about a booking in the form of person ID, room ID, start date and end date and archive state.
    * Justification: It is essential to the app's main purpose: manage bookings.
    * Highlights: This enhancement include change to both model and storage, which require in-depth knowledge about the programme flow.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=W14-2&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=SherryWu178&tabRepo=AY2021S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed releases `TBC` - `TBC` (X releases) on GitHub

* **Enhancements to existing features**:
  * Modify delete person feature so that when a person is deleted, all the booking(s) related to her/him are deleted (cascading delete). (Pull requests [\#33](), [\#34]())
  * Add tests for JsonAdaptedBooking, JsonBookingBookStorage, 

* **Documentation**:
  * Developer Guide:
    * Added the section about findBooking and Booking class.
    * Added the UML diagram for findBooking.

* **Community**:
  * Coming Soon.


* **Tools**:
  * Coming Soon.

