---
layout: page
title: Gene Chua's Project Portfolio Page
---

## Project: ConciergeBook

ConciergeBook (CB) is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 
ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - 
faster than traditional GUI apps.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=iamgenechua&tabRepo=AY2021S1-CS2103-W14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

#### Implementation
* **New user feature**: Add Booking [#51](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/51), [#213](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/213)
    1. What it does: allows the user to add booking with various parameters tied to the booking.
    These parameters are the person ID, room ID, start date and end date.
    2. Justification: this feature is essential, as bookings need to be made to book the room for the hotel's guests
    so that they can stay at the hotel. Without this service, the hotel would not be able to house guests and not be
    profitable.
    3. Highlight: the implementation does multiple validation checking of dates, ensuring that the start date is before the end date, and that the dates are not more than 30 nights apart.

* **New user feature**: Unarchive Booking 
    1. What it does: allows the user to archive bookings, so that they can kind of delete a booking with the ability to
    recover it again in the future should they change their mind.
    2. Justification: this feature is a good-to-have for users, as an archive functionality is present in many applications
    where deletion is allowed. This is because users may sometimes change their mind about whether they want to delete an item.

* **New user feature**: Get Bill
    1. What it does: allows the user to archive bookings, so that they can kind of delete a booking with the ability to
    recover it again in the future should they change their mind.
    2. Justification: this feature is a good-to-have for users, as an archive functionality is present in many applications
    where deletion is allowed. This is because users may sometimes change their mind about

* **Enhancements to existing features**: 
    * Allowed user input for ordering room service to be case-insensitive

* **Contribution to team-based tasks**: Implemented Storage Feature for Room Service Book
    
* **Contribution to team-based tasks**: Refactored Person class [#44](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/44)
    1. Refactored the Person class to have an ID field, and implemented the assignment of a unique ID to each person added.

#### Documentation
   1. User Guide:
        1. Revamped the user guide to remove multiple typos  
        2. Documented usage for order room service [#132](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/132)
   2. Developer Guide:
        1. Documented implementation for AddBooking feature [\#100](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/100)        
        2. Documented implementation for ArchiveBooking feature
   3. Updated project's README [\#24](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/24)
