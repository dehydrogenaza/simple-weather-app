# Task
SDA course task decription available (requires login):

https://java.pl.sdacademy.pro/e-podrecznik/projekt_praktyczny/scenariusz_1_pogodynka/

# Important!

To configure the App, create a `credentials.properties` file in `src/main/resources` (or just copy the template file and remove the `TEMPLATE` prefix). This file needs to contain the following properties:

    db_url=jdbc:YOUR_DATABASE_URL
    db_username=YOUR_USERNAME
    db_password=YOUR_PASSWORD

Typical value for `YOUR_DATABASE_URL` is `jdbc:mysql://localhost:3306/weather` (make sure you create a DB named `weather`).

Substitute your personal credentials for `YOUR_USERNAME` and `YOUR_PASSWORD`, as configured when you set up MySQL on your computer.

**Do NOT add this file to Github, as this would leak your password!** It is added to `.gitignore` by default, you should not change it.