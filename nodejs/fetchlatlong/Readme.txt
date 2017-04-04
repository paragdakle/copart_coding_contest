Readme for Fetch Lat Long problem:

The csv dataset is read per line basis and if new location is found the corresponding lat long are fetched.

A database insert async insert will simply replace the array insert operation.

The following packages are needed to run the application:
1. sudo apt-get install nodejs
2. sudo apt-get install npm
3. npm install sync-request
4. npm install csv
5. npm install fast-csv

Currently the code assumes the csv file will be titled VehiclesDetails.csv and will be located in the same directory as the js file.
Post completion, all the unique locations along with their lat long values are printed on console.

To run the code exuecute:

nodejs fetchlatlong.js