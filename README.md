# IoDigital Technical Assessment - TedTalks API

This project is a technical home assignment for **IoDigital.com**, implemented as a standard **Spring Boot** application
using **Kotlin**.

## Why Kotlin?

Kotlin was chosen over Java for its modern features and fun development experience. While it may have slowed the process
slightly due to its learning curve, cleaning the rust out of my knowledge made it worth a struggle.

---

## How to Run the Project

There are two ways to run this project:

1. **Using an IDE**  
   Open the project in your modern IDE of choice (e.g., IntelliJ IDEA), and the run configuration will be automatically
   generated.

2. **Using Gradle**  
   Ensure you have **Java version > 17** installed. Open the terminal in the main folder of the project and run:
   ```bash
   ./gradlew bootRun

Overview of the Solution
The goal was to create an MVP for a CSV-based TED Talk parser with functionality to:

Manage TED Talks
Calculate and view the influence score
Key Implementation Details
Influence Score Formula
Based on the data provided, it was assumed that only around 5% of viewers leave likes. To reflect this, the following
formula was chosen:
influenceScore = (likes * 0.5) + (views * 0.1)

Data Persistence
An in-memory H2 database was used for simplicity, as there were no explicit requirements regarding persistent storage.
Transitioning to a persistent database would be straightforward if needed.

CSV Parsing
CSV parsing was implemented in a single thread, as performance optimization was not a requirement. While the current
implementation processes the provided dataset in 15 seconds, the task can be parallelized using Kotlin coroutines for
better performance on larger datasets.

Validation
Basic validation rules were implemented to ensure data integrity:

Likes cannot exceed views.
The date cannot precede the first-ever recorded TED Talk.
Negative values for likes or views are disallowed.
How to Import Data
Data can be imported using the following cURL command:

   ```bash
    curl --location 'http://localhost:8080/import/file' --form 'csv=@"$PATH_TO_FILE"'
```

Replace $PATH_TO_FILE with the path to your CSV file.

Data Management
The Spring Data REST repositories framework was chosen to handle data management as it provides all necessary features
out of the box.

Influence Score Handling
The influence score is a persistable attribute that gets recalculated every time an entity is updated. This ensures the
score remains accurate ,reflects the latest data and makes searching for influential talks a trivial task.

Example Requests:

1) Get the Most Influential Talks
   Retrieve talks sorted by views:

```bash
GET http://localhost:8080/talks?page=0&size=5&sort=views,desc 
```

2) Get Top Influential Talks by Year
   Retrieve talks sorted by influenceScore for a specific year:

```bash
GET http://localhost:8080/top?page=0&size=20&sort=influenceScore,desc&year=2012
```
While the implementation is functional, more tests and polishing could improve the overall quality and robustness. However, the solution meets the assessment criteria within the time constraints.
