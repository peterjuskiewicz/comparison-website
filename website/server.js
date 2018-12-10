//Import the express and url modules
var express = require('express');
var url = require("url");

//Status codes defined in external file
require('./http_status.js');

//The express module is a function. When it is executed it returns an app object
var app = express();

//Import the mysql module
var mysql = require('mysql');

//Create a connection object with the user details
var connectionPool = mysql.createPool({
    connectionLimit: 1,
    host: "localhost",
    user: "root",
    password: "password",
    database: "compare_database",
    debug: false
});

app.get("/products", handleGetRequest);

app.get("/products/*", handleGetRequest);

//Serve up static pages from public folder
app.use(express.static('public'));

app.listen(8080);

function handleGetRequest(request, response) {


  let urlObj = url.parse(request.url, true);


  let pathArray = urlObj.pathname.split("/");

  let searchProduct = pathArray[pathArray.length - 1];

  console.log(searchProduct);

  let sql = "SELECT product.brand, retailer_product.price, retailer_product.url, product.name " +
    "FROM product " +
    "INNER JOIN  retailer_product ON product.id = retailer_product.product_id " +
    "WHERE product.brand  LIKE " + "'" + searchProduct + "'";

  connectionPool.query(sql, function(err, result) {

    if(err) {
      response.status(HTTP_STATUS.INTERNAL_SERVER_ERROR);
      response.json({'error': true, 'message': + err});
      return;
    }
    response.json(result);

  });








}