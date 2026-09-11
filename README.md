
# Gradebook Manager

First programming assignment for Programming II (FIU). A command-line Java program for managing student grades — users enter student records (name, PID, grade) with full input validation, then run commands to query stats and update grades.

## Features
- Interactive student entry with validation (names, 7-digit PID, grade 0–100)
- Automatic letter-grade conversion (A through F)
- Commands: `min/max/average/median score|letter`, `letter <PID>`, `name <PID>`, `change <PID> <newGrade>`, `tab scores`, `tab letters`, `quit`

## Structure
- **`Grade`** — score + letter grade, kept in sync
- **`Student`** — name, PID, and `Grade`
- **`GradeBook`** — list of students, lookup and stats
- **`InputValidation`** — input parsing/validation
- **`Main`** — reads input and dispatches commands

Built in Java using `Scanner` for console I/O, no external dependencies.