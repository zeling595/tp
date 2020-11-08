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

* **Project Management**:
    1. Managed release `v1.3.trial` (1 release) on GitHub.
    2. Managed release `v1.2` (1 release) on GitHub.
    3. Set up GitHub team org/repo

#### Implementation
* **New user feature**: Add Booking
    1. What it does: allows the user to order room services of various types. Upon ordering a room service, we will add
    the cost for the room service to their total bill as well.
    2. Justification: this feature is essential, as room services are part and parcel of hotels - ensuring that guests have
    an enjoyable stay. By enabling this functionality, we allow the receptionist to order and track room services
    for the guests, and finally charge them the correct amount for their stay.
    3. Highlight: the implementation allows us to easily extend the room services that we offer.
    4. Challenges: this feature required the creation of a new class RoomService, as well as a class in model to track
    all the room services and tie them to the correct booking. There was also the challenge of decoupling the implementation
    from the Booking class.

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
        1. Revamped the user guide to make it more understandable and user-friendly
        2. Documented usage for order room service [#132](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/132)
   2. Developer Guide:
        1. Documented implementation for AddBooking feature [\#100](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/100)        
        2. Merged implementation for UnarchiveBooking feature together with Archive B
   3. Updated project's README [\#24](https://github.com/AY2021S1-CS2103-W14-2/tp/pull/24)
