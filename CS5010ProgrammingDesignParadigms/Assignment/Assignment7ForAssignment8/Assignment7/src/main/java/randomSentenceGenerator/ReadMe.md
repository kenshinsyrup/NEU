Includetheentrypointtoyourprogram. 

### Run
1. Input arguments should be a fold path under "src" like "src/grammars" containing the grammar json files

2. Start the Main method.

3. In the terminal, input parameters. If given parameter is invalid, should get warning. If valid, should get sentence.


### Design
1. We use Generator to drive the whole program. 

2. With the given argument path, the JSONReader will read the file names under that directory and prompt all names in the terminal to let the user choose.

3. After user choose a file, the JSONReader will read that file and put all data into the Grammar. This way we save the memory.

4. The Grammar will check if the data are valid, if so, build sentence and the Generator will output it.

