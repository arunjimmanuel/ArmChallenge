This project processes multiple JSON files containing board lists, combines them into a single JSON output, and includes metadata summarizing the results. The project supports both CLI mode and Web mode.
Features

    Combine Board Lists:
        Reads JSON files from a specified directory.
        Combines all board lists into a single JSON output.

    Sorting:
        Orders boards alphabetically by:
            Vendor
            Name

    Metadata:
        Includes the following under a _metadata object:
            Total number of unique vendors.
            Total number of boards.

    Output:
        Outputs the resultant JSON to the console or serves it over HTTP.

    Modes:
        CLI Mode: Processes the data and outputs the JSON to the console.
        Web Mode: Exposes an HTTP endpoint to retrieve the JSON.

Assumptions

    Each JSON file contains an array of boards in a consistent structure.
    Invalid or missing JSON files are ignored.
    Sorting rules:
        Case-insensitive alphabetical sorting.

Prerequisites

```
    Java 17+
    Maven 3.9+
    JSON files in the directory to process.
```

How to Run
CLI Mode

To execute the program in CLI mode and provide the directory of JSON files:

```
mvn spring-boot:run -Dspring-boot.run.arguments="--mode=cli --directory=/path/to/json/files"
```

The CLI output will include:

    Combined JSON output.
    Metadata.

Web Mode

To run the web service and expose an API:
```
mvn spring-boot:run
```

The web service exposes the following endpoint:

    GET /api/boards
        Returns the combined JSON output, including the _metadata object.

Output Example
Combined JSON Output:

```
{
  "boards": [
    {
      "name": "Low Power",
      "vendor": "Tech Corp",
      "core": "Cortex-M0+",
      "hasWifi": false
    },
    {
      "name": "B7-400X",
      "vendor": "Boards R Us",
      "core": "Cortex-M7",
      "hasWifi": true
    }
  ],
  "_metadata": {
    "total_vendors": 2,
    "total_boards": 2
  }
}

```

Enhancements and Future Improvements

    Error Handling:
        Add better handling for malformed or invalid JSON files.
        Log skipped files with detailed error messages.

    Tests:
        Include comprehensive unit and integration tests for both CLI and Web modes.

    Performance:
        Optimize for large JSON files or directories with numerous files.

    Web Enhancements:
        Add filtering and sorting options via query parameters (e.g., ?sort=vendor).
        Implement pagination for large board lists.

    Deployment:
        Containerize the application using Docker.
        Add CI/CD pipelines for automated builds and tests.

Assumptions and Decisions

    The directory is provided as a CLI argument or form parameter for web mode.
    JSON files have a standard schema; deviations are ignored.
    Default sorting is case-insensitive.


Notes

    In Web mode, access the API at 
```
curl -X POST http://localhost:8080/api/boards \
     -d "directory=/path/to/directory"
```

