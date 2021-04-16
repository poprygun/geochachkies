// server.js
// where your node app starts
// init project
var dotenv = require('dotenv')
var dotenvExpand = require('dotenv-expand')

var myEnv = dotenv.config()
dotenvExpand(myEnv)

const express = require('express');
const app = express();

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('.'));

// http://expressjs.com/en/starter/basic-routing.html
app.get('/', function(request, response) {
  response.sendFile(__dirname + '/index.html');
});

// listen for requests :)
const listener = app.listen(process.env.REACT_APP_PORT, function() {
  console.log('Your app is listening on port ' + listener.address().port);
});