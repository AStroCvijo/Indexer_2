
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
5. Run the project in IntelliJ IDEA (this will run without command line arguments)
6. Copy the command that IntelliJ IDEA used to run the app (you can find it in the bottom window)
7. Open terminal and navigate to the repository
8. Paste the command you coppied
9. Add command line arguments and run


### Arguments guide 

`-path or -p` followed by path to the folder you want to index e.g. `.\\Data`
This will index the `.\\Data` folder and save it to `.\\indexedFolders` (the starting location of .\\\ is inside the app folder, so either move to folder you want to index there or navigate to it)

`-force or -f`
This will force the app to index the folder again and save it to `.\\indexedFolders` (if you don't force a re-index, and the folder has already been indexed, the app will just load it)

`-query or -q` followed by the string you want to search for e.g. `print`
This will print all the occurrences of `print` in the given folder

### Example usage
`gradlew run --args="-p .\\Data -f -q print"`
This will index `.\\Data folder` and print all the occurrences of `print`

