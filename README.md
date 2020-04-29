# Patient Management System
> A patient management system developed in Java with the Swing GUI widget toolkit.

## Contents
- [How to use](#how-to-use)
- [Impressions](#impressions)

## How to use

| Command                         | Usage                                                                    |
|---------------------------------|--------------------------------------------------------------------------|
| `./gradlew run`                 | Run the Patient Management System.                                       |
| `./gradlew jar`                 | Build a jar that includes all dependencies.                              |
| `java -jar build/libs/pms.jar`  | Start the application from the created jar.                              |
| `./gradlew test`                | Run unit tests.                                                          |
| `./gradlew javadoc`             | Build Javadoc, which will be saved under `build/docs/javadoc/index.html`.|

If you like to develop additional features or fix any bugs, feel free to submit a Pull Request.´

## Database Structure
This project uses [SQLite](https://www.sqlite.org/index.html) and all data is stored in `PatientData.sqlite`.
![database structure](/images/db_structure.png)

## Impressions

### Login Screen
![login screen](/images/login.png)

### Patient Overview
![patient overview](/images/overview.png)

### Patient Profiles
![patient profiles](/images/profile.png)

## License
See the [MIT license](/LICENSE.md)
