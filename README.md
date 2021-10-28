# InferRules

To build : 
`./gradlew build`


#### Running as a binary

Download the latest release or run `./gradlew distZip`.

Unzip `../distributions/InferRules-1.0-SNAPSHOT.zip`
Got to `/bin`
Run `./Infer -b "<Before Code Snippet>" -a "<After Code Snippet>" -l "<Languag>"`
  
Example: 
` ./InferRules -b "count = 0\nfor e in es:\n    count += e\nprint(count)" -a "count = np.sum(es)" -l "Python"`
