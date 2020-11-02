---
layout: page
title: User Guide
---

ConciergeBook (CB) is a **desktop app for hotel receptionists to efficiently manage guest bookings via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConciergeBook can help you optimise how you manage your rooms, your guests and all new and existing bookings - faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `conciergebook.jar` from [here](https://github.com/AY2021S1-CS2103-W14-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ConciergeBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`listBooking`** : Lists all bookings.

   * **`checkIn`**`pid/7 rid/2103 sd/2021-12-12 ed/2021-12-13` : Checks in a person with ID `7` to room `2103` from `2021-12-12` to `2021-12-13`.

   * **`deleteBooking`**`bid/3` : Deletes the booking with booking ID 3.

   * **`clear`** : Deletes all bookings and guests.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addPerson n/NAME`, `NAME` is a parameter which can be used as `addPerson n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `bid/BOOKING_ID [sd/START_DATE]` can be used for find booking command with optional parameter start date.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* With the exception of ordering room service, if multiple parameters are inputted, the last instance will be accepted. <br>
  e.g. if the command specifies `editBooking sd/2020-11-12 sd/2020-11-15`, the new date to be changed will be taken as `2020-11-15`. 
  
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `addPerson`

Adds a person to the local guestbook. 

Format: `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...`
* Prefixes that are not listed in the format of the command may be parsed as part of another. 

Examples:
* `addPerson n/Damith C. Rajapakse p/90123456 e/dcsdcr@nus.edu.sg a/NUS SOC t/VVIP`
* `addPerson n/Amanda Leow p/82340582 e/amanda@yahoo.com.sg a/Orchard`
* `addPerson n/Amy Tan p/91233344 e/amy@gmail.com a/Cinnamon College pid/2` will create a person with `Cinnamon College pid/2`
as the address. 


### Listing all persons : `listPerson`

Lists all persons in the address book.

Format: `listPerson`

### Editing a person : `editPerson`

Edits an existing person in the guestbook.

Format: `editPerson INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* Prefixes that are not listed in the format of the command may be parsed as part of another. 

Examples:
*  `editPerson 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `editPerson 2 n/Betsy Crower` Edits the name of the 2nd person to be `Betsy Crower`.
*  `editPerson 3 a/ pid/2 Tembusu College` Edits the address of the 3rd person to be `pid/2 Tembusu College`. 

### Locating persons by name: `findPerson`

Finds persons whose names contain any of the given keywords.

Format: `findPerson KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findPerson John` returns `john` and `John Doe`
* `findPerson alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `deletePerson`

Deletes the specified person from the guestbook.

Format: `deletePerson INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listPerson` followed by `deletePerson 2` deletes the 2nd person in the guestbook.
* `findPerson Betsy` followed by `deletePerson 1` deletes the 1st person in the results of the `findPerson` command.

### Checking in guest: `checkIn`

Checks in a guest into the hotel.
Format: `checkIn pid/PERSONAL_ID rid/ROOM_ID sd/START_DATE ed/END_DATE`

* Checks in the person with `PERSONAL_ID` at the specified `ROOM_ID` from `START_DATE` to `END_DATE`.     
* The person with the specified `PERSONAL_ID` **must be a positive integer** and **must have been added to the local 
guestbook prior to this.**  
* The `ROOM_ID` **must be a positive integer** and **must be an existing roomId in the hotel.** 
* The `ROOM_ID` determines what type of hotel room it is. Single rooms ($70/night) are from
`ROOM_ID` 2103 to 2112. Double rooms ($100/night) are from `ROOM_ID` 2113 to 2122, Suite rooms ($150/night)
are from `ROOM_ID` 2123 to 2132.  
* The `START_DATE` and `END_DATE` **must be in valid date format in the format yyyy-MM-dd.**
* `START_DATE` must be before `END_DATE`  
* All the fields must be provided.

Example:
*  `checkIn pid/5 rid/2120 sd/2020-12-12 ed/2020-12-25` Checks in person with personal Id `5` into room Id `2120`
from 12 December 2020 to 25 December 2020.

### Checking out guest: `checkOut`

Checks out a guest from the hotel.

Format: `checkOut bid/BOOKING_ID`

* Checks out the guest with the specified `BOOKING_ID`.  
* The `BOOKING_ID` refers to the unique identifier of the booking. 
* The `BOOKING_ID` must be a valid, active booking Id in the BookingBook.  

Example:
* `checkOut bid/42` checks out the guest from his room with the valid booking Id of `42`.

### Viewing a bill: `getBill`

Finds the bill of a specified booking Id.  

Format: `getBill bid/BOOKING_ID`

* The `BOOKING_ID` refers to the unique identifier of the booking.  
* The `BOOKING_ID` must be a valid booking Id in the BookingBook.  

Example:
* `getBill bid/6` shows the bill for the booking Id `6`.  

### Filtering hotel rooms: `filterRoom`
Filters the hotel rooms with some optional filters.

Format: `filterRoom sd/START_DATE ed/END_DATE [typ/ROOM_TYPE]`

* Both `START_DATE` and `END_DATE` have to be provided to list all the hotel rooms that are available within those dates.
* Dates have to be in the format `YYYY-MM-DD`
* An optional `ROOM_TYPE` can be provided to filter the list based on the hotel room’s type. Only 1, 2, and 3 are accepted 
as parameters. 1 indicates Single Rooms, 2 indicates Double Rooms, 3 indicates Suite Rooms. 

Examples:
* `filterRoom sd/2020-09-14 ed/2020-09-17` filters all the hotel rooms which are available from Sept 14 2020 to Sept 17 2020.
* `filterRoom sd/2020-11-09 ed/2020-11-15 typ/2` filters all double rooms which are available from Nov 9 2020 to Nov 15 2020.

### Listing hotel rooms: `listRoom`

Shows a list of all rooms in the room book.

Format: `listRoom [typ/ROOM_TYPE]`

* An optional `ROOM_TYPE` can be provided to filter the list based on the hotel room’s type. Only 1, 2, and 3 are accepted 
  as parameters. 1 indicates Single Rooms, 2 indicates Double Rooms, 3 indicates Suite Rooms. 

Examples:
* `listRoom` will list all the rooms in the Room Book. 
* `listRoom typ/3` will list all the suite rooms in the Room Book. 

### Listing bookings: `listBooking`
Lists the bookings sorted by most recent to least recent. Active bookings will be listed before inactive bookings.

Format: `listBooking`

* Lists all the bookings.

Examples:
* listBooking lists all the bookings.

### Editing a booking : `editBooking`

Edits an existing booking in the booking book.

Format: `editBooking bid/BOOKING_ID [rid/ROOM_ID] [sd/START_DATE] [ed/END_DATE]`

* Edits the booking with booking ID `BOOKING_ID`. The id **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* The edited booking cannot be a duplicate booking or conflicts with any existing booking.

Examples:
*  `editBooking bid/1 rid/2105` Edits the room ID of the booking with ID `1` to be `2105`.
*  `editBooking bid/2 sd/2021-12-13` Edits the start date of the booking with ID `2` to be `2021-12-13`.

### Deleting a booking : `deleteBooking`

Deletes a booking in the booking book.

Format: `deleteBooking bid/BOOKING_ID`

* Deletes the booking with booking ID `BOOKING_ID`. The id **must be a positive integer** 1, 2, 3, …​ 
 and must be present in the bookingBook.

Examples:
*  `editBooking bid/1 rid/2105` Edits the room ID of the booking with ID `1` to be `2105`.
*  `editBooking bid/2 sd/2021-12-13` Edits the start date of the booking with ID `2` to be `2021-12-13`.

### Locating bookings: `findBooking [rid/ROOM_ID] [pid/PERSON_ID] [sd/START_DATE] [ed/END_DATE] [ac/IS_ACTIVE]`

Finds bookings which matches all the given predicates.

Format: `findBooking KEYWORD [MORE_KEYWORDS]`

* The order of the parameters does not matter. e.g. `findBooking pid/3 rid/2103` is the same as `findBooking rid/2103 pid/3 `
* the input room ID and person ID must be valid (registered in the database).
* At least one parameter should be provided.

Examples:
* `findBooking pid/3` returns all the bookings related to the person with person Id 3.
* `findBooking sd/2020-11-12 ed/2020-11-16` returns all the bookings starts from 12 Nov 2020 and ends on 16 Nov 2020.

### Ordering Room Service : `orderRoomService`

Order room service for a particular booking.

Format: `orderRoomService bid/BOOKING_ID rst/ROOM_SERVICE_TYPE`

* Adds room service to booking with booking ID `BOOKING_ID`. The id **must be a valid integer** 1, 2, 3, …​
* The booking id must be the id of a currently active booking.
* The room service type must be one of the following values: `WIFI`, `DINING`, `MASSAGE`

Examples:
*  `orderRoomService bid/1 rst/WIFI` Orders WIFI room service for booking with ID `1`.
*  `orderRoomService bid/2 rst/DINING` Orders DINING room service for booking with ID `2`.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ConciergeBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.





--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ConciergeBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Person** | `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addPerson n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete Person** | `deletePerson INDEX`<br> e.g., `deletePerson 3`
**Edit Person** | `editPerson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editPerson 2 n/James Lee e/jameslee@example.com`
**Find Person** | `findPerson KEYWORD [MORE_KEYWORDS]`<br> e.g., `findPerson James Jake`
**List Person** | `listPerson`
**Check In** | `checkIn pid/PERSONAL_ID rid/ROOM_ID sd/START_DATE ed/END_DATE`<br> e.g., `checkIn pid/5 rid/2120 sd/2020-12-12 ed/2020-12-25`
**Check Out** | `checkOut bid/BOOKING_ID`<br> e.g., `checkOut bid/42`
**Filter Room** | `filterRoom sd/START_DATE ed/END_DATE [typ/ROOM_TYPE]`<br> e.g., `filterRoom sd/2020-09-14 ed/2020-09-17 typ/3`
**List Room** | `listRoom`
**List Booking** | `listBooking`<br> e.g., `listBooking`
**Edit Booking** | `editBooking bid/BOOKING_ID [rid/ROOM_ID] [sd/START_DATE] [ed/END_DATE]` <br> e.g. `editBooking bid/1 rid/2104`
**Get Bill** | `getBill bid/BOOKING_ID`<br> e.g., `getBill bid/6`
**Delete Booking** | `deleteBooking bid/BOOKING_ID`<br> e.g., `deleteBooking bid/3`
**Find Booking** | `findBooking [rid/ROOM_ID] [pid/PERSON_ID] [sd/START_DATE] [ed/END_DATE] [ac/IS_ACTIVE]` <br> e.g. `FINDBooking pid/1 rid/2104`
**Order Room Service** | `orderRoomService bid/BOOKING_ID rst/ROOM_SERVICE_TYPE`<br> e.g., `orderRoomService bid/1 rst/WIFI`


**Help** | `help`
