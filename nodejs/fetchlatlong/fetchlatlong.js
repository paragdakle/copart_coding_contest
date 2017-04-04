var request = require('sync-request');
var fs = require('fs');
var csv = require('csv');
var stream = fs.createReadStream("VehicleDetails.csv");

var locations = {};

/* Read the csv and perform http request to get lat long if new location found.
 * Currently an array (dictionary) is used in place of a database.
 */
var csvStream = csv
    .parse()
    .on("data", function(data){ //For each entry in csv
        if(!(data[19] in locations)) {
            //Since no operation is being done parallely, the http requests are intentionally performed synchronously
            var res = request('GET', 'http://maps.googleapis.com/maps/api/geocode/json?address=' + formatLocation(data[19]));
            var response = res.getBody();
            var responseJson = JSON.parse(response.toString())
            //If response contains a match store it to the array
            if(responseJson.results[0] != undefined) {
                locations[data[19]] = {
                    'Lat': responseJson.results[0].geometry.location.lat,
                    'Long': responseJson.results[0].geometry.location.lng
                };
            }
        }
    })
    .on("end", function(){
        console.log(locations);
        console.log("done");
    });
 
stream.pipe(csvStream);

//Convert the location to a search safe string
var formatLocation = function(location) {
    return formattedLocation = location.replace(/\s+/g, '+');
}