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

### 1. Adding Todos
Todo is a type of task without any deadline.

Syntax: `todo <task_name>`

Example: `todo read books`

Result: A new todo task will be added to the list and the following message will be shown:

```
I entrust this task to you.
read books
```

## Adding deadlines

Deadline is a type of task with a single due date. The due date format follows DD-MM-YYYY (e.g. 14-4-2026) or DD MMM YYYY (e.g. 14 Apr 2026).

Syntax: `deadline <task_name> /by <due_date>`

Example: `deadline read books /by 14-4-2026`

Result: A new deadline task will be added to the list and the following message will be shown:

```
Carry this out.
read books (by: 14 Apr 2026)
```

## Adding events

Event is a type of task with a start and end date. The date format follows DD-MM-YYYY (e.g. 14-4-2026) or DD MMM YYYY (e.g. 14 Apr 2026).

Syntax: `event <task_name> /from <start_date> /to <end_date>`

Example: `event read books /from 14-4-2026 /ti 15-4-2026`

Result: A new event task will be added to the list and the following message will be shown:

```
I entrust this task to you.
read books (from: 14 Apr 2026 to: 15 Apr 2026)
```

## Other commands

- `help`
- `bye`
- `list`
- `mark`
- `unmark`
- `delete`
- `find`

I will update this user guide soon. But for other commands, you can type `help` to show the list of commands you can enter.

You can also type `help <command_name>` to learn more about each command.
