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
    * Highlights: This enhancement takes multiple parameters, and they can also be optional. The implementation involves compressing multiple predicates into one to feed into filteredList. 

* **New Feature**: Add the ability to delete booking.
    * What it does: allows the user to delete the booking permanently with the given booking ID. This is different from archiving as it wipes out the entry from the database entirely.
    * Justification: This feature allows the user to delete an unwanted booking, this is especially useful when a field is wrong (e.g. typo), as it does not leave any trace in database.
    * Highlights: This enhancement takes multiple parameters, and they can also be optional. The implementation involves compressing multiple predicates into one to feed into filteredList. 


* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `TBC` - `TBC` (X releases) on GitHub

* **Enhancements to existing features**: Coming Soon.

* **Documentation**:
  * Developer Guide:
    * Added Use Cases `UC01`, `UC02`, UC03`
    * Added Ui.png mockup
  * Updated index.md

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#24](), [\#25]()


* **Tools**:
  * Coming Soon.

