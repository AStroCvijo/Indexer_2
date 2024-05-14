import java.io.File
import kotlin.system.exitProcess

// HashMap to store hashed tokens
var tokenMap = HashMap<String, MutableList<TokenInfo>>()

// Master function for inserting tokens
fun insertToken(tokenInfo: TokenInfo, trie: Trie) {
    // Insert into the Trie
    trie.insert(tokenInfo.t)

    // Insert into the HashMap
    if (!tokenMap.containsKey(tokenInfo.h)) {
        tokenMap[tokenInfo.h] = mutableListOf()
    }
    tokenMap[tokenInfo.h]?.add(tokenInfo)
}

// Main function
fun main(args: Array<String>) {

    println("RUN!")

    // No arguments provided
    if (args.isEmpty()) {
        println("Please provide a command-line argument")
        return
    }

    // Create an instance of the trie
    val trie = Trie()

    // Initialize arguments
    var folderPath = ""
    var force = false
    var query = false

    // Parse arguments
    for (i in args.indices){
        if ((args[i] == "-p" || args[i] == "-path") && i+1<args.size){
            folderPath = args[i+1]
        }
        if(args[i] == "-f" || args[i] == "-force"){
            force = true
        }
        if(args[i] == "-q" || args[i] == "-query"){
            query = true
        }
    }

    // Execution logic based on parsed arguments
    if(folderPath != ""){
        // File object representing the folder
        val folder = File(folderPath)

        // Check if the folder exists
        if (!folder.exists() || !folder.isDirectory) {
            println("Invalid folder path or folder does not exist.")
            return
        }

        // File paths for the tokenMap.json and trie.json
        val tokenMapFilePath = ".\\indexedFolders\\" + folderPath + "_tokenMap.json"
        val trieFilePath = ".\\indexedFolders\\" + folderPath + "_trie.json"

        // Logic if force = false
        if(!force){
            // Check if they already exist
            if (File(tokenMapFilePath).exists() && File(trieFilePath).exists()) {

                if(query){
                    tokenMap = loadTokenMapFromJson(tokenMapFilePath)
                    if (tokenMap.isNotEmpty()) {
                        println("tokenMap loaded from $tokenMapFilePath successfully.")
                    }
                    trie.root = loadTrieFromJson(trieFilePath).root
                    if (trie.root.child.isNotEmpty()) {
                        println("trie loaded from $trieFilePath successfully.")
                    }
                }
                else{
                    println("tokenMap for $tokenMapFilePath already exists.")
                    println("trie for $trieFilePath already exists.")
                }

            } else {
                // Loops over the folder and its subfolders and tokenizes the words in files
                val allowedExtensions = readAllowedExtensionsFromFile()
                loopFilesAndSubfolders(folder, trie, allowedExtensions)

                // Save tokenMap to JSON file
                saveTokenMapToJson(tokenMapFilePath)

                // Save trie to JSON file
                saveTrieToJson(trie.root, trieFilePath)
            }

            // Starts prompting the user for query's
            if(query){
                // Loop until Enter is pressed
                var searchString: String
                while (true) {
                    // Prompt the user to input a query
                    while (true) {
                        println("Enter the string to search for (or press Enter to quit):")
                        searchString = readlnOrNull()?.trim() ?: ""

                        if (searchString.isEmpty()) {
                            print("Are you sure you want to quit? [Y/N]")
                            val quit = readlnOrNull()?.trim() ?: "N"
                            if (quit.uppercase() == "Y") {
                                exitProcess(0)
                            } else {
                                continue
                            }
                        }
                        break
                    }

                    // Print search results
                    if (trie.search(searchString)) {
                        println("Search Results:")
                        // Retrieve token information from the HashMap
                        val tokenInfoList = tokenMap[searchString.hashCode().toString()]
                        tokenInfoList?.forEach { tokenInfo ->
                            println("String '$searchString' found in file '${tokenInfo.fp}' at position ${tokenInfo.p}")
                        }
                        if (tokenInfoList != null) {
                            println("Found ${tokenInfoList.size} occurrences in the indexed folder")
                        }
                    } else {
                        println("No matches found for '$searchString'.")
                    }
                }
            }
            else{
                return
            }
        }
        else{
            // Loops over the folder and its subfolders and tokenizes the words in files
            val allowedExtensions = readAllowedExtensionsFromFile()
            loopFilesAndSubfolders(folder, trie, allowedExtensions)

            // Save tokenMap to JSON file
            saveTokenMapToJson(tokenMapFilePath)

            // Save trie to JSON file
            saveTrieToJson(trie.root, trieFilePath)

            // Starts prompting the user for query's
            if(query){
                // Loop until Enter is pressed
                var searchString: String
                while (true) {
                    // Prompt the user to input a query
                    while (true) {
                        println("Enter the string to search for (or press Enter to quit):")
                        searchString = readlnOrNull()?.trim() ?: ""

                        if (searchString.isEmpty()) {
                            print("Are you sure you want to quit? [Y/N]")
                            val quit = readlnOrNull()?.trim() ?: "N"
                            if (quit.uppercase() == "Y") {
                                exitProcess(0)
                            } else {
                                continue
                            }
                        }
                        break
                    }

                    // Print search results
                    if (trie.search(searchString)) {
                        println("Search Results:")
                            // Retrieve token information from the HashMap
                            val tokenInfoList = tokenMap[searchString.hashCode().toString()]
                            tokenInfoList?.forEach { tokenInfo ->
                                println("String '$searchString' found in file '${tokenInfo.fp}' at position ${tokenInfo.p}")
                            }
                            if (tokenInfoList != null) {
                            println("Found ${tokenInfoList.size} occurrences in the indexed folder")
                            }
                    } else {
                        println("No matches found for '$searchString'.")
                    }
                }
            }
            else{
                return
            }
        }
    }
    else{
        println("No folder path argument!")
    }
}
