# DiceGame
# Overview
This project is a simple dice game implemented using Java EE (Jakarta EE) concepts and tools. The game allows users to log in, play a dice game, and view their scores. It showcases the use of servlets, filters, sessions, and JSPs in a dynamic web application.

# Features
- User Authentication: Users log in to access the game.
- Game Logic: Users roll dice, and scores are calculated based on predefined rules.
- Session Management: Sessions maintain user login states, game states, and scores.
- Error Handling: Robust error handling with friendly user messages.
- Input Validation: Ensures data integrity and prevents errors.
- Dynamic Content: JSPs generate HTML content based on user actions.
  
# Technical Details
- Servlets
BestScoreServlet: Displays the best scores.
DeconnectServlet: Handles user logout.
GameServlet: Manages game actions and score updates.
LoginServlet: Manages user login.
NewGameServlet: Starts a new game .
ReinitGameServlet: Resets the current game.
UserManagementServlet: Manages user registration and updates.

- Filters
ExceptionFilter: Logs and handles exceptions, forwarding to an error page.
SecurityFilter: Ensures only authenticated users access protected resources.
UserValidationFilter: Validates user input for registration and updates.

- Sessions
Session Management: Maintains user-specific data and game state.

- JSPs and Frontend
LoginForm.jsp: User login interface.
GamePage.jsp: Main game interface for rolling dice and viewing scores.
ScorePage.jsp: Displays scores.
Error.jsp: Shows error messages.

- How It Works

1- Registration and Login: Users register and log in using UserManagementServlet and LoginServlet.
![Screenshot 2024-06-20 at 23 03 32](https://github.com/AmmariHiba/DiceGame/assets/121461519/1b730c2e-714!f-4556-b1ab-51615c8feb3a)
[Screenshot 2024-06-20 at 23 03 25](https://github.com/AmmariHiba/DiceGame/assets/121461519/e558a24e-0413-4230-80ea-652ca40128ea)

2- Starting a Game: Clicking "Nouvelle partie" directs users to NewGameServlet to begin a new game session.
![Screenshot 2024-06-20 at 22 55 11](https://github.com/AmmariHiba/DiceGame/assets/121461519/624d0626-2018-4ec2-ae3a-357993fee97f)

3- Gameplay: Users roll dice via GameServlet, aiming for the condition where die1 < die2 < die3 to win.
![Screenshot 2024-06-20 at 22 54 36](https://github.com/AmmariHiba/DiceGame/assets/121461519/f873ff5f-faf4-4334-8fcd-f9521983267f)


4- Score Display: BestScoreServlet shows top scores achieved by users.
![Screenshot 2024-06-20 at 22 54 43](https://github.com/AmmariHiba/DiceGame/assets/121461519/76d4e9a5-a935-4ecd-847f-9e3a12f5f7a4)


5- Logout:DeconnectServlet handles user logout, ending the session securely.


  
