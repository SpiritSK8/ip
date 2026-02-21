# Emu User Guide

**Emu** is a **desktop smart task management chatbot** that helps
you capture, organize, and track tasks efficiently, with a cheerful personality to boot!

## Features
> [INFO!] **Notes about the command format:**
>- Words in `UPPER_CASE` are values given by the user. 
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a value given by the user. 
> - Formats must be followed in the <ins>exact</ins> order given. 
> - Extra values given for commands that do not require any 
(such as list, undo, and bye) will be ignored.
e.g. bye 123 will be interpreted as bye.
> - `INDEX` refers to the index of a task when `list` is used.
### 1. Add a Task
Emu supports three types of tasks:

- **To-Do**: `todo DESCRIPTION`  
  Example: `todo Buy groceries`


- **Deadline**: `deadline DESCRIPTION /by BY`  
  `BY` can be **any string**  — if in `YYYY-MM-DD` format, it will automatically be converted to `MMM d yyyy`.  
  Example: `deadline Submit report /by 2026-02-28`


- **Event**: `event DESCRIPTION /from FROM /to TO`  
  `FROM` and `TO` can be **any string**  — if in `YYYY-MM-DD` format, it will automatically be converted to `MMM d yyyy`.  
  Example: `event Meeting /from 2026-02-21 /to 2026-02-21`

  > **Note:** Emu does not ensure the event start date/time 
  > is before the end date/time.

### 2. Mark a Task
Marks a task as done.

- **Command:** `mark INDEX`
- Example: `mark 2`

### 3. Unmark a Task
Unmarks a previously marked task.

- **Command:** `unmark INDEX`
- Example: `unmark 2`

### 4. Delete a Task
Deletes a task from the TaskList.

- **Command:** `delete INDEX`
- Example: `delete 3`

### 5. Undo Last Change
Undoes the last action that modified the TaskList.

- **Command:** `undo`

> Only actions that modify the TaskList (add, delete, mark/unmark) can be undone.

### 6. List All Tasks
Displays all tasks in the TaskList.

- **Command:** `list`

### 7. Search Tasks by Keyword
Searches for tasks whose description contains the given keyword.

- **Command:** `find KEYWORD`
- Case-sensitive, returns tasks in the order they appear in the list.
- Example: `find homework`

### 8. Quit and Save
Saves all tasks and exits the program.

- **Command:** `bye`

> Do not close via the X button — tasks will not be saved.

### 9. Data Handling
- Tasks are automatically loaded from `[JAR location]/data/tasks.txt`.
- Advanced users can edit this file **only when Emu is not running**.
- Invalid edits may be discarded, or corrupt the original data. Do it
at your **own discretion**.

## Command Summary

| Action        | Command Example                       |
|---------------|---------------------------------------|
| Add To-Do     | `todo DESCRIPTION`                    |
| Add Deadline  | `deadline DESCRIPTION /by BY`         |
| Add Event     | `event DESCRIPTION /from FROM /to TO`|
| Delete        | `delete INDEX`                        |
| Mark Task     | `mark INDEX`                          |
| Unmark Task   | `unmark INDEX`                        |
| Undo          | `undo`                                |
| List Tasks    | `list`                                |
| Search        | `find KEYWORD`                        |
| Quit & Save   | `bye`                                 |                                                              |