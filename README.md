# CPSC 210 Personal Project 

## Summary

For my *CPSC 210* term project, I plan on making a calendar application that will receive
inputs of scheduled events, including the name, date, time, and duration, and display it in
an organized way. It would be useful for people that have busy schedules, such as 
students or employees, as it could help them organize their daily activities and also for 
planning for future events. For example, a student could use this application to keep track 
of their **class schedule, assignment due dates, upcoming exams, and school events**. This 
project interests me because I personally use agendas and calendars to organize my 
schedule, so I think it would be interesting to build my own.

## User Stories 

- I want to be able to add an event to the calendar
- I want to be able to add a task with due date to the calendar
- I want to be able to view a list of events on a certain day 
- I want to be able to view a list of tasks that are yet to be completed  
- I want to be able to be able to remove a task once it is complete
- As a user, I want the option to save the calendar 
- As a user, I want the option of loading a previously made calendar 

- You can generate the first required action related to the user story "I want to be able to add an event 
to the calendar" by inputting the name, date, time and duration into the text fields and pressing the "Add Event button"
Note: year, month, time, and duration must be integers 
- You can generate the second required action related to the user story "I want to be abel to view a list 
of events on a certain day" by putting adding the date to the text fields and clicking the "Filter Events" button 
- You can locate my visual component on the bottom of the main frame 
- You can save the state of my application byb pressing the "Save calendar button"
- You can reload the state of my application by pressing the "Load Calendar" button 

# Citation 
Image taken from https://www.flaticon.com/free-icon/calendar_4781427 
no exception handling. For example, only integers from 1-12 are accepted for the month of an event, however, there is 
nothing that informs the user if an invalid input has been made and causes errors. By adding exception handling, these 
errors could be prevented. Possible exception handling would be for the date inputs and duplicate names of the same 
event or task. 
