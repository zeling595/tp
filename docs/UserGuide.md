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

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.


### Checking in guest: `checkIn`
Checks in a guest into the hotel.

Format: checkIn ic/GUEST_IC id/ROOM_ID sd/START_DATE ed/END_DATE

* Checks in the guest into the specified ROOM_ID
* The roomId refers to the unique identifier of the room
* The guest IC and room ID must be unique.
* The room ID must be a valid room number.
* GUEST_IC needs to be a 5 lettered-string, with the first as a character and the remaining 4 as numbers
Dates have to be in the format YYYY-MM-DD.

Examples:
* checkIn ic/S7894 id/4102 sd/2020-09-14 ed/2020-09-17 checks in a guest whose IC is S7894 into room 4102 from 
14 September 2020 to 17 September 2020.


### Checking out guest: `checkOut`
Checks out a guest from the hotel.

Format: checkOut ROOM_ID

* Checks out the guest staying at ROOM_ID.
* The room ID refers to the unique identifier of the room. 
* The room ID must be a valid room number (4-digit number e.g. 2103, 1010, 3103)

Examples:
* checkOut 2103 checks out the guest who stays in room 2103.


### Listing hotel rooms: `listRoom`
Lists the hotel rooms with some optional filters.

Format: listRoom sd/START_DATE ed/END_DATE rt/ROOM_TYPE

* Lists all the hotel rooms if none of the arguments are provided.
* Both START_DATE and END_DATE have to be provided to list all the hotel rooms that are available from the START_DATE to END_DATE.
* Dates have to be in the format YYYY-MM-DD
* An optional ROOM_TYPE can be provided to filter the list based on the hotel room’s type.

Examples:
* listRoom sd/2020-09-14 ed/2020-09-17 lists all the hotel rooms which are available from Sept 14 2020 to Sept 17 2020.
* listRoom rt/single lists all the hotel rooms of single type.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ConciergeBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Person** | `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `addPerson n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear Person** | `clearPerson`
**Delete Person** | `deletePerson INDEX`<br> e.g., `deletePerson 3`
**Edit Person** | `editPerson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editPerson 2 n/James Lee e/jameslee@example.com`
**Find Person** | `findPerson KEYWORD [MORE_KEYWORDS]`<br> e.g., `findPerson James Jake`
**List Person** | `listPerson`
**Check In** | `checkIn ic/GUEST_IC id/ROOM_ID sd/START_DATE ed/END_DATE`<br> e.g., `checkIn ic/S7894 id/4102 sd/2020-09-14 ed/2020-09-17`
**Check Out** | `checkOut ROOM_ID`
**list Room** | `listRoom sd/START_DATE ed/END_DATE rt/ROOM_TYPE`<br> e.g., `listRoom sd/2020-09-14 ed/2020-09-17`
**Get Bill** | `getBill id/ROOM_ID ed/BOOKING_END_DATE`<br> e.g., `getBill id/2103 ed/2020-09-15`


**Help** | `help`
