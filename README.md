
# File Indexer - CLI tool

A CLI tool that indexes a given folder and allows efficient searching for string occurrences.

## Instructions

1. Download the repository
2. Set up `allowedExtensions.txt` file int the root directory e.g.
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


### Arguments guide 

`-path or -p` followed by path to the folder you want to index e.g. `.\\Data`
This will index the `.\\Data` folder and save it to `.\\indexedFolders` (the starting location of .\\\ is inside the app folder, so either move to folder you want to index there or navigate to it)

`-force or -f`
This will force the app to index the folder again and save it to `.\\indexedFolders` (if you don't force a re-index, and the folder has already been indexed, the app will just load it)

`-query or -q`
This will indicate that you want to query the folder after indexing

`-case or -c`
This will indicate that you want the search to be case-sensitive (if you choose case-sensitive search re-index will be forced)

### Example usage
`C:........MainKT -p .\\Data -f -q`
This will index `.\\Data folder` and start the query process until you choose to stop it

