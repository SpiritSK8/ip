# Myne User Guide

<img width="300" height="540" alt="Ui" src="https://github.com/user-attachments/assets/42c47414-4345-490f-924e-6ab41545c80d" />

_May our meeting be blessed on this fruitful day..._

You just got transported into a new world, and you will be working under **Myne** and **Ferdinand**, two high-ranking nobles.
However, you have the leeway to choose your own tasks! Myne and Ferdinand will record down the tasks you wrote down.

Basically, it's a to-do app, but with some cool features and an even cooler personality!

## Features
- Todo app with **3 types of tasks**
- Clean UI with **micro animations** for the premium feel
- CLI-first interaction. The faster you type, the faster you use this app
- **Command history** with up/down arrow keys
- Smart task selection. **Mark/unmark/delete tasks by partial name matching**
- In-app **help message** for each command

## Command List

This app is meant to be learnable even without this user guide, but
a command list is included in this user guide for easy reference.


| Command    | Usage                                                  | Example                                                                      | Notes                                      |
|------------|--------------------------------------------------------|------------------------------------------------------------------------------|--------------------------------------------|
| `help`     | `help`<br/>`help <command_name>`                       | `help todo`                                                                  | -                                          |
| `todo`     | `todo <task_name>`                                     | `todo read books`                                                            | -                                          |
| `deadline` | `deadline <task_name> /by <due_date>`                  | `deadline buy books /by 14-3-2026`<br/>`deadline get books /by 14 Mar 2026`  | Dates must be in DD-MM-YYYY or DD MMM YYYY |
| `event`    | `event <task_name> /from <start_date> /to <end_date>`  | `event write books /from 9-3-2026 /to 9 Mar 2027`                            | Dates must be in DD-MM-YYYY or DD MMM YYYY |
| `mark`     | `mark <task_number>`<br/>`mark <task_name>`            | `mark 1`<br/>`mark read books`                                               | Partial match only works with 1 match      |
| `unmark`   | `unmark <task_number>`<br/>`unmark <task_name>`        | `unmark 1`<br/>`unmark read books`                                           | Partial match only works with 1 match      |
| `list`     | `list`                                                 | `list`                                                                       | -                                          |
| `delete`   | `delete <task_number>`<br/>`delete <task_name>`        | `delete 1`<br/>`delete read books`                                           | Partial match only works with 1 match      |
| `find`     | `find <task_name>`                                     | `find read`                                                                  | -                                          |
| `bye`      | `bye`                                                  | `bye`                                                                        | -                                          |


### 1. Adding Todos
Todo is a type of task without any deadline.

Syntax: `todo <task_name>`

Example: `todo read books`

Result: A new todo task will be added to the list and the following message will be shown:

```
I entrust this task to you.
read books
```

### 2. Adding deadlines

Deadline is a type of task with a single due date. The due date format follows DD-MM-YYYY (e.g. 14-4-2026) or DD MMM YYYY (e.g. 14 Apr 2026).

Syntax: `deadline <task_name> /by <due_date>`

Example: `deadline read books /by 14-4-2026`

Result: A new deadline task will be added to the list and the following message will be shown:

```
Carry this out.
read books (by: 14 Apr 2026)
```

### 3. Adding events

Event is a type of task with a start and end date. The date format follows DD-MM-YYYY (e.g. 14-4-2026) or DD MMM YYYY (e.g. 14 Apr 2026).

Syntax: `event <task_name> /from <start_date> /to <end_date>`

Example: `event read books /from 14-4-2026 /ti 15-4-2026`

Result: A new event task will be added to the list and the following message will be shown:

```
I entrust this task to you.
read books (from: 14 Apr 2026 to: 15 Apr 2026)
```

### 4. Mark/Unmark Tasks

Used to mark/unmark tasks as done. These commands accept either the
index of the task or the name of the task.

If task name is used, then it will first find tasks whose name
exactly matches the task name provided. If it finds any, it will
mark/unmark all of them. If not, then partial matches will be
searched. For partial match mark/unmark to work properly, there
can only be 1 task with the matching name. This is to prevent the
user from accidentally typing something like `mark a` and marking
half of their tasks.

Syntax:
- `mark <task_number>`
- `mark <task_name>`
- `unmark <task_number>`
- `unmark <task_name>`

Example usage:
- `mark 1`
- `unmark read books`

Result: Matching tasks will be marked and the following message will be shown:

```
You have carried out your task with utmost diligence. Very good.
✔ read books
```

### 5. List

Lists all of your tasks.

Usage: `list`

Result: A list of your tasks will be shown with the following message:

```
Here.

1. Read books
2. ✔ Buy books (by: 5 Apr 2026)
```

### 6. Delete Tasks

Deletes the specified task. Just like mark/unmark, this command
accepts either a task number or a task name.

Usage:
- `delete <task_number>`
- `delete <task_name>`

Example:
- `delete 1`
- `delete read books`

Result: The selected tasks will be deleted and the following message
will be shown:

```
Let me give this task to someone else...

read books
```

### 7. Find Tasks

This command finds the tasks whose name contains the keyword.

Usage: `find <task_name>`

Example: `find ad`

Result: A list of tasks that match the keyword will be shown
with the following message:

```
Here.

1. Read books
2. Admin stuff (by: 27 Feb 2026)
```

### 8. Help Command

This command can be used on its own to display all the available
commands, or followed by a valid command name to show more details
about the command.

Usage:
- `help`
- `help <command_name>`

Example:
- `help todo`

### 9. Bye!

If you wish to depart, use this command.

Usage: `bye`