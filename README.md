
# File Indexer - CLI tool

Test task project created for the application to the JetBrains Internship ("Natural Language Code Search").<br />
Date of creation: May, 2024 <br /><br />
A CLI tool that indexes a given folder and allows efficient searching for string occurrences. <br /><br />
***The code changed after the deadline for the JetBrains internship application because I found a better approach to the problem. If you only find the code that was submitted before the deadline relevant, here is that version of the code [Original Code](https://github.com/AStroCvijo/Indexer_2/tree/2c36111e3e33ed813ecdb60995e40ebc20edd68f)***

## Instructions

1. Download the repository
2. Set up `allowedExtensions.txt` file in the root directory e.g.
```txt

  txt
  kt
  py

```
4. Open the project in IntelliJ IDEA
5. Run the project in IntelliJ IDEA (this will run the app without command line arguments)
6. Copy the command that IntelliJ IDEA used to run the app (you can find it in the bottom window), should look like this `C:........MainKT`
7. Open the terminal and navigate to the repository
8. Paste the command you coppied (`C:........MainKT`)
9. Add command line arguments and run the app


## Arguments guide 

`-path or -p` followed by path to the folder you want to index e.g. `.\\Data`
This will index the `.\\Data` folder and save it to `.\\indexedFolders` (the starting location of .\\\ is inside the app folder, so either move to folder you want to index there or navigate to it)

`-force or -f`
This will force the app to index the folder again and save it to `.\\indexedFolders` (if you don't force a re-index, and the folder has already been indexed, the app will just load it)

`-query or -q`
This will indicate that you want to query the folder after indexing

`-case or -c`
This will indicate that you want the search to be case-sensitive

## Examples

| Command                                       | Description                                                          |
|-----------------------------------------------|----------------------------------------------------------------------|
| `C:........MainKT -p .\\Data`                 | Index a Folder Without Re-indexing or Querying                       |
| `C:........MainKT -p .\\Data -f`              | Index a Folder and Force Re-indexing                                 |
| `C:........MainKT -p .\\Data -q`              | Index a Folder and Query Without Case Sensitivity                    |
| `C:........MainKT -p .\\Data -f -q`           | Index a Folder, Force Re-indexing, and Query Without Case Sensitivity|
| `C:........MainKT -p .\\Data -q -c`           | Index a Folder, Query with Case Sensitivity                          |
| `C:........MainKT -p .\\Data -f -q -c`        | Index a Folder, Force Re-indexing, and Query with Case Sensitivity   |
| `C:........MainKT -p .\\Documents -q`         | Index a Different Folder and Query Without Case Sensitivity          |
| `C:........MainKT -p .\\AnotherFolder -f -q -c`| Index a Folder, Force Re-indexing, and Perform Case-sensitive Search |


